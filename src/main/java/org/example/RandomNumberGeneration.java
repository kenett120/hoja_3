package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase encargada de generar listas de números enteros aleatorios.
 *
 * Los números generados se utilizan como datos de entrada
 * para los algoritmos de ordenamiento en las pruebas de rendimiento.
 *
 * El valor máximo posible para los números generados está
 * definido por la constante MAX_VALUE.
 *
 * @author Kenett
 * @version 1.0
 */
public class RandomNumberGeneration {

    /**
     * Valor máximo (exclusivo) que pueden tomar los números generados.
     */
    private static final int MAX_VALUE = 10000;

    /**
     * Genera una lista de números enteros aleatorios.
     *
     * Cada número generado estará en el rango
     * de 0 (inclusive) a MAX_VALUE (exclusivo).
     *
     * @param amount cantidad de números aleatorios a generar
     * @return lista de números enteros generados aleatoriamente
     */
    public List<Integer> generateNumbers(int amount) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            numbers.add(random.nextInt(MAX_VALUE));
        }

        return numbers;
    }
}

