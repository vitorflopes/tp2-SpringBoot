package com.example.demo.controller;

import com.example.demo.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private List<Produto> produtos = new ArrayList<>();
    private Integer proximoId = 1;

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        produto.setId(proximoId++);
        produtos.add(produto);
        return produto;
    }
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtos;
    }
    @GetMapping("/{id}")
    public Produto buscarProdutoPorId(@PathVariable Integer id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Integer id, @RequestBody Produto produtoAtualizado) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(produtoExistente -> {
                    produtoExistente.setNome(produtoAtualizado.getNome());
                    produtoExistente.setDescricao(produtoAtualizado.getDescricao());
                    produtoExistente.setPreco(produtoAtualizado.getPreco());
                    return produtoExistente;
                })
                .orElse(null);
    }
    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Integer id) {
        produtos.removeIf(p -> p.getId().equals(id));
    }
}
