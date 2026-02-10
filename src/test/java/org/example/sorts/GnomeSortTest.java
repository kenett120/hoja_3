package org.example.sorts;

/**
 * Test suite for GnomeSort that verifies it complies with the SortAlgorithm contract.
 */
public class GnomeSortTest extends SortAlgorithmContract {

    @Override
    protected <T extends Comparable<T>> SortAlgorithm<T> createSortAlgorithm() {
        return new GnomeSort<>();
    }
}
