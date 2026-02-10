package org.example.sorts;

import java.util.List;

public class RadixSort {

    public void sort(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        int max = getMax(list);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(list, exp);
        }
    }

    private int getMax(List<Integer> list) {
        int max = list.get(0);
        for (int num : list) {
            if (num > max) max = num;
        }
        return max;
    }

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

