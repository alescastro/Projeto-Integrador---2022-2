package org.example.services;

import org.example.domain.Produto;
import org.example.domain.PropriedadesCategoria;

import java.util.List;
import java.util.Scanner;

import static org.example.services.CategoriaService.exibirCategorias;

public class ProdutoService {
    public static void cadastrarProduto(Scanner scanner, List<PropriedadesCategoria> propriedadesCategorias, List<Produto> produtos) {
        if (propriedadesCategorias.isEmpty()) {
            System.out.println("Não há categorias cadastradas. Cadastre uma categoria antes de adicionar um produto.");
            return;
        }

        System.out.println("Digite o nome do produto:");
        String nomeProduto = scanner.nextLine();

        System.out.println("Escolha a categoria do produto:");
        exibirCategorias(propriedadesCategorias);

        int opcaoCategoria = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (opcaoCategoria < 1 || opcaoCategoria > propriedadesCategorias.size()) {
            System.out.println("Opção inválida. Produto não cadastrado.");
        } else {
            PropriedadesCategoria propriedadesCategoriaSelecionada = propriedadesCategorias.get(opcaoCategoria - 1);
            Produto novoProduto = new Produto(nomeProduto, propriedadesCategoriaSelecionada);
            produtos.add(novoProduto);
            System.out.println("Produto cadastrado com sucesso.");
        }
    }

    public static void exibirProdutos(List<Produto> produtos) {
        System.out.println("***        PRODUTOS        ***");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println("PRODUTO: " + produto.getNome() + " | CATEGORIA: " + produto.getCategoria().getNome());
            }
        }
        System.out.println("*****************************");
    }
}
