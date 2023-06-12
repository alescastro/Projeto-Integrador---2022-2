package org.example.services;

import org.example.domain.Produto;
import org.example.domain.PropriedadesCategoria;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

import static org.example.services.CategoriaService.exibirCategorias;

public class ProdutoService {
    private static final Font FONT = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240);
    private static JTextField nomeTextField;
    private static JComboBox<PropriedadesCategoria> categoriaComboBox;

    public static void cadastrarProduto(Scanner scanner, List<PropriedadesCategoria> propriedadesCategorias, List<Produto> produtos) {
        setLookAndFeel();

        // Verifica se há categorias cadastradas
        if (propriedadesCategorias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há categorias cadastradas. Cadastre uma categoria antes de adicionar um produto.", "Cadastro de Produto", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(BACKGROUND_COLOR);

        // Título da janela
        JLabel titleLabel = new JLabel("Cadastrar Produto");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Painel de conteúdo
        JPanel contentPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        contentPanel.setBackground(BACKGROUND_COLOR);

        // Verifica se há categorias cadastradas
        if (propriedadesCategorias.isEmpty()) {
            JLabel emptyLabel = new JLabel("Não há categorias cadastradas. Cadastre uma categoria antes de adicionar um produto.");
            emptyLabel.setFont(FONT);
            contentPanel.add(emptyLabel);
        } else {
            // Campo de texto para o nome do produto
            JLabel nomeLabel = new JLabel("Nome do Produto:");
            nomeLabel.setFont(FONT);
            nomeTextField = new JTextField();
            nomeTextField.setFont(FONT);

            // Combo box para a seleção da categoria
            JLabel categoriaLabel = new JLabel("Escolha a categoria do produto:");
            categoriaLabel.setFont(FONT);

            categoriaComboBox = new JComboBox<>(propriedadesCategorias.toArray(new PropriedadesCategoria[0]));
            categoriaComboBox.setFont(FONT);
            categoriaComboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof PropriedadesCategoria) {
                        PropriedadesCategoria categoria = (PropriedadesCategoria) value;
                        setText(categoria.getNome() + " (Gênero: " + categoria.getGenero().getNome() + ")");
                    }
                    return this;
                }
            });

            // Adiciona os componentes ao painel de conteúdo
            contentPanel.add(nomeLabel);
            contentPanel.add(nomeTextField);
            contentPanel.add(categoriaLabel);
            contentPanel.add(categoriaComboBox);
        }

        // Adiciona o painel de conteúdo ao painel principal
        panel.add(contentPanel, BorderLayout.CENTER);

        // Caixa de diálogo para a inserção do nome do produto e escolha da categoria
        int option = JOptionPane.showConfirmDialog(null, panel, "Cadastrar Produto", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            // Obtém o nome do produto inserido
            String nomeProduto = nomeTextField.getText();

            // Verifica se o nome do produto está vazio
            if (nomeProduto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "O nome do produto não pode estar vazio. Produto não cadastrado.", "Cadastro de Produto", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtém a categoria selecionada
            PropriedadesCategoria categoriaSelecionada = (PropriedadesCategoria) categoriaComboBox.getSelectedItem();

            if (categoriaSelecionada != null) {
                // Cria o objeto Produto com a categoria e o nome fornecidos
                Produto novoProduto = new Produto(nomeProduto, categoriaSelecionada);
                produtos.add(novoProduto);

                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.", "Cadastro de Produto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma categoria selecionada. Produto não cadastrado.", "Cadastro de Produto", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void exibirProdutos(List<Produto> produtos) {
        setLookAndFeel();

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(BACKGROUND_COLOR);

        // Título da janela
        JLabel titleLabel = new JLabel("Produto(s)");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Painel de conteúdo
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BACKGROUND_COLOR);

        if (produtos.isEmpty()) {
            JLabel emptyLabel = new JLabel("Nenhum produto cadastrado.");
            emptyLabel.setFont(FONT);
            contentPanel.add(emptyLabel);
        } else {
            for (Produto produto : produtos) {
                JLabel produtoLabel = new JLabel("PRODUTO: " + produto.getNome() + " | CATEGORIA: " + produto.getCategoria().getNome());
                produtoLabel.setFont(FONT);
                contentPanel.add(produtoLabel);
            }
        }

        // Adiciona o painel de conteúdo ao painel principal
        panel.add(contentPanel, BorderLayout.CENTER);

        // Caixa de diálogo para exibir os produtos
        JOptionPane.showMessageDialog(null, panel, "Produtos", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}