package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGeneration {

    private static final int MAX_VALUE = 10000;

    /**
     * Genera una lista de números aleatorios
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
