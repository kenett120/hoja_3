package org.example.sorts;

import java.util.List;

/**
 * Implementación del algoritmo Radix Sort.
 *
 * Radix Sort es un algoritmo de ordenamiento no comparativo
 * que ordena números enteros procesando cada dígito de manera
 * individual, comenzando desde el dígito menos significativo.
 *
 * Esta implementación utiliza Counting Sort como subrutina
 * para ordenar los elementos según cada posición decimal.
 *
 * Nota: Este algoritmo no utiliza la interfaz Comparable,
 * ya que no se basa en comparaciones entre elementos,
 * sino en el análisis de sus dígitos.
 *
 * Complejidad:
 * - Mejor caso: O(nk)
 * - Caso promedio: O(nk)
 * - Peor caso: O(nk)
 *
 * Donde:
 * n = número de elementos
 * k = número de dígitos del número mayor
 *
 * Espacio adicional: O(n + k)
 *
 * @author Kenett
 * @version 1.0
 */
public class RadixSort {

    /**
     * Ordena una lista de números enteros utilizando Radix Sort.
     *
     * Si la lista es nula o está vacía, el método no realiza ninguna acción.
     *
     * @param list lista de números enteros a ordenar
     */
    public void sort(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        int max = getMax(list);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(list, exp);
        }
    }

    /**
     * Obtiene el valor máximo dentro de la lista.
     *
     * @param list lista de números enteros
     * @return el número máximo encontrado
     */
    private int getMax(List<Integer> list) {
        int max = list.get(0);
        for (int num : list) {
            if (num > max) max = num;
        }
        return max;
    }

    /**
     * Aplica Counting Sort basado en el dígito representado
     * por el exponente actual.
     *
     * @param list lista de números enteros a ordenar
     * @param exp exponente que representa la posición decimal actual
     */
    private void countingSort(List<Integer> list, int exp) {
        int[] output = new int[list.size()];
        int[] count = new int[10];

        for (int num : list) {
            count[(num / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            int num = list.get(i);
            output[count[(num / exp) % 10] - 1] = num;
            count[(num / exp) % 10]--;
        }

        for (int i = 0; i < list.size(); i++) {
            list.set(i, output[i]);
        }
    }
}
