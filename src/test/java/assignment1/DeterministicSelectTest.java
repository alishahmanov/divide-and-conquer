package assignment1;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    public void testSingleElement() {
        int[] arr = {42};
        Counter counter = new Counter();
        DeterministicSelect ds = new DeterministicSelect();
        assertEquals(42, ds.select(arr, 0, counter));
    }

    @Test
    public void testSmallArray() {
        int[] arr = {7, 1, 5};
        Counter counter = new Counter();
        DeterministicSelect ds = new DeterministicSelect();

        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);

        for (int k = 0; k < arr.length; k++) {
            assertEquals(sorted[k], ds.select(Arrays.copyOf(arr, arr.length), k, counter));
        }
    }
    @Test
    public void testAllEqual() {
        int[] arr = {9, 9, 9, 9};
        Counter counter = new Counter();
        DeterministicSelect ds = new DeterministicSelect();
        assertEquals(9, ds.select(arr, 2, counter));
    }

    @Test
    public void testRandomArray() {
        int[] arr = {8, 3, 7, 1, 5, 9};
        Counter counter = new Counter();
        DeterministicSelect ds = new DeterministicSelect();
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);

        for (int k = 0; k < arr.length; k++) {
            assertEquals(sorted[k], ds.select(Arrays.copyOf(arr, arr.length), k, counter));
        }
    }

    @Test
    public void testLargeArray() {
        int[] arr = new Random().ints(2000, 0, 10000).toArray();
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);

        Counter counter = new Counter();
        DeterministicSelect ds = new DeterministicSelect();

        assertEquals(sorted[1234], ds.select(Arrays.copyOf(arr, arr.length), 1234, counter));
    }
}
