package org.example.domain;

import org.example.domain.Genero;

public class PropriedadesCategoria {
    private String nome;
    private Genero genero;

    public PropriedadesCategoria(String nome, Genero genero) {
        this.nome = nome;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public Genero getGenero() {
        return genero;
    }
}
