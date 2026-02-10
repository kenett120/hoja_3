package org.example.sorts;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> implements SortAlgorithm<T> {

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

