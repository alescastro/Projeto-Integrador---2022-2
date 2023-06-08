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
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static org.example.algoritmo.Algoritmo.backtracking;
import static org.example.services.CategoriaService.cadastrarCategoria;
import static org.example.services.CategoriaService.exibirCategorias;
import static org.example.services.GeneroService.criarGenerosFixos;
import static org.example.services.ProdutoService.cadastrarProduto;
import static org.example.services.ProdutoService.exibirProdutos;



public class Main {

    private static JFrame frame;
    private static JPanel panel;
    private static JButton cadastrarCategoriaButton;
    private static JButton cadastrarProdutoButton;
    private static JButton exibirCategoriasButton;
    private static JButton exibirProdutosButton;
    private static JButton organizarEstoqueButton;
    private static JButton excluirCategoriaButton;
    private static JButton excluirProdutoButton;
    private static JButton sairButton;





    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<PropriedadesCategoria> propriedadesCategorias = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        List<Genero> generos = criarGenerosFixos();

        int opcao;
//        do {
//            exibirMenu();
//            opcao = scanner.nextInt();
//            scanner.nextLine(); // Limpar o buffer do scanner
//
//            switch (opcao) {
//                case 1:
//                    cadastrarCategoria(scanner, propriedadesCategorias, generos);
//                    break;
//                case 2:
//                    cadastrarProduto(scanner, propriedadesCategorias, produtos);
//                    break;
//                case 3:
//                    exibirCategoriasNumeradas(propriedadesCategorias);
//                    break;
//                case 4:
//                    exibirProdutosNumerados(produtos);
//                    break;
//                case 5:
//                    organizarEstoque(propriedadesCategorias, generos, produtos);
//                    break;
//                case 6:
//                    excluirCategoria(scanner, propriedadesCategorias);
//                    break;
//                case 7:
//                    excluirProduto(scanner, produtos);
//                    break;
//                case 0:
//                    System.out.println("Encerrando o programa. Obrigado por nos utilizar!");
//                    break;
//                default:
//                    System.out.println("Opção inválida. Digite novamente.");
//                    break;
//            }
//        } while (opcao != 0);



        frame = new JFrame("Gerenciador de Estoque");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        panel = new JPanel();
        panel.setLayout(null);

        cadastrarCategoriaButton = new JButton("Cadastrar Categoria");
        cadastrarCategoriaButton.setBounds(10, 10, 180, 25);
        panel.add(cadastrarCategoriaButton);

        cadastrarProdutoButton = new JButton("Cadastrar Produto");
        cadastrarProdutoButton.setBounds(10, 45, 180, 25);
        panel.add(cadastrarProdutoButton);

        exibirCategoriasButton = new JButton("Exibir Categorias");
        exibirCategoriasButton.setBounds(10, 80, 180, 25);
        panel.add(exibirCategoriasButton);

        exibirProdutosButton = new JButton("Exibir Produtos");
        exibirProdutosButton.setBounds(10, 115, 180, 25);
        panel.add(exibirProdutosButton);

        organizarEstoqueButton = new JButton("Organizar Estoque");
        organizarEstoqueButton.setBounds(10, 150, 180, 25);
        panel.add(organizarEstoqueButton);

        excluirCategoriaButton = new JButton("Excluir Categoria");
        excluirCategoriaButton.setBounds(10, 185, 180, 25);
        panel.add(excluirCategoriaButton);

        excluirProdutoButton = new JButton("Excluir Produto");
        excluirProdutoButton.setBounds(10, 220, 180, 25);
        panel.add(excluirProdutoButton);

        sairButton = new JButton("Sair");
        sairButton.setBounds(200, 220, 180, 25);
        panel.add(sairButton);

