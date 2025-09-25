package assignment1;

public class MergeSort implements SortInterface {
    private static final int CUTOFF = 16;
    @Override
    public void sort(int[] arr, Counter counter) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int [] temp = new int[arr.length];
        counter.increaseMemory();
        sort(arr, temp, 0, arr.length - 1, counter);
    }

    private void sort(int[] arr, int[] temp, int left, int right, Counter counter) {
        counter.startOfRecursion();
        if (right - left < CUTOFF) {
            insertionSort(arr,left,right,counter);
            counter.endOfRecursion();
            return;
        }
        int mid = (left + right) / 2;

        sort(arr, temp, left, mid, counter);
        sort(arr, temp, mid + 1, right, counter);

        merge(arr, temp, left, mid, right, counter);
        counter.endOfRecursion();
    }
    private void merge(int[] arr, int[] temp, int left, int mid, int right, Counter counter) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            counter.increaseCompare();
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }


            counter.increaseMemory();
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
            counter.increaseMemory();
        }
        while (j <= right) {
            temp[k++] = arr[j++];
            counter.increaseMemory();
        }
        for (int q = left; q <= right; q++) {
            arr[q] = temp[q];
        }
    }
    private void insertionSort(int[] arr, int left, int right, Counter counter) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left) {
                counter.increaseCompare();
                if (arr[j] > key) {
                    arr[j+1] = arr[j];
                    counter.increaseMemory();
                    j--;
                } else {
                    break;
                }
            }
            arr[j+1] = key;
            counter.increaseMemory();

        }
    }
}