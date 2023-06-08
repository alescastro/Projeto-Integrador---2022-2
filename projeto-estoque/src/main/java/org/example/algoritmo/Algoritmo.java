package org.example.algoritmo;

import org.example.domain.Categoria;
import org.example.domain.Produtos;
import org.example.domain.PropriedadesCategoria;

import java.util.*;

import static org.example.services.AlgoritmoService.ehCorSegura;

public class Algoritmo {

    private boolean solutionFound = false;
    private List<Produtos> listaProdutos;

    public Algoritmo(List<Produtos> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }


    private boolean isColorValid(int[][] graph, PropriedadesCategoria[] coloracoes, int vertice, PropriedadesCategoria cor) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[vertice][i] == 1 && coloracoes[i] == cor) {
                return false;
            }
        }
        return true;
    }

    private void printSolution(PropriedadesCategoria[] coloracoes) {
        Map<Categoria, ArrayList<Object>> produtosPorCategoria = new HashMap<>();

        for (Categoria categoria : Categoria.values()) {
            produtosPorCategoria.put(categoria, new ArrayList<>());
        }

        for (int i = 0; i < coloracoes.length; i++) {
            PropriedadesCategoria propriedadesCategoria = coloracoes[i];
            produtosPorCategoria.get(propriedadesCategoria).add(listaProdutos.get(i).getNome());
        }

        for (Categoria categoria : Categoria.values()) {
            List<Object> produtos = produtosPorCategoria.get(categoria);
            System.out.println("* Produtos da categoria " + categoria + ": *");
            for (Object produto : produtos) {
                System.out.println(produto);
            }
            System.out.println(); // linha em branco entre as categorias
        }
    }

    public static boolean backtracking(boolean[][] grafo, int[] cores, int posicao, int numCores, List<PropriedadesCategoria> propriedadesCategorias) {
        if (posicao == cores.length) {
            return true;
        }

        PropriedadesCategoria categoriaAtual = propriedadesCategorias.get(posicao);

        for (int cor = 0; cor < numCores; cor++) {
            if (ehCorSegura(grafo, cores, posicao, cor) && corAtendeGenero(categoriaAtual, cores, cor, propriedadesCategorias)) {
                cores[posicao] = cor;
                if (backtracking(grafo, cores, posicao + 1, numCores, propriedadesCategorias)) {
                    return true;
                }
                cores[posicao] = -1;
            }
        }

        return false;
    }

    private static boolean corAtendeGenero(PropriedadesCategoria categoriaAtual, int[] cores, int cor, List<PropriedadesCategoria> propriedadesCategorias) {
        for (int i = 0; i < cores.length; i++) {
            if (cores[i] == cor && !categoriaAtual.getGenero().equals(propriedadesCategorias.get(i).getGenero())) {
                return false;
            }
        }
        return true;
    }
}