package org.example.sorts;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Test suite for RadixSort.
 * Note: RadixSort only works with positive integers, so tests are tailored accordingly.
 */
public class RadixSortTest {

    @Test
    public void testEmptyList() {
        RadixSort sorter = new RadixSort();
        List<Integer> list = new ArrayList<>();
        sorter.sort(list);
        assertTrue("Empty list should remain empty", list.isEmpty());
    }

    @Test
    public void testSingleElement() {
        RadixSort sorter = new RadixSort();
        List<Integer> list = new ArrayList<>(Arrays.asList(42));
        sorter.sort(list);
        assertEquals("Single element list should remain unchanged", Arrays.asList(42), list);
    }

    @Test
    public void testAlreadySorted() {
        RadixSort sorter = new RadixSort();
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        sorter.sort(list);
        assertEquals("Already sorted list should remain sorted", Arrays.asList(1, 2, 3, 4, 5), list);
    }

    @Test
    public void testReverseSorted() {
        RadixSort sorter = new RadixSort();
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        sorter.sort(list);
        assertEquals("Reverse sorted list should be sorted correctly", Arrays.asList(1, 2, 3, 4, 5), list);
    }

    @Test
    public void testWithDuplicates() {
        RadixSort sorter = new RadixSort();
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5));
        sorter.sort(list);
        assertEquals("List with duplicates should be sorted correctly",
                     Arrays.asList(1, 1, 2, 3, 4, 5, 5, 6, 9), list);
    }

    @Test
    public void testRandomList() {
        RadixSort sorter = new RadixSort();
        List<Integer> list = new ArrayList<>(Arrays.asList(64, 34, 25, 12, 22, 11, 90));
        sorter.sort(list);
        assertEquals("Random list should be sorted correctly",
                     Arrays.asList(11, 12, 22, 25, 34, 64, 90), list);
    }

    @Test
    public void testAllSameElements() {
        RadixSort sorter = new RadixSort();
        List<Integer> list = new ArrayList<>(Arrays.asList(7, 7, 7, 7, 7));
        sorter.sort(list);
        assertEquals("List with all same elements should remain unchanged",
                     Arrays.asList(7, 7, 7, 7, 7), list);
    }

    @Test
    public void testTwoElements() {
        RadixSort sorter = new RadixSort();
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 1));
        sorter.sort(list);
        assertEquals("Two element list should be sorted correctly", Arrays.asList(1, 2), list);
    }

    @Test
    public void testLargeNumbers() {
        RadixSort sorter = new RadixSort();
        List<Integer> list = new ArrayList<>(Arrays.asList(170, 45, 75, 90, 802, 24, 2, 66));
        sorter.sort(list);
        assertEquals("List with large numbers should be sorted correctly",
                     Arrays.asList(2, 24, 45, 66, 75, 90, 170, 802), list);
    }

    @Test
    public void testLargeList() {
        RadixSort sorter = new RadixSort();
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
