package org.example.sorts;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Contract test that defines the expected behavior for all SortAlgorithm implementations.
 * Any class implementing SortAlgorithm should pass all these tests.
 */
public abstract class SortAlgorithmContract {

    /**
     * Subclasses must provide the specific SortAlgorithm implementation to test
     */
    protected abstract <T extends Comparable<T>> SortAlgorithm<T> createSortAlgorithm();

    @Test
    public void testEmptyList() {
        SortAlgorithm<Integer> sorter = createSortAlgorithm();
        List<Integer> list = new ArrayList<>();
        sorter.sort(list);
        assertTrue("Empty list should remain empty", list.isEmpty());
    }

    @Test
    public void testSingleElement() {
        SortAlgorithm<Integer> sorter = createSortAlgorithm();
        List<Integer> list = new ArrayList<>(Arrays.asList(42));
        sorter.sort(list);
        assertEquals("Single element list should remain unchanged", Arrays.asList(42), list);
    }

    @Test
    public void testAlreadySorted() {
        SortAlgorithm<Integer> sorter = createSortAlgorithm();
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        sorter.sort(list);
        assertEquals("Already sorted list should remain sorted", Arrays.asList(1, 2, 3, 4, 5), list);
    }

    @Test
    public void testReverseSorted() {
        SortAlgorithm<Integer> sorter = createSortAlgorithm();
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        sorter.sort(list);
        assertEquals("Reverse sorted list should be sorted correctly", Arrays.asList(1, 2, 3, 4, 5), list);
    }

    @Test
    public void testWithDuplicates() {
        SortAlgorithm<Integer> sorter = createSortAlgorithm();
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5));
        sorter.sort(list);
        assertEquals("List with duplicates should be sorted correctly",
                     Arrays.asList(1, 1, 2, 3, 4, 5, 5, 6, 9), list);
    }

    @Test
    public void testRandomList() {
        SortAlgorithm<Integer> sorter = createSortAlgorithm();
        List<Integer> list = new ArrayList<>(Arrays.asList(64, 34, 25, 12, 22, 11, 90));
        sorter.sort(list);
        assertEquals("Random list should be sorted correctly",
                     Arrays.asList(11, 12, 22, 25, 34, 64, 90), list);
    }

    @Test
    public void testNegativeNumbers() {
        SortAlgorithm<Integer> sorter = createSortAlgorithm();
        List<Integer> list = new ArrayList<>(Arrays.asList(-5, 3, -1, 0, 8, -3));
        sorter.sort(list);
        assertEquals("List with negative numbers should be sorted correctly",
                     Arrays.asList(-5, -3, -1, 0, 3, 8), list);
    }

    @Test
    public void testAllSameElements() {
        SortAlgorithm<Integer> sorter = createSortAlgorithm();
        List<Integer> list = new ArrayList<>(Arrays.asList(7, 7, 7, 7, 7));
        sorter.sort(list);
        assertEquals("List with all same elements should remain unchanged",
                     Arrays.asList(7, 7, 7, 7, 7), list);
    }

    @Test
    public void testTwoElements() {
        SortAlgorithm<Integer> sorter = createSortAlgorithm();
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 1));
        sorter.sort(list);
        assertEquals("Two element list should be sorted correctly", Arrays.asList(1, 2), list);
    }

    @Test
    public void testStrings() {
        SortAlgorithm<String> sorter = createSortAlgorithm();
        List<String> list = new ArrayList<>(Arrays.asList("dog", "cat", "bird", "ant"));
        sorter.sort(list);
        assertEquals("String list should be sorted alphabetically",
                     Arrays.asList("ant", "bird", "cat", "dog"), list);
    }

    @Test
    public void testLargeList() {
        SortAlgorithm<Integer> sorter = createSortAlgorithm();
        List<Integer> list = new ArrayList<>();
        // Create a list of 100 elements in descending order
        for (int i = 100; i > 0; i--) {
            list.add(i);
        }
        sorter.sort(list);
        // Verify it's now in ascending order
        for (int i = 0; i < 100; i++) {
            assertEquals("Element at position " + i + " should be " + (i + 1),
                        Integer.valueOf(i + 1), list.get(i));
        }
    }
}
