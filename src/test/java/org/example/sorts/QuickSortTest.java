package org.example.sorts;

/**
 * Test suite for QuickSort that verifies it complies with the SortAlgorithm contract.
 */
public class QuickSortTest extends SortAlgorithmContract {

    @Override
    protected <T extends Comparable<T>> SortAlgorithm<T> createSortAlgorithm() {
        return new QuickSort<>();
    }
}
