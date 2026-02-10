package org.example.sorts;

import java.util.List;

public class QuickSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    @Override
    public void sort(List<T> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private void quickSort(List<T> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private int partition(List<T> list, int low, int high) {
        // Median-of-three pivot selection to avoid O(n²) worst case on sorted data
        int mid = low + (high - low) / 2;

        // Sort low, mid, high so median ends up in mid position
        if (list.get(mid).compareTo(list.get(low)) < 0) {
            swap(list, low, mid);
        }
        if (list.get(high).compareTo(list.get(low)) < 0) {
            swap(list, low, high);
        }
        if (list.get(high).compareTo(list.get(mid)) < 0) {
            swap(list, mid, high);
        }

        // Move median to high position to use as pivot
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

    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

