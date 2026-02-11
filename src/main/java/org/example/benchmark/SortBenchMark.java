package org.example.benchmark;

import org.example.FileManager;
import org.example.RandomNumberGeneration;
import org.example.sorts.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Clase encargada de ejecutar las pruebas de rendimiento
 * para los diferentes algoritmos de ordenamiento.
 *
 * Esta clase:
 * - Genera números aleatorios.
 * - Guarda y lee los datos desde archivo.
 * - Ejecuta los algoritmos en dos escenarios:
 *      • Datos desordenados (caso promedio)
 *      • Datos ordenados (mejor caso)
 * - Mide el tiempo de ejecución usando System.nanoTime().
 * - Guarda los resultados en un archivo CSV.
 *
 * Las ejecuciones se realizan en paralelo utilizando
 * ExecutorService para optimizar el tiempo total del benchmark.
 *
 * Los tamaños evaluados van desde 10 hasta 3000 elementos,
 * conforme a lo solicitado en la hoja de trabajo.
 *
 * @author Joao
 * @version 1.0
 */
public class SortBenchMark {

    /**
     * Escritor utilizado para almacenar los resultados
     * del benchmark en formato CSV.
     */
    private final BufferedWriter csvWriter;

    /**
     * Constructor que inicializa el archivo CSV y escribe
     * el encabezado correspondiente.
     *
     * @throws IOException si ocurre un error al crear el archivo
     */
    public SortBenchMark() throws IOException {
        csvWriter = new BufferedWriter(new FileWriter("benchmark_results.csv"));
        csvWriter.write("Algorithm,Size,Scenario,TimeMs\n");
    }

    /**
     * Ejecuta los benchmarks para distintos tamaños de datos,
     * evaluando tanto el caso promedio como el mejor caso.
     *
     * @throws Exception si ocurre algún error durante la ejecución
     */
    public void runBenchmarks() throws Exception {
        RandomNumberGeneration generator = new RandomNumberGeneration();
        FileManager fileManager = new FileManager();

        System.out.println("Running benchmarks from 10 to 3000 elements...\n");

        // Tamaños ajustados según la hoja (máximo 3000)
        int[] sizes = {10, 100, 500, 1000, 2000, 3000};

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int size : sizes) {
            System.out.println("Testing with " + size + " elements:");

            // Generar datos aleatorios
            List<Integer> data = generator.generateNumbers(size);
            fileManager.writeNumbersToFile(data);
            List<Integer> unsortedNumbers = fileManager.readNumbersFromFile();

            // Crear copia ordenada (mejor caso)
            List<Integer> sortedNumbers = new ArrayList<>(unsortedNumbers);
            Collections.sort(sortedNumbers);

            // Caso promedio (datos desordenados)
            System.out.println("  Unsorted data (Average Case):");
            List<Future<BenchmarkResult>> unsortedFutures = new ArrayList<>();

            unsortedFutures.add(executor.submit(() ->
                    measureParallel("GnomeSort", new GnomeSort<>(), new ArrayList<>(unsortedNumbers), size, "Unsorted")));
            unsortedFutures.add(executor.submit(() ->
                    measureParallel("MergeSort", new MergeSort<>(), new ArrayList<>(unsortedNumbers), size, "Unsorted")));
            unsortedFutures.add(executor.submit(() ->
                    measureParallel("QuickSort", new QuickSort<>(), new ArrayList<>(unsortedNumbers), size, "Unsorted")));
            unsortedFutures.add(executor.submit(() ->
                    measureParallel("InsertionSort", new InsertionSort<>(), new ArrayList<>(unsortedNumbers), size, "Unsorted")));
            unsortedFutures.add(executor.submit(() ->
                    measureRadixParallel("RadixSort", new ArrayList<>(unsortedNumbers), size, "Unsorted")));

            for (Future<BenchmarkResult> future : unsortedFutures) {
                BenchmarkResult result = future.get();
                System.out.println("    " + result.name + " → " + result.timeMs + " ms");
                csvWriter.write(result.name + "," + result.size + "," + result.scenario + "," + result.timeMs + "\n");
            }

            // Mejor caso (datos ordenados)
            System.out.println("  Sorted data (Best Case):");
            List<Future<BenchmarkResult>> sortedFutures = new ArrayList<>();

            sortedFutures.add(executor.submit(() ->
                    measureParallel("GnomeSort", new GnomeSort<>(), new ArrayList<>(sortedNumbers), size, "Sorted")));
            sortedFutures.add(executor.submit(() ->
                    measureParallel("MergeSort", new MergeSort<>(), new ArrayList<>(sortedNumbers), size, "Sorted")));
            sortedFutures.add(executor.submit(() ->
                    measureParallel("QuickSort", new QuickSort<>(), new ArrayList<>(sortedNumbers), size, "Sorted")));
            sortedFutures.add(executor.submit(() ->
                    measureParallel("InsertionSort", new InsertionSort<>(), new ArrayList<>(sortedNumbers), size, "Sorted")));
            sortedFutures.add(executor.submit(() ->
                    measureRadixParallel("RadixSort", new ArrayList<>(sortedNumbers), size, "Sorted")));

            for (Future<BenchmarkResult> future : sortedFutures) {
                BenchmarkResult result = future.get();
                System.out.println("    " + result.name + " → " + result.timeMs + " ms");
                csvWriter.write(result.name + "," + result.size + "," + result.scenario + "," + result.timeMs + "\n");
            }

            System.out.println("--------------------------------");
        }

        executor.shutdown();
        csvWriter.close();

        System.out.println("\nResults saved to benchmark_results.csv");
    }

    /**
     * Mide el tiempo de ejecución de un algoritmo
     * basado en comparación.
     *
     * @param name nombre del algoritmo
     * @param algorithm instancia del algoritmo
     * @param data datos a ordenar
     * @param size tamaño del conjunto de datos
     * @param scenario escenario evaluado (Sorted o Unsorted)
     * @param <T> tipo de dato comparable
     * @return resultado encapsulado del benchmark
     */
    private <T extends Comparable<T>> BenchmarkResult measureParallel(
            String name,
            SortAlgorithm<T> algorithm,
            List<T> data,
            int size,
            String scenario) {

        long start = System.nanoTime();
        algorithm.sort(data);
        long end = System.nanoTime();

        long timeMs = (end - start) / 1_000_000;
        return new BenchmarkResult(name, size, scenario, timeMs);
    }

    /**
     * Mide el tiempo de ejecución del algoritmo Radix Sort.
     *
     * @param name nombre del algoritmo
     * @param data datos a ordenar
     * @param size tamaño del conjunto de datos
     * @param scenario escenario evaluado
     * @return resultado encapsulado del benchmark
     */
    private BenchmarkResult measureRadixParallel(
            String name,
            List<Integer> data,
            int size,
            String scenario) {

        RadixSort radix = new RadixSort();

        long start = System.nanoTime();
        radix.sort(data);
        long end = System.nanoTime();

        long timeMs = (end - start) / 1_000_000;
        return new BenchmarkResult(name, size, scenario, timeMs);
    }

    /**
     * Clase interna que encapsula los resultados
     * de cada ejecución del benchmark.
     */
    private static class BenchmarkResult {
        String name;
        int size;
        String scenario;
        long timeMs;

        BenchmarkResult(String name, int size, String scenario, long timeMs) {
            this.name = name;
            this.size = size;
            this.scenario = scenario;
            this.timeMs = timeMs;
        }
    }
}


