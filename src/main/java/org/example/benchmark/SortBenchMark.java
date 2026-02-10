package org.example.benchmark;

import org.example.FileManager;
import org.example.RandomNumberGeneration;
import org.example.sorts.*;

import java.util.ArrayList;
import java.util.List;

public class SortBenchMark {

    public void runBenchmarks() throws Exception {

        RandomNumberGeneration generator = new RandomNumberGeneration();
        FileManager fileManager = new FileManager();

        for (int size = 10; size <= 3000; size += 500) {

            List<Integer> data = generator.generateNumbers(size);
            fileManager.writeNumbersToFile(data);
            List<Integer> numbers = fileManager.readNumbersFromFile();

            measure("GnomeSort", new GnomeSort<>(), new ArrayList<>(numbers));
            measure("MergeSort", new MergeSort<>(), new ArrayList<>(numbers));
            measure("QuickSort", new QuickSort<>(), new ArrayList<>(numbers));
            measureRadix(new ArrayList<>(numbers));

            System.out.println("--------------------------------");
        }
    }

    private <T extends Comparable<T>> void measure(
            String name,
            SortAlgorithm<T> algorithm,
            List<T> data) {

        long start = System.nanoTime();
        algorithm.sort(data);
        long end = System.nanoTime();

        System.out.println(name + " → " + (end - start) / 1_000_000 + " ms");
    }

    private void measureRadix(List<Integer> data) {
        RadixSort radix = new RadixSort();

        long start = System.nanoTime();
        radix.sort(data);
        long end = System.nanoTime();

        System.out.println("RadixSort → " + (end - start) / 1_000_000 + " ms");
    }
}

