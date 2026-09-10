package com.example.SistemaDBar.controller;

import com.example.SistemaDBar.model.Produto;
import com.example.SistemaDBar.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listarTodos();
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

}
