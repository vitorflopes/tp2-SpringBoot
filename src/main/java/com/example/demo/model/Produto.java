package com.example.demo.model;

import lombok.Data;

@Data
public class Produto {
    private Integer id;
    private String nome;
    private String descricao;
    private Double preco;
}
