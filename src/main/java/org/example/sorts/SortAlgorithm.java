package org.example.sorts;

import java.util.List;

/**
 * Interfaz que define el contrato para los algoritmos
 * de ordenamiento basados en comparación.
 *
 * Todas las clases que implementen esta interfaz deben
 * proporcionar una implementación del método sort,
 * capaz de ordenar una lista cuyos elementos implementen
 * la interfaz Comparable.
 *
 * @param <T> tipo de dato que implementa Comparable
 *
 * @author Joao
 * @version 1.0
 */
public interface SortAlgorithm<T extends Comparable<T>> {

    /**
     * Ordena la lista recibida utilizando el algoritmo
     * específico implementado por la clase.
     *
     * @param list lista de elementos comparables a ordenar
     */
    void sort(List<T> list);
}

