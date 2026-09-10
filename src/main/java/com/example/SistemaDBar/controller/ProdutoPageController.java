package com.example.SistemaDBar.controller;

import com.example.SistemaDBar.model.Produto;
import com.example.SistemaDBar.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoPageController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        model.addAttribute("produto", new Produto());
        return "produtos";
    }

    @PostMapping
    public String criar(@ModelAttribute Produto produto) {
        produtoService.salvar(produto);
        return "redirect:/produtos";
    }

    @PostMapping("/deletar")
    public String deletar(@ModelAttribute Produto produto) {
        produtoService.reduzirEstoque(produto.getNome(), produto.getEstoque());
        return "redirect:/produtos";
    }
}