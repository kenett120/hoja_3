package org.example.sorts;

/**
 * Test suite for InsertionSort that verifies it complies with the SortAlgorithm contract.
 */
public class InsertionSortTest extends SortAlgorithmContract {

    @Override
    protected <T extends Comparable<T>> SortAlgorithm<T> createSortAlgorithm() {
        return new InsertionSort<>();
    }
}
