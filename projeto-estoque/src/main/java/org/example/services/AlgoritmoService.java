package org.example.services;

public class AlgoritmoService {
    public static boolean ehCorSegura(boolean[][] grafo, int[] cores, int posicao, int cor) {
        for (int i = 0; i < grafo.length; i++) {
            if (grafo[posicao][i] && cores[i] == cor) {
                return false;
            }
        }
        return true;
    }
}
