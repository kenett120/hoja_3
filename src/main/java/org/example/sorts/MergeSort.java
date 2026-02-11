package org.example.sorts;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del algoritmo Merge Sort.
 *
 * Merge Sort es un algoritmo de ordenamiento basado en el paradigma
 * divide y vencerás. Divide la lista en dos mitades, las ordena
 * recursivamente y luego combina los resultados en una lista final ordenada.
 *
 * Es un algoritmo estable y eficiente para grandes volúmenes de datos.
 *
 * Complejidad:
 * - Mejor caso: O(n log n)
 * - Caso promedio: O(n log n)
 * - Peor caso: O(n log n)
 *
 * Espacio adicional: O(n)
 *
 * @param <T> tipo de dato que implementa la interfaz Comparable
 *
 * @author Kenett
 * @version 1.0
 */
public class MergeSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    /**
     * Ordena la lista utilizando el algoritmo Merge Sort.
     *
     * Si la lista tiene uno o ningún elemento, ya está ordenada.
     * En caso contrario, se divide en dos sublistas, se ordenan
     * recursivamente y luego se combinan.
     *
     * @param list lista de elementos comparables a ordenar
     */
    @Override
    public void sort(List<T> list) {
        if (list.size() <= 1) return;

        int mid = list.size() / 2;
        List<T> left = new ArrayList<>(list.subList(0, mid));
        List<T> right = new ArrayList<>(list.subList(mid, list.size()));

        sort(left);
        sort(right);

        merge(list, left, right);
    }

    /**
     * Combina dos listas previamente ordenadas en una sola lista ordenada.
     *
     * @param result lista donde se almacenará el resultado combinado
     * @param left sublista izquierda ordenada
     * @param right sublista derecha ordenada
     */
    private void merge(List<T> result, List<T> left, List<T> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) result.set(k++, left.get(i++));
        while (j < right.size()) result.set(k++, right.get(j++));
    }
}
