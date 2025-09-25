package assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    @Test
    public void testRandomArray() {
        int[] arr = {2, 4, 3, 1, 4, 9};
        Counter counter = new Counter();
        SortInterface sorter = new MergeSort();
        sorter.sort(arr, counter);
        assertArrayEquals(new int[]{1, 2, 3, 4, 4, 9}, arr);
    }

    @Test
    public void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        Counter counter = new Counter();
        SortInterface sorter = new MergeSort();
        sorter.sort(arr, counter);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testSameElements() {
        int[] arr = {7, 7, 7, 7};
        Counter counter = new Counter();
        SortInterface sorter = new MergeSort();
        sorter.sort(arr, counter);
        assertArrayEquals(new int[]{7, 7, 7, 7}, arr);
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        Counter counter = new Counter();
        SortInterface sorter = new MergeSort();
        sorter.sort(arr, counter);
        assertArrayEquals(new int[]{}, arr);
    }
}
