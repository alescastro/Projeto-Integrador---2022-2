package org.example.services;

import org.example.domain.Genero;
import org.example.domain.PropriedadesCategoria;

import java.util.List;
import java.util.Scanner;

import static org.example.services.GeneroService.exibirGeneros;

public class CategoriaService {
    public static void cadastrarCategoria(Scanner scanner, List<PropriedadesCategoria> propriedadesCategorias, List<Genero> generos) {
        System.out.println("Digite o nome da categoria:");
        String nomeCategoria = scanner.nextLine();

        System.out.println("Escolha o gênero da categoria:");
        exibirGeneros(generos);

        int opcaoGenero = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (opcaoGenero < 1 || opcaoGenero > generos.size()) {
            System.out.println("Opção inválida. Categoria não cadastrada.");
        } else {
            Genero generoSelecionado = generos.get(opcaoGenero - 1);
            PropriedadesCategoria novaPropriedadesCategoria = new PropriedadesCategoria(nomeCategoria, generoSelecionado);
            propriedadesCategorias.add(novaPropriedadesCategoria);
            System.out.println("Categoria cadastrada com sucesso.");
        }
    }

    public static void exibirCategorias(List<PropriedadesCategoria> propriedadesCategorias) {
        System.out.println("***      CATEGORIAS      ***");
        if (propriedadesCategorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
        } else {
            for (int i = 0; i < propriedadesCategorias.size(); i++) {
                PropriedadesCategoria propriedadesCategoria = propriedadesCategorias.get(i);
                System.out.println((i + 1) + "- CATEGORIA: " + propriedadesCategoria.getNome() + " | GÊNERO: " + propriedadesCategoria.getGenero().getNome());
            }
        }
        System.out.println("***************************");
    }
}
