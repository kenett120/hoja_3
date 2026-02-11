package org.example.sorts;

import java.util.List;

/**
 * Implementación del algoritmo Insertion Sort.
 *
 * Insertion Sort es un algoritmo de ordenamiento basado en comparación
 * que construye la lista ordenada de manera incremental. En cada iteración,
 * toma un elemento y lo inserta en la posición correcta dentro de la parte
 * ya ordenada de la lista.
 *
 * Es eficiente para listas pequeñas o parcialmente ordenadas,
 * pero poco eficiente para grandes volúmenes de datos.
 *
 * Complejidad:
 * - Mejor caso: O(n)
 * - Caso promedio: O(n^2)
 * - Peor caso: O(n^2)
 *
 * @param <T> tipo de dato que implementa la interfaz Comparable
 *
 * @author Joao
 * @version 1.0
 */
public class InsertionSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    /**
     * Ordena la lista utilizando el algoritmo Insertion Sort.
     *
     * Recorre la lista desde la segunda posición, tomando cada elemento
     * y desplazando los elementos mayores hacia la derecha hasta encontrar
     * la posición correcta para insertarlo.
     *
     * @param list lista de elementos comparables a ordenar
     */
    @Override
    public void sort(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}


