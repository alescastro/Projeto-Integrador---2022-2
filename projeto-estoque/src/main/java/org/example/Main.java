package org.example;

import org.example.domain.Genero;
import org.example.domain.Produto;
import org.example.domain.PropriedadesCategoria;

import java.awt.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Graphics;


import static org.example.algoritmo.Algoritmo.backtracking;
import static org.example.services.CategoriaService.cadastrarCategoria;
import static org.example.services.CategoriaService.exibirCategorias;
import static org.example.services.GeneroService.criarGenerosFixos;
import static org.example.services.ProdutoService.cadastrarProduto;
import static org.example.services.ProdutoService.exibirProdutos;



public class Main {

    private static JFrame frame;
    private static JFrame welcomeFrame;
    private static JPanel panel;
    private static JButton cadastrarCategoriaButton;
    private static JButton cadastrarProdutoButton;
    private static JButton exibirCategoriasButton;
    private static JButton exibirProdutosButton;
    private static JButton organizarEstoqueButton;
    private static JButton excluirCategoriaButton;
    private static JButton excluirProdutoButton;
    private static JButton sairButton;
    private static JButton iniciarButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showWelcomeScreen();
            }
        });
    }
    private static void showWelcomeScreen() {
        welcomeFrame = new JFrame("Bem-vindo");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(600, 400);
        welcomeFrame.setLocationRelativeTo(null); // Centraliza o frame na tela

        // Imagem de fundo
        String imagePath = "C:/Users/Computador/Desktop/Projeto Integrador - II Semestre (graph coloring)/Projeto-Integrador---2022-2/projeto-estoque/src/main/java/org/example/images/background.jpg";
        ImageIcon backgroundImage = new ImageIcon(imagePath);
        JLabel backgroundLabel = new JLabel(backgroundImage);

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        contentPane.setOpaque(false);
        contentPane.setLayout(new BorderLayout());

        String overlayImagePath = "C:/Users/Computador/Desktop/Projeto Integrador - II Semestre (graph coloring)/Projeto-Integrador---2022-2/projeto-estoque/src/main/java/org/example/images/welcomemessage.png"; // Substitua pelo caminho correto da sua imagem PNG
        ImageIcon overlayImage = new ImageIcon(overlayImagePath);
        JLabel overlayLabel = new JLabel(overlayImage);
        int overlayWidth = overlayImage.getIconWidth();
        int overlayHeight = overlayImage.getIconHeight();
        int overlayX = (welcomeFrame.getWidth() - overlayWidth) / 2;
        int overlayY = (welcomeFrame.getHeight() - overlayHeight) / 3; // Ajusta a posição vertical
        overlayLabel.setBounds(overlayX, overlayY, overlayWidth, overlayHeight);
        backgroundLabel.setLayout(null);
        backgroundLabel.add(overlayLabel);

        contentPane.add(backgroundLabel, BorderLayout.CENTER);

        // Botão "Iniciar"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder()); // Define uma borda vazia

        JButton iniciarButton = new JButton("Iniciar");
        iniciarButton.setPreferredSize(new Dimension(160, 50));
        iniciarButton.setFont(new Font("Arial", Font.BOLD, 16));
        iniciarButton.setForeground(Color.BLACK);
        iniciarButton.setFocusPainted(false);
        iniciarButton.setBorderPainted(false);
        iniciarButton.setBackground(Color.WHITE);
        iniciarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        iniciarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                welcomeFrame.setVisible(false);
                welcomeFrame.dispose();
                showMainApplication();
            }
        });

        buttonPanel.add(iniciarButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        welcomeFrame.setContentPane(contentPane);
        welcomeFrame.setVisible(true);
    }


    private static void showMainApplication() {
        Scanner scanner = new Scanner(System.in);
        List<PropriedadesCategoria> propriedadesCategorias = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        List<Genero> generos = criarGenerosFixos();

        frame = new JFrame("Gerenciador de Estoque");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Define o frame como centralizado na tela

        String imagePath = "C:/Users/Computador/Desktop/Projeto Integrador - II Semestre (graph coloring)/Projeto-Integrador---2022-2/projeto-estoque/src/main/java/org/example/images/background2.jpg";
        ImageIcon originalImageIcon = new ImageIcon(imagePath);

        // Redimensionar a imagem
        Image originalImage = originalImageIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);

        JFrame welcomeFrame = new JFrame("Bem-vindo");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(600, 400);
        welcomeFrame.setLocationRelativeTo(null); // Centraliza o frame na tela

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(resizedImageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        contentPane.setOpaque(false);
        contentPane.setLayout(new BorderLayout());

        panel = new JPanel(null);
        panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        cadastrarCategoriaButton = new JButton("Cadastrar Categoria");
        cadastrarCategoriaButton.setBounds(10, 20, 180, 25);
        cadastrarCategoriaButton.setFont(new Font("Arial", Font.PLAIN, 14));
        cadastrarCategoriaButton.setBackground(Color.WHITE);
        cadastrarCategoriaButton.setForeground(Color.BLACK);
        cadastrarCategoriaButton.setFocusPainted(false);
        cadastrarCategoriaButton.setBorder(BorderFactory.createEmptyBorder());
        panel.add(cadastrarCategoriaButton);

        cadastrarProdutoButton = new JButton("Cadastrar Produto");
        cadastrarProdutoButton.setBounds(10, 55, 180, 25);
        cadastrarProdutoButton.setFont(new Font("Arial", Font.PLAIN, 14));
        cadastrarProdutoButton.setBackground(Color.WHITE);
        cadastrarProdutoButton.setForeground(Color.BLACK);
        cadastrarProdutoButton.setFocusPainted(false);
        cadastrarProdutoButton.setBorder(BorderFactory.createEmptyBorder());
        panel.add(cadastrarProdutoButton);

        exibirCategoriasButton = new JButton("Exibir Categorias");
        exibirCategoriasButton.setBounds(10, 90, 180, 25);
        exibirCategoriasButton.setFont(new Font("Arial", Font.PLAIN, 14));
        exibirCategoriasButton.setBackground(Color.WHITE);
        exibirCategoriasButton.setForeground(Color.BLACK);
        exibirCategoriasButton.setFocusPainted(false);
        exibirCategoriasButton.setBorder(BorderFactory.createEmptyBorder());
        panel.add(exibirCategoriasButton);

        exibirProdutosButton = new JButton("Exibir Produtos");
        exibirProdutosButton.setBounds(10, 125, 180, 25);
        exibirProdutosButton.setFont(new Font("Arial", Font.PLAIN, 14));
        exibirProdutosButton.setBackground(Color.WHITE);
        exibirProdutosButton.setForeground(Color.BLACK);
        exibirProdutosButton.setFocusPainted(false);
        exibirProdutosButton.setBorder(BorderFactory.createEmptyBorder());
        panel.add(exibirProdutosButton);

        organizarEstoqueButton = new JButton("Organizar Estoque");
        organizarEstoqueButton.setBounds(10, 160, 180, 25);
        organizarEstoqueButton.setFont(new Font("Arial", Font.PLAIN, 14));
        organizarEstoqueButton.setBackground(Color.WHITE);
        organizarEstoqueButton.setForeground(Color.BLACK);
        organizarEstoqueButton.setFocusPainted(false);
        organizarEstoqueButton.setBorder(BorderFactory.createEmptyBorder());
        panel.add(organizarEstoqueButton);

        excluirCategoriaButton = new JButton("Excluir Categoria");
        excluirCategoriaButton.setBounds(10, 195, 180, 25);
        excluirCategoriaButton.setFont(new Font("Arial", Font.PLAIN, 14));
        excluirCategoriaButton.setBackground(Color.WHITE);
        excluirCategoriaButton.setForeground(Color.BLACK);
        excluirCategoriaButton.setFocusPainted(false);
        excluirCategoriaButton.setBorder(BorderFactory.createEmptyBorder());
        panel.add(excluirCategoriaButton);

        excluirProdutoButton = new JButton("Excluir Produto");
        excluirProdutoButton.setBounds(10, 230, 180, 25);
        excluirProdutoButton.setFont(new Font("Arial", Font.PLAIN, 14));
        excluirProdutoButton.setBackground(Color.WHITE);
        excluirProdutoButton.setForeground(Color.BLACK);
        excluirProdutoButton.setFocusPainted(false);
        excluirProdutoButton.setBorder(BorderFactory.createEmptyBorder());
        panel.add(excluirProdutoButton);

        sairButton = new JButton("Sair");
        sairButton.setBounds(380, 330, 180, 25);
        sairButton.setFont(new Font("Arial", Font.PLAIN, 14));
        sairButton.setBackground(Color.WHITE);
        sairButton.setForeground(Color.BLACK);
        sairButton.setFocusPainted(false);
        sairButton.setBorder(BorderFactory.createEmptyBorder());
        panel.add(sairButton);


        // Adicione os ActionListeners para cada botão
        cadastrarCategoriaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarCategoria(scanner, propriedadesCategorias);
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
                organizarEstoque(propriedadesCategorias, produtos);
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
                String[] opcoes = {"Sim", "Não"};
                int confirmacao = JOptionPane.showOptionDialog(frame, "Deseja mesmo encerrar o programa?",
                        "Encerramento do Programa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, opcoes, opcoes[1]);

                if (confirmacao == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Obrigado por nos utilizar!", "Encerramento do Programa", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();  // Fecha o frame
                }
            }
        });

        // Defina o painel como transparente
        panel.setOpaque(false);

        // Adicione o painel ao painel de conteúdo
        contentPane.add(panel);

        // Defina o painel de conteúdo como o conteúdo do frame
        frame.setContentPane(contentPane);

        // Defina o frame como visível
        frame.setVisible(true);
    }


    private static int getCategoriaIndex(String categoriaNome, List<PropriedadesCategoria> propriedadesCategorias) {
        for (int i = 0; i < propriedadesCategorias.size(); i++) {
            if (propriedadesCategorias.get(i).getNome().equalsIgnoreCase(categoriaNome)) {
                return i;
            }
        }
        return -1;
    }

    private static void organizarEstoque(List<PropriedadesCategoria> propriedadesCategorias, List<Produto> produtos) {
        int numCategorias = propriedadesCategorias.size();
        boolean[][] grafo = new boolean[numCategorias][numCategorias];

        int[] cores = new int[numCategorias];
        Arrays.fill(cores, -1);

        StringBuilder mensagem = new StringBuilder();

        // Solicita as restrições de categoria
        String restricaoInput = JOptionPane.showInputDialog(null, "Digite as categorias que podem ficar juntas (separadas por vírgula):", "Restrição de Categorias", JOptionPane.PLAIN_MESSAGE);
        if (restricaoInput != null && !restricaoInput.isEmpty()) {
            String[] gruposRestricoes = restricaoInput.split("-");
            Set<Integer> categoriasInformadas = new HashSet<>();
            for (String grupo : gruposRestricoes) {
                String[] categoriasPermitidas = grupo.trim().split(",");
                if (categoriasPermitidas.length > 1) {
                    for (int i = 0; i < categoriasPermitidas.length - 1; i++) {
                        for (int j = i + 1; j < categoriasPermitidas.length; j++) {
                            int indexCategoria1 = getCategoriaIndex(categoriasPermitidas[i].trim(), propriedadesCategorias);
                            int indexCategoria2 = getCategoriaIndex(categoriasPermitidas[j].trim(), propriedadesCategorias);
                            if (indexCategoria1 != -1 && indexCategoria2 != -1) {
                                grafo[indexCategoria1][indexCategoria2] = true;
                                grafo[indexCategoria2][indexCategoria1] = true;
                                categoriasInformadas.add(indexCategoria1);
                                categoriasInformadas.add(indexCategoria2);
                            }
                        }
                    }
                }
            }

            // Encontra os componentes conexos no grafo
            List<Set<Integer>> componentesConexos = encontrarComponentesConexos(grafo);

            if (componentesConexos.size() > 0) {
                mensagem.append("<html><h2>Estoque organizado com sucesso.</h2><br><br>");

                // Cria um mapa para armazenar os produtos por categoria
                Map<Integer, List<Produto>> produtosPorCategoria = new HashMap<>();
                for (int i = 0; i < numCategorias; i++) {
                    produtosPorCategoria.put(i, new ArrayList<>());
                }

                // Classifica os produtos por categoria
                for (Produto produto : produtos) {
                    int indexCategoria = getCategoriaIndex(produto.getCategoria().getNome(), propriedadesCategorias);
                    List<Produto> produtosDaCategoria = produtosPorCategoria.get(indexCategoria);
                    produtosDaCategoria.add(produto);
                }

                // Exibe os armazéns
                int armazemIndex = 1;
                for (Set<Integer> componenteConexo : componentesConexos) {
                    mensagem.append("<b>Armazém ").append(armazemIndex).append(":</b><br>");
                    mensagem.append("  Categorias:");
                    for (int indexCategoria : componenteConexo) {
                        mensagem.append(" ").append(propriedadesCategorias.get(indexCategoria).getNome());
                    }
                    mensagem.append("<br>");
                    mensagem.append("  Produtos:<br>");
                    for (int indexCategoria : componenteConexo) {
                        PropriedadesCategoria categoria = propriedadesCategorias.get(indexCategoria);
                        List<Produto> produtosDaCategoria = produtosPorCategoria.get(indexCategoria);
                        mensagem.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Categoria: ").append(categoria.getNome()).append("<br>");
                        for (Produto produto : produtosDaCategoria) {
                            mensagem.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Produto: ").append(produto.getNome()).append("<br>");
                        }
                    }
                    armazemIndex++;
                }

                JOptionPane.showMessageDialog(null, mensagem.toString(), "Organização de Estoque", JOptionPane.INFORMATION_MESSAGE);
            } else {
                mensagem.append("Não é possível organizar o estoque com as categorias informadas.");
                JOptionPane.showMessageDialog(null, mensagem.toString(), "Organização de Estoque", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            mensagem.append("Não é possível organizar o estoque sem restrições de categoria.");
            JOptionPane.showMessageDialog(null, mensagem.toString(), "Organização de Estoque", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static List<Set<Integer>> encontrarComponentesConexos(boolean[][] grafo) {
        int numVertices = grafo.length;
        int[] cores = new int[numVertices];
        Arrays.fill(cores, -1);

        List<Set<Integer>> componentesConexos = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            if (cores[i] == -1) {
                Set<Integer> componente = new HashSet<>();
                backtracking(grafo, i, cores, componente);
                componentesConexos.add(componente);
            }
        }

        return componentesConexos;
    }

    private static void backtracking(boolean[][] grafo, int vertice, int[] cores, Set<Integer> componente) {
        cores[vertice] = 1;
        componente.add(vertice);

        for (int i = 0; i < grafo.length; i++) {
            if (grafo[vertice][i] && cores[i] == -1) {
                backtracking(grafo, i, cores, componente);
            }
        }
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
                mensagem.append((i + 1)).append("- CATEGORIA: ").append(propriedadesCategoria.getNome()).append("\n");
            }

            boolean numeroValido = false;
            int numeroCategoria = 0;
            do {
                String numeroCategoriaStr = JOptionPane.showInputDialog(frame, mensagem.toString(),
                        "Exclusão de Categoria", JOptionPane.QUESTION_MESSAGE);

                if (numeroCategoriaStr == null) {
                    // O usuário cancelou a exclusão
                    return;
                }

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

            // Atualizar os dados exibidos no frame principal
            exibirCategorias(propriedadesCategorias);
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

                if (numeroProdutoStr == null) {
                    // O usuário cancelou a exclusão
                    return;
                }

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

            // Atualizar os dados exibidos no frame principal
            exibirProdutosNumerados(produtos);
        }

        JOptionPane.showMessageDialog(frame, mensagem.toString(), "Exclusão de Produto", JOptionPane.INFORMATION_MESSAGE);
    }
}