package assignment1;

import java.util.Arrays;

public class DeterministicSelect {

    public int select(int[] arr, int k, Counter counter) {
        if (SortUtils.isInvalid(arr) || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Некорректные входные данные");
        }
        return selectRecursive(arr, 0, arr.length - 1, k, counter);
    }

    private int selectRecursive(int[] arr, int left, int right, int k, Counter counter) {
        counter.startOfRecursion();

        while (true) {
            if (left == right) {
                counter.endOfRecursion();
                return arr[left];
            }

            int pivotIndex = medianOfMedians(arr, left, right, counter);

            int pivotNewIndex = SortUtils.partition(arr, left, right, pivotIndex, counter);

            if (k == pivotNewIndex) {
                counter.endOfRecursion();
                return arr[k];
            } else if (k < pivotNewIndex) {
                right = pivotNewIndex - 1;
            } else {
                left = pivotNewIndex + 1;
            }
        }
    }

    private int medianOfMedians(int[] arr, int left, int right, Counter counter) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            counter.increaseMemory();
            return left + n / 2;
        }

        int numMedians = (int) Math.ceil((double) n / 5);
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);

            Arrays.sort(arr, subLeft, subRight + 1);
            counter.increaseMemory();

            int medianIndex = subLeft + (subRight - subLeft) / 2;
            SortUtils.swap(arr, left + i, medianIndex, counter);
        }

        return medianOfMedians(arr, left, left + numMedians - 1, counter);
    }
}
