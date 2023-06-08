package org.example.services;

import org.example.domain.Genero;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneroService {
    static void exibirGeneros(List<Genero> generos) {
        JFrame frame = new JFrame("Escolher Gênero");

        // Crie um array com os nomes dos gêneros
        String[] nomesGeneros = new String[generos.size()];
        for (int i = 0; i < generos.size(); i++) {
            nomesGeneros[i] = generos.get(i).getNome();
        }

        // Crie um JComboBox com os nomes dos gêneros
        JComboBox<String> comboBox = new JComboBox<>(nomesGeneros);

        // Exiba um JOptionPane com o JComboBox
        int result = JOptionPane.showOptionDialog(frame, comboBox, "Escolher Gênero",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result == JOptionPane.OK_OPTION) {
            // Obtenha o índice da opção selecionada
            int opcaoGenero = comboBox.getSelectedIndex();

            if (opcaoGenero >= 0 && opcaoGenero < generos.size()) {
                // Gênero selecionado
                Genero generoSelecionado = generos.get(opcaoGenero);
                JOptionPane.showMessageDialog(frame, "Gênero selecionado: " + generoSelecionado.getNome());
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhum gênero selecionado.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Operação cancelada.");
        }
    }

    public static List<Genero> criarGenerosFixos() {
        List<Genero> generos = new ArrayList<>();

        // Gêneros que podem ser agrupados juntos
        List<String> alimentos = Arrays.asList("Alimentos", "Bebidas");
        List<String> automoveis = Arrays.asList("Automóveis");
        List<String> beleza = Arrays.asList("Beleza", "Saúde");
        List<String> brinquedos = Arrays.asList("Brinquedos", "Jogos");
        List<String> calcados = Arrays.asList("Calçados");
        List<String> casa = Arrays.asList("Casa", "Decoração");
        List<String> eletronicos = Arrays.asList("Eletrônicos", "Tecnologia");
        List<String> joias = Arrays.asList("Joias", "Acessórios");
        List<String> livros = Arrays.asList("Livros", "Revistas");
        List<String> vestuario = Arrays.asList("Vestuário", "Moda");
        List<String> esportes = Arrays.asList("Esportes", "Fitness");


        generos.add(new Genero("Alimentos", alimentos));
        generos.add(new Genero("Automóveis", automoveis));
        generos.add(new Genero("Beleza", beleza));
        generos.add(new Genero("Brinquedos", brinquedos));
        generos.add(new Genero("Calçados", calcados));
        generos.add(new Genero("Casa", casa));
        generos.add(new Genero("Eletrônicos", eletronicos));
        generos.add(new Genero("Esportes", esportes));
        generos.add(new Genero("Joias", joias));
        generos.add(new Genero("Livros", livros));
        generos.add(new Genero("Vestuário", vestuario));



        return generos;
    }
}
