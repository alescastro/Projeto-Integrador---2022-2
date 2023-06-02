package org.example.domain;

import java.util.List;

public class Genero {
    private String nome;

    public Genero(String nome, List<String> calcados) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
