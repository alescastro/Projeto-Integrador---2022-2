package org.example;

import org.example.domain.Genero;
import org.example.domain.Produto;
import org.example.domain.PropriedadesCategoria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.example.algoritmo.Algoritmo.backtracking;
import static org.example.services.CategoriaService.cadastrarCategoria;
import static org.example.services.CategoriaService.exibirCategorias;
import static org.example.services.GeneroService.criarGenerosFixos;
import static org.example.services.ProdutoService.cadastrarProduto;
import static org.example.services.ProdutoService.exibirProdutos;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<PropriedadesCategoria> propriedadesCategorias = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        List<Genero> generos = criarGenerosFixos();

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    cadastrarCategoria(scanner, propriedadesCategorias, generos);
                    break;
                case 2:
                    cadastrarProduto(scanner, propriedadesCategorias, produtos);
                    break;
                case 3:
                    exibirCategorias(propriedadesCategorias);
                    break;
                case 4:
                    exibirProdutos(produtos);
                    break;
                case 5:
                    organizarEstoque(propriedadesCategorias, generos);
                    exibirArmazem(propriedadesCategorias, produtos);
                    break;
                case 0:
                    System.out.println("Encerrando o programa. Obrigado por nos utilizar!");
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("***     MENU     ***");
        System.out.println("1- Cadastrar categoria");
        System.out.println("2- Cadastrar produto");
        System.out.println("3- Exibir categorias");
        System.out.println("4- Exibir produtos");
        System.out.println("5- Organizar estoque");
        System.out.println("0- Sair");
        System.out.println("*******************");
        System.out.println("Digite a opção desejada:");
    }
    private static void organizarEstoque(List<PropriedadesCategoria> propriedadesCategorias, List<Genero> generos) {
        int numCategorias = propriedadesCategorias.size();
        boolean[][] grafo = new boolean[numCategorias][numCategorias];

        // Cria uma matriz de adjacência com base nas restrições de gênero
        for (int i = 0; i < numCategorias; i++) {
            PropriedadesCategoria propriedadesCategoriaI = propriedadesCategorias.get(i);
            for (int j = i + 1; j < numCategorias; j++) {
                PropriedadesCategoria propriedadesCategoriaJ = propriedadesCategorias.get(j);
                if (propriedadesCategoriaI.getGenero().equals(propriedadesCategoriaJ.getGenero())) {
                    grafo[i][j] = true;
                    grafo[j][i] = true;
                }
            }
        }

        int[] cores = new int[numCategorias];
        Arrays.fill(cores, -1);

        if (backtracking(grafo, cores, 0, generos.size())) {
            System.out.println("Estoque organizado com sucesso.\n");

            // Agrupa as categorias por cor
            Map<Integer, List<PropriedadesCategoria>> grupos = new HashMap<>();
            for (int i = 0; i < numCategorias; i++) {
                int cor = cores[i];
                if (!grupos.containsKey(cor)) {
                    grupos.put(cor, new ArrayList<>());
                }
                grupos.get(cor).add(propriedadesCategorias.get(i));
            }

            // Exibe as categorias agrupadas por cor
            for (List<PropriedadesCategoria> grupo : grupos.values()) {
                System.out.println("Grupo de categorias:");
                for (PropriedadesCategoria propriedadesCategoria : grupo) {
                    System.out.println("- " + propriedadesCategoria.getNome());
                }
                System.out.println();
            }
        } else {
            System.out.println("Não é possível organizar o estoque de acordo com as restrições de gênero.");
        }
    }

    private static void exibirArmazem(List<PropriedadesCategoria> propriedadesCategorias, List<Produto> produtos) {
        System.out.println("***        ARMAZÉM        ***");
        for (PropriedadesCategoria propriedadesCategoria : propriedadesCategorias) {
            System.out.println("CATEGORIA: " + propriedadesCategoria.getNome() + " | GÊNERO: " + propriedadesCategoria.getGenero().getNome());
            System.out.println("PRODUTOS:");
            for (Produto produto : produtos) {
                if (produto.getCategoria().equals(propriedadesCategoria)) {
                    System.out.println("- " + produto.getNome());
                }
            }
            System.out.println("------------------------------");
        }
        System.out.println("*****************************");
    }
}