        // Adicione os ActionListener para cada botão
        cadastrarCategoriaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarCategoria(scanner, propriedadesCategorias, generos);
            }
        });

        cadastrarProdutoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarProduto(scanner, propriedadesCategorias, produtos);
            }
        });

        exibirCategoriasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibirCategorias(propriedadesCategorias);
            }
        });

        exibirProdutosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibirProdutosNumerados(produtos);
            }
        });

        organizarEstoqueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                organizarEstoque(propriedadesCategorias, generos, produtos);
            }
        });

        excluirCategoriaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirCategoria(scanner, propriedadesCategorias);
            }
        });

        excluirProdutoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirProduto(scanner, produtos);
            }
        });

        sairButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Encerramento do Programa");

                int confirmacao = JOptionPane.showConfirmDialog(frame, "Encerrar o programa? Obrigado por nos utilizar!",
                        "Encerramento do Programa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirmacao == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Adicione o painel ao frame
        frame.add(panel);

        // Defina o frame como visível
        frame.setVisible(true);

    }

    private static void exibirMenu() {
        System.out.println("*     MENU     *");
        System.out.println("1- Cadastrar categoria");
        System.out.println("2- Cadastrar produto");
        System.out.println("3- Exibir categorias");
        System.out.println("4- Exibir produtos");
        System.out.println("5- Organizar estoque");
        System.out.println("6- Excluir categoria");
        System.out.println("7- Excluir produto");
        System.out.println("0- Sair");
        System.out.println("*");
        System.out.println("Digite a opção desejada:");
    }

    private static void organizarEstoque(List<PropriedadesCategoria> propriedadesCategorias, List<Genero> generos, List<Produto> produtos) {
        JFrame frame = new JFrame("Organização de Estoque");

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

        StringBuilder mensagem = new StringBuilder();

        if (backtracking(grafo, cores, 0, generos.size(), propriedadesCategorias)) {
            mensagem.append("Estoque organizado com sucesso.\n\n");

            // Cria um mapa para armazenar os produtos por gênero
            Map<Genero, List<Produto>> produtosPorGenero = new HashMap<>();
            for (Genero genero : generos) {
                produtosPorGenero.put(genero, new ArrayList<>());
            }

            // Classifica os produtos por gênero
            for (Produto produto : produtos) {
                Genero generoProduto = produto.getCategoria().getGenero();
                List<Produto> produtosDoGenero = produtosPorGenero.get(generoProduto);
                produtosDoGenero.add(produto);
            }

            // Adiciona as informações dos armazéns por gênero que não estão vazios
            for (Map.Entry<Genero, List<Produto>> entry : produtosPorGenero.entrySet()) {
                Genero genero = entry.getKey();
                List<Produto> produtosDoGenero = entry.getValue();

                if (!produtosDoGenero.isEmpty()) {
                    mensagem.append("Armazém:\n");
                    mensagem.append("- Gênero: ").append(genero.getNome()).append("\n");
                    mensagem.append("  Produtos:\n");
                    for (Produto produto : produtosDoGenero) {
                        mensagem.append("    - ").append(produto.getNome()).append("\n");
                    }
                    mensagem.append("\n");
                }
            }
        } else {
            mensagem.append("Não é possível organizar o estoque de acordo com as restrições de gênero.");
        }

        JOptionPane.showMessageDialog(frame, mensagem.toString(), "Organização de Estoque", JOptionPane.INFORMATION_MESSAGE);

    }

    private static void exibirCategoriasNumeradas(List<PropriedadesCategoria> propriedadesCategorias) {
        System.out.println("* CATEGORIAS *");
        for (int i = 0; i < propriedadesCategorias.size(); i++) {
            PropriedadesCategoria categoria = propriedadesCategorias.get(i);
            System.out.println((i + 1) + ". " + categoria.getNome());
        }
        System.out.println();
    }

    private static void exibirProdutosNumerados(List<Produto> produtos) {
        JFrame frame = new JFrame("Lista de Produtos");

        StringBuilder produtosString = new StringBuilder();
        produtosString.append("<html><body><h2>PRODUTOS</h2>");

        if (produtos.isEmpty()) {
            produtosString.append("<p>Nenhum produto cadastrado.</p>");
        } else {
            produtosString.append("<ul>");
            for (int i = 0; i < produtos.size(); i++) {
                Produto produto = produtos.get(i);
                produtosString.append("<li>").append(produto.getNome()).append("</li>");
            }
            produtosString.append("</ul>");
        }

        produtosString.append("</body></html>");

        JOptionPane.showMessageDialog(frame, produtosString.toString(), "Lista de Produtos", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void excluirCategoria(Scanner scanner, List<PropriedadesCategoria> propriedadesCategorias) {
        JFrame frame = new JFrame("Exclusão de Categoria");

        StringBuilder mensagem = new StringBuilder();

        if (propriedadesCategorias.isEmpty()) {
            mensagem.append("Não há categorias cadastradas. Não é possível excluir uma categoria.");
        } else {
            mensagem.append("Categorias:\n");
            for (int i = 0; i < propriedadesCategorias.size(); i++) {
                PropriedadesCategoria propriedadesCategoria = propriedadesCategorias.get(i);
                mensagem.append((i + 1)).append("- CATEGORIA: ").append(propriedadesCategoria.getNome())
                        .append(" | GÊNERO: ").append(propriedadesCategoria.getGenero().getNome()).append("\n");
            }

            boolean numeroValido = false;
            int numeroCategoria = 0;
            do {
                String numeroCategoriaStr = JOptionPane.showInputDialog(frame, mensagem.toString(),
                        "Exclusão de Categoria", JOptionPane.QUESTION_MESSAGE);

                try {
                    numeroCategoria = Integer.parseInt(numeroCategoriaStr);
                    if (numeroCategoria >= 1 && numeroCategoria <= propriedadesCategorias.size()) {
                        numeroValido = true;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Número de categoria inválido.", "Exclusão de Categoria",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Número de categoria inválido.", "Exclusão de Categoria",
                            JOptionPane.ERROR_MESSAGE);
                }
            } while (!numeroValido);

            PropriedadesCategoria categoriaRemovida = propriedadesCategorias.remove(numeroCategoria - 1);
            JOptionPane.showMessageDialog(frame, "Categoria " + categoriaRemovida.getNome() + " removida com sucesso.",
                    "Exclusão de Categoria", JOptionPane.INFORMATION_MESSAGE);
        }

        JOptionPane.showMessageDialog(frame, mensagem.toString(), "Exclusão de Categoria", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void excluirProduto(Scanner scanner, List<Produto> produtos) {
        JFrame frame = new JFrame("Exclusão de Produto");

        StringBuilder mensagem = new StringBuilder();

        if (produtos.isEmpty()) {
            mensagem.append("Não há produtos cadastrados. Não é possível excluir um produto.");
        } else {
            mensagem.append("Produtos:\n");
            for (int i = 0; i < produtos.size(); i++) {
                Produto produto = produtos.get(i);
                mensagem.append((i + 1)).append(". ").append(produto.getNome()).append("\n");
            }

            boolean numeroValido = false;
            int numeroProduto = 0;
            do {
                String numeroProdutoStr = JOptionPane.showInputDialog(frame, mensagem.toString(),
                        "Exclusão de Produto", JOptionPane.QUESTION_MESSAGE);

                try {
                    numeroProduto = Integer.parseInt(numeroProdutoStr);
                    if (numeroProduto >= 1 && numeroProduto <= produtos.size()) {
                        numeroValido = true;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Número de produto inválido.", "Exclusão de Produto",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Número de produto inválido.", "Exclusão de Produto",
                            JOptionPane.ERROR_MESSAGE);
                }
            } while (!numeroValido);

            Produto produtoRemovido = produtos.remove(numeroProduto - 1);
            JOptionPane.showMessageDialog(frame, "Produto " + produtoRemovido.getNome() + " removido com sucesso.",
                    "Exclusão de Produto", JOptionPane.INFORMATION_MESSAGE);
        }

        JOptionPane.showMessageDialog(frame, mensagem.toString(), "Exclusão de Produto", JOptionPane.INFORMATION_MESSAGE);
    }
}