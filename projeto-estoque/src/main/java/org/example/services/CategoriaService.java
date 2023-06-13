package org.example.services;

import org.example.domain.PropriedadesCategoria;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class CategoriaService {
    private static final Font FONT = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240);

    public static void cadastrarCategoria(Scanner scanner, List<PropriedadesCategoria> propriedadesCategorias) {
        setLookAndFeel();

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(BACKGROUND_COLOR);

        // Título da janela
        JLabel titleLabel = new JLabel("Cadastrar Categoria");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Painel de conteúdo
        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        contentPanel.setBackground(BACKGROUND_COLOR);

        // Campo de texto para o nome da categoria
        JLabel nomeLabel = new JLabel("Nome da Categoria:");
        nomeLabel.setFont(FONT);
        JTextField nomeTextField = new JTextField();
        nomeTextField.setFont(FONT);

        // Adiciona os componentes ao painel de conteúdo
        contentPanel.add(nomeLabel);
        contentPanel.add(nomeTextField);

        // Adiciona o painel de conteúdo ao painel principal
        panel.add(contentPanel, BorderLayout.CENTER);

        // Caixa de diálogo para a inserção do nome da categoria
        int result = JOptionPane.showConfirmDialog(null, panel, "Cadastrar Categoria", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nomeCategoria = nomeTextField.getText().trim();

            if (nomeCategoria.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum nome de categoria fornecido. Categoria não cadastrada.", "Cadastro de Categoria", JOptionPane.WARNING_MESSAGE);
                return;
            }

            PropriedadesCategoria novaPropriedadesCategoria = new PropriedadesCategoria(nomeCategoria);
            propriedadesCategorias.add(novaPropriedadesCategoria);
            JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso.", "Cadastro de Categoria", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void exibirCategorias(List<PropriedadesCategoria> propriedadesCategorias) {
        setLookAndFeel();

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(BACKGROUND_COLOR);

        // Título da janela
        JLabel titleLabel = new JLabel("Categoria(s)");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Painel de conteúdo
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BACKGROUND_COLOR);

        if (propriedadesCategorias.isEmpty()) {
            JLabel emptyLabel = new JLabel("Nenhuma categoria cadastrada.");
            emptyLabel.setFont(FONT);
            contentPanel.add(emptyLabel);
        } else {
            for (int i = 0; i < propriedadesCategorias.size(); i++) {
                PropriedadesCategoria propriedadesCategoria = propriedadesCategorias.get(i);
                JLabel categoriaLabel = new JLabel((i + 1) + "- CATEGORIA: " + propriedadesCategoria.getNome());
                categoriaLabel.setFont(FONT);
                contentPanel.add(categoriaLabel);
            }
        }

        // Adiciona o painel de conteúdo ao painel principal
        panel.add(contentPanel, BorderLayout.CENTER);

        // Caixa de diálogo para exibir as categorias
        JOptionPane.showMessageDialog(null, panel, "Categorias", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
