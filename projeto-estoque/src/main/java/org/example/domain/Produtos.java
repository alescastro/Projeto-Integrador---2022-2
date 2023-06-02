package org.example.domain;

public class Produtos {
    String nome;
    PropriedadesCategoria propriedadesCategoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PropriedadesCategoria getCategoria() {
        return propriedadesCategoria;
    }

    public void setCategoria(PropriedadesCategoria propriedadesCategoria) {
        this.propriedadesCategoria = propriedadesCategoria;
    }

    public Produtos(String nome, PropriedadesCategoria propriedadesCategoria){
        this.nome = nome;
        this.propriedadesCategoria = propriedadesCategoria;
    }
}
