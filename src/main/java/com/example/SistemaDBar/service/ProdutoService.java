package com.example.SistemaDBar.service;

import com.example.SistemaDBar.model.Produto;
import com.example.SistemaDBar.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    };

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
    }

    public Produto reduzirEstoque(String nome, Integer quantidade) {
        Produto produto = produtoRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + nome));

        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (produto.getEstoque() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente para " + nome);
        }

        produto.setEstoque(produto.getEstoque() - quantidade);
        return produtoRepository.save(produto);
    }

    /*
    public Produto criarProduto(Produto produto, String nome, String categoria, BigDecimal preco, Integer estoque){

        P
    }
    */
}
