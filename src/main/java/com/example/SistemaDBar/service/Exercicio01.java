package com.example.SistemaDBar.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.function.Predicate;

record Order(String id, String product, double amount, boolean regulatoryHold) {}

class SettlementRouter {

    private record Rule(Predicate<Order> condition, Function<Order, String> action) {}

    private final List<Rule> rules = new CopyOnWriteArrayList<>();
    private final Map<String, String> processedCache = new ConcurrentHashMap<>();
    private final Map<String, LongAdder> metrics = new ConcurrentHashMap<>();

    private static final String DEFAULT_PATH = "MANUAL_REVIEW";

    public void addRule(Predicate<Order> condition, Function<Order, String> action) {
        rules.add(new Rule(condition, action));
    }

    public String route(Order order) {
        // idempotência: computeIfAbsent garante que o cálculo só roda 1x por id,
        // mesmo sob concorrência (ConcurrentHashMap trata isso atomicamente)
        return processedCache.computeIfAbsent(order.id(), id -> {
            String path = rules.stream()
                    .filter(rule -> rule.condition().test(order))
                    .findFirst()
                    .map(rule -> rule.action().apply(order))
                    .orElse(DEFAULT_PATH);

            metrics.computeIfAbsent(path, k -> new LongAdder()).increment();
            return path;
        });
    }

    public Map<String, Long> getMetrics() {
        Map<String, Long> snapshot = new ConcurrentHashMap<>();
        metrics.forEach((path, count) -> snapshot.put(path, count.sum()));
        return snapshot;
    }
}