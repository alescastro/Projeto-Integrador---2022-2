package org.example.services;

import org.example.domain.Genero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneroService {
    static void exibirGeneros(List<Genero> generos) {
        System.out.println("Escolha o gênero:");
        for (int i = 0; i < generos.size(); i++) {
            System.out.println((i + 1) + "- " + generos.get(i).getNome());
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
