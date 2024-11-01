package com.example.mutantes.service;

import org.springframework.stereotype.Service;

@Service
public class mutantService {
    public boolean isMutant(String[] dna) {
        int n = dna.length;
        int count = 0;

        // Recorremos la matriz para verificar las secuencias en las direcciones necesarias.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j + 3 < n && hasSequence(dna, i, j, 0, 1)) count++; // Horizontal
                if (i + 3 < n && hasSequence(dna, i, j, 1, 0)) count++; // Vertical
                if (i + 3 < n && j + 3 < n && hasSequence(dna, i, j, 1, 1)) count++; // Diagonal hacia abajo derecha
                if (i - 3 >= 0 && j + 3 < n && hasSequence(dna, i, j, -1, 1)) count++; // Diagonal hacia arriba derecha

                if (count > 1) return true;
            }
        }
        return false;
    }

    private boolean hasSequence(String[] dna, int i, int j, int x, int y) {
        char firstChar = dna[i].charAt(j);
        for (int k = 1; k < 4; k++) {
            if (dna[i + k * x].charAt(j + k * y) != firstChar) return false;
        }
        return true;
    }
}
