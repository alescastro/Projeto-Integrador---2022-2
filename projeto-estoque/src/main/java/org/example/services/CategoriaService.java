package org.example.services;

import org.example.domain.Genero;
import org.example.domain.PropriedadesCategoria;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

import static org.example.services.GeneroService.exibirGeneros;

public class CategoriaService {
    public static void cadastrarCategoria(Scanner scanner, List<PropriedadesCategoria> propriedadesCategorias, List<Genero> generos) {
        JFrame frame = new JFrame("Cadastrar Categoria");

        // Solicitar o nome da categoria
        String nomeCategoria = JOptionPane.showInputDialog(frame, "Digite o nome da categoria:");

        // Criar uma lista de opções para os gêneros
        String[] opcoesGenero = new String[generos.size()];
        for (int i = 0; i < generos.size(); i++) {
            opcoesGenero[i] = generos.get(i).getNome();
        }

        // Solicitar a escolha do gênero da categoria
        String opcaoGenero = (String) JOptionPane.showInputDialog(frame, "Escolha o gênero da categoria:", "Selecionar Gênero", JOptionPane.QUESTION_MESSAGE, null, opcoesGenero, opcoesGenero[0]);

        if (opcaoGenero == null) {
            // O usuário cancelou a seleção do gênero
            JOptionPane.showMessageDialog(frame,"Nenhum gênero selecionado. Categoria não cadastrada.");
        } else {
            // Encontrar o gênero selecionado na lista de gêneros
            Genero generoSelecionado = null;
            for (Genero genero : generos) {
                if (genero.getNome().equals(opcaoGenero)) {
                    generoSelecionado = genero;
                    break;
                }
            }

            if (generoSelecionado != null) {
                PropriedadesCategoria novaPropriedadesCategoria = new PropriedadesCategoria(nomeCategoria, generoSelecionado);
                propriedadesCategorias.add(novaPropriedadesCategoria);
                JOptionPane.showMessageDialog(frame, "Categoria cadastrada com sucesso.", "Cadastro de Categoria", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Nome da categoria inválido. Categoria não cadastrada.");
            }
        }
    }


    public static void exibirCategorias(List<PropriedadesCategoria> propriedadesCategorias) {


        JFrame frame = new JFrame("Categorias");
        StringBuilder sb = new StringBuilder();

        sb.append("***      CATEGORIAS      ***\n");

        if (propriedadesCategorias.isEmpty()) {
            sb.append("Nenhuma categoria cadastrada.\n");
        } else {
            for (int i = 0; i < propriedadesCategorias.size(); i++) {
                PropriedadesCategoria propriedadesCategoria = propriedadesCategorias.get(i);
                sb.append((i + 1) + "- CATEGORIA: " + propriedadesCategoria.getNome() + " | GÊNERO: " + propriedadesCategoria.getGenero().getNome() + "\n");
            }
        }

        sb.append("***************************");

        JOptionPane.showMessageDialog(frame, sb.toString(), "Categorias", JOptionPane.INFORMATION_MESSAGE);
    }
}
