package assignment1;

import java.util.Random;

public class SortUtils {
    private static final Random random = new Random();

    public static void swap(int[] arr, int i, int j, Counter counter) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        counter.increaseMemory();
    }

    public static int partition(int[] arr, int left, int right, int pivotIndex, Counter counter) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right, counter);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            counter.increaseCompare();
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex, counter);
                storeIndex++;
            }
        }

        swap(arr, storeIndex, right, counter);
        return storeIndex;
    }

    public static int randomPivot(int left, int right) {
        return left + random.nextInt(right - left + 1);
    }

    public static boolean isInvalid(int[] arr) {
        return arr == null || arr.length == 0;
    }
}
