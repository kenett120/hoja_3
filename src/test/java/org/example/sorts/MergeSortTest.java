package org.example.sorts;

/**
 * Test suite for MergeSort that verifies it complies with the SortAlgorithm contract.
 */
public class MergeSortTest extends SortAlgorithmContract {

    @Override
    protected <T extends Comparable<T>> SortAlgorithm<T> createSortAlgorithm() {
        return new MergeSort<>();
    }
}
