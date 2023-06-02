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

    public void graphColoring(int[][] graph, PropriedadesCategoria[] cores) {
        int numVertices = graph.length;
        PropriedadesCategoria[] coloracoes = new PropriedadesCategoria[numVertices];
        graphColoring(graph, cores, 0, coloracoes);
    }
    //todo Atual problema é que ele nao esta mostrando a categoria certa. REVISAR o que queremos mostrar e comparar com o que ele esta mostrando
    private void graphColoring(int[][] graph, PropriedadesCategoria[] cores, int vertice, PropriedadesCategoria[] coloracoes) {
        int numVertices = graph.length;
        if (vertice == numVertices) {
            printSolution(coloracoes);
            solutionFound = true;
            return;
        }

        for (PropriedadesCategoria cor : cores) {
            if (isColorValid(graph, coloracoes, vertice, cor)) {
                coloracoes[vertice] = cor;
                graphColoring(graph, cores, vertice + 1, coloracoes);
                coloracoes[vertice] = null; // backtrack

                if (solutionFound) {
                    return;
                }
            }
        }
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
            System.out.println("*** Produtos da categoria " + categoria + ": ***");
            for (Object produto : produtos) {
                System.out.println(produto);
            }
            System.out.println(); // linha em branco entre as categorias
        }
    }
    public static boolean backtracking(boolean[][] grafo, int[] cores, int posicao, int numCores) {
        if (posicao == cores.length) {
            return true;
        }

        for (int cor = 0; cor < numCores; cor++) {
            if (ehCorSegura(grafo, cores, posicao, cor)) {
                cores[posicao] = cor;
                if (backtracking(grafo, cores, posicao + 1, numCores)) {
                    return true;
                }
                cores[posicao] = -1;
            }
        }

        return false;
    }
}

