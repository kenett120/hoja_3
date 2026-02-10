package org.example.sorts;

import java.util.List;

public interface SortAlgorithm<T extends Comparable<T>> {
    void sort(List<T> list);
}
