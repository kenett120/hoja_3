package org.example.sorts;

import java.util.List;

/**
 * Implementación del algoritmo Quick Sort.
 *
 * Quick Sort es un algoritmo de ordenamiento basado en el paradigma
 * divide y vencerás. Selecciona un pivote, particiona la lista en
 * elementos menores y mayores que el pivote, y aplica el proceso
 * recursivamente a cada sublista.
 *
 * Esta implementación utiliza la estrategia "median-of-three"
 * para seleccionar el pivote, reduciendo la probabilidad de
 * alcanzar el peor caso cuando la lista ya está ordenada.
 *
 * Complejidad:
 * - Mejor caso: O(n log n)
 * - Caso promedio: O(n log n)
 * - Peor caso: O(n^2)
 *
 * Espacio adicional: O(log n) debido a la recursión.
 *
 * @param <T> tipo de dato que implementa la interfaz Comparable
 *
 * @author Joao
 * @version 1.0
 */
public class QuickSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    /**
     * Ordena la lista utilizando el algoritmo Quick Sort.
     *
     * @param list lista de elementos comparables a ordenar
     */
    @Override
    public void sort(List<T> list) {
        quickSort(list, 0, list.size() - 1);
    }

    /**
     * Método auxiliar recursivo que aplica Quick Sort
     * sobre un rango específico de la lista.
     *
     * @param list lista a ordenar
     * @param low índice inicial del rango
     * @param high índice final del rango
     */
    private void quickSort(List<T> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    /**
     * Realiza la partición de la lista utilizando el método
     * median-of-three para seleccionar el pivote.
     *
     * @param list lista a particionar
     * @param low índice inicial
     * @param high índice final
     * @return índice final del pivote después de la partición
     */
    private int partition(List<T> list, int low, int high) {
        int mid = low + (high - low) / 2;

        if (list.get(mid).compareTo(list.get(low)) < 0) {
            swap(list, low, mid);
        }
        if (list.get(high).compareTo(list.get(low)) < 0) {
            swap(list, low, high);
        }
        if (list.get(high).compareTo(list.get(mid)) < 0) {
            swap(list, mid, high);
        }

        swap(list, mid, high);

        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    /**
     * Intercambia dos elementos dentro de la lista.
     *
     * @param list lista donde se realizará el intercambio
     * @param i índice del primer elemento
     * @param j índice del segundo elemento
     */
    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}


