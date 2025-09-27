package assignment1;

import java.util.Random;

public class QuickSort implements SortInterface {
    private final Random random = new Random();

    @Override
    public void sort(int[] arr, Counter counter) {
        if (arr == null || arr.length == 0) return;
        quickSort(arr, 0, arr.length - 1, counter);
    }

    private void quickSort(int[] arr, int left, int right, Counter counter) {
        counter.startOfRecursion();

        while (left < right) {
            int pivotIndex = left + random.nextInt(right - left + 1);
            int pivotNewIndex = partition(arr, left, right, pivotIndex, counter);

            if (pivotNewIndex - left < right - pivotNewIndex) {
                quickSort(arr, left, pivotNewIndex - 1, counter);
                left = pivotNewIndex + 1;
            } else {
                quickSort(arr, pivotNewIndex + 1, right, counter);
                right = pivotNewIndex - 1;
            }
        }

        counter.endOfRecursion();
    }

    private int partition(int[] arr, int left, int right, int pivotIndex, Counter counter) {
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

    private void swap(int[] arr, int i, int j, Counter counter) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        counter.increaseMemory();
    }
}
