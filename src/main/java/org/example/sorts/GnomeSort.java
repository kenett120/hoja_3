package org.example.sorts;

import java.util.List;

/**
 * Implementación del algoritmo Gnome Sort.
 *
 * Gnome Sort es un algoritmo de ordenamiento basado en comparación,
 * similar a Insertion Sort. Funciona intercambiando elementos
 * adyacentes que estén fuera de orden, retrocediendo en la lista
 * hasta que cada elemento quede ubicado correctamente.
 *
 * Es sencillo de implementar, pero no es eficiente para grandes
 * volúmenes de datos.
 *
 * Complejidad:
 * - Mejor caso: O(n)
 * - Caso promedio: O(n^2)
 * - Peor caso: O(n^2)
 *
 * @param <T> tipo de dato que implementa la interfaz Comparable
 *
 * @author Kenett
 * @version 1.0
 */
public class GnomeSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    /**
     * Ordena la lista utilizando el algoritmo Gnome Sort.
     *
     * El método recorre la lista comparando elementos adyacentes.
     * Si están en el orden correcto, avanza; si no, los intercambia
     * y retrocede hasta que el elemento quede en la posición adecuada.
     *
     * @param list lista de elementos comparables a ordenar
     */
    @Override
    public void sort(List<T> list) {
        int index = 0;

        while (index < list.size()) {
            if (index == 0) {
                index++;
            } else if (list.get(index).compareTo(list.get(index - 1)) >= 0) {
                index++;
            } else {
                T temp = list.get(index);
                list.set(index, list.get(index - 1));
                list.set(index - 1, temp);
                index--;
            }
        }
    }
}

