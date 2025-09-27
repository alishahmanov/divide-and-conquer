package assignment1;

public class QuickSort implements SortInterface {
    @Override
    public void sort(int[] arr, Counter counter) {
        if (SortUtils.isInvalid(arr)) return;
        quickSort(arr, 0, arr.length - 1, counter);
    }

    private void quickSort(int[] arr, int left, int right, Counter counter) {
        counter.startOfRecursion();

        while (left < right) {
            int pivotIndex = SortUtils.randomPivot(left, right);
            int pivotNewIndex = SortUtils.partition(arr, left, right, pivotIndex, counter);

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
}
