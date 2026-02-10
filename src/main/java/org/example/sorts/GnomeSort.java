package org.example.sorts;

import java.util.List;

public class GnomeSort<T extends Comparable<T>> implements SortAlgorithm<T> {

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

