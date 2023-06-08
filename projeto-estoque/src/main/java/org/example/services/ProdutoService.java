package org.example.services;

import org.example.domain.Produto;
import org.example.domain.PropriedadesCategoria;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

import static org.example.services.CategoriaService.exibirCategorias;

public class ProdutoService {
    public static void cadastrarProduto(Scanner scanner, List<PropriedadesCategoria> propriedadesCategorias, List<Produto> produtos) {
        JFrame frame = new JFrame("Cadastrar Produto");

        if (propriedadesCategorias.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Não há categorias cadastradas. Cadastre uma categoria antes de adicionar um produto.", "Cadastro de Produto", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Solicitar o nome do produto
        String nomeProduto = JOptionPane.showInputDialog(frame, "Digite o nome do produto:");

        // Criar uma lista de opções para as categorias
        String[] opcoesCategoria = new String[propriedadesCategorias.size()];
        for (int i = 0; i < propriedadesCategorias.size(); i++) {
            PropriedadesCategoria categoria = propriedadesCategorias.get(i);
            opcoesCategoria[i] = categoria.getNome() + " (Gênero: " + categoria.getGenero().getNome() + ")";
        }

        // Solicitar a escolha da categoria do produto
        String opcaoCategoria = (String) JOptionPane.showInputDialog(frame, "Escolha a categoria do produto:", "Selecionar Categoria", JOptionPane.QUESTION_MESSAGE, null, opcoesCategoria, opcoesCategoria[0]);

        if (opcaoCategoria == null) {
            // O usuário cancelou a seleção da categoria
            JOptionPane.showMessageDialog(frame, "Nenhuma categoria selecionada. Produto não cadastrado.", "Cadastro de Produto", JOptionPane.WARNING_MESSAGE);
        } else {
            // Encontrar a categoria selecionada na lista de propriedades de categoria
            PropriedadesCategoria categoriaSelecionada = null;
            for (PropriedadesCategoria categoria : propriedadesCategorias) {
                String categoriaDescricao = categoria.getNome() + " (Gênero: " + categoria.getGenero().getNome() + ")";
                if (categoriaDescricao.equals(opcaoCategoria)) {
                    categoriaSelecionada = categoria;
                    break;
                }
            }

            if (categoriaSelecionada != null) {
                // Criar o objeto Produto com a categoria e o nome fornecidos
                Produto novoProduto = new Produto(nomeProduto, categoriaSelecionada);
                produtos.add(novoProduto);

                JOptionPane.showMessageDialog(frame, "Produto cadastrado com sucesso.", "Cadastro de Produto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Categoria inválida. Produto não cadastrado.", "Cadastro de Produto", JOptionPane.ERROR_MESSAGE);
            }
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
