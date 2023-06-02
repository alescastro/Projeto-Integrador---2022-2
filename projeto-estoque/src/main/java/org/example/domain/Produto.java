package org.example.domain;

public class Produto {
    private String nome;
    private PropriedadesCategoria propriedadesCategoria;

    public Produto(String nome, PropriedadesCategoria propriedadesCategoria) {
        this.nome = nome;
        this.propriedadesCategoria = propriedadesCategoria;
    }

    public String getNome() {
        return nome;
    }

    public PropriedadesCategoria getCategoria() {
        return propriedadesCategoria;
    }
}
