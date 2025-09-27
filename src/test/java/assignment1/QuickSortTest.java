package assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class QuickSortTest {

    @Test
    public void testRandomArray() {
        int[] arr = {1, 3, 3, 2, 5};
        Counter counter = new Counter();
        SortInterface sorter = new QuickSort();
        sorter.sort(arr, counter);
        assertArrayEquals(new int[]{1, 2, 3, 3, 5}, arr);
    }

    @Test
    public void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        Counter counter = new Counter();
        SortInterface sorter = new QuickSort();
        sorter.sort(arr, counter);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testSameElements() {
        int[] arr = {7, 7, 7, 7};
        Counter counter = new Counter();
        SortInterface sorter = new QuickSort();
        sorter.sort(arr, counter);
        assertArrayEquals(new int[]{7, 7, 7, 7}, arr);
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        Counter counter = new Counter();
        SortInterface sorter = new QuickSort();
        sorter.sort(arr, counter);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testLargeArray() {
        int[] arr = new Random().ints(1000, 0, 10000).toArray();
        int[] expected = Arrays.copyOf(arr, arr.length);

        Counter counter = new Counter();
        SortInterface sorter = new QuickSort();
        sorter.sort(arr, counter);

        Arrays.sort(expected);
        assertArrayEquals(expected, arr);
    }
}
