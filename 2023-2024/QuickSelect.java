import java.util.*;

class QuickSelect {

    /**
     * Creates a random partition in a subarray of the input array in-place
     * and returns the index of the pivot.
     * You may assume all items in arr are distinct.
     *
     * @param arr Array to partition.
     * @param left Lower bound of the subarray to partition (inclusive).
     * @param right Upper bound of the subarray to partition (inclusive).
     * @return The index of the pivot after partitioning the array.
     */
    public int partition(int[] arr, int start, int end) {
        // TODO
        if (start >= end) {
            return start;
        } else {
            Random rand = new Random();
            int pivotIndex = rand.nextInt(start, end);
            swap(arr, end, pivotIndex);

            int pivot = arr[end];
            int left = start;
            int right = end-1;

            while(left <= right) {
                if(arr[right] > pivot) {
                    right--;
                } else {
                    swap(arr, left, right);
                    left++;
                }
            }

            swap(arr, left, end);
            return left;
        }

    }

    /**
     * Performs in-place quick select to return the k'th smallest number in arr.
     *
     * @param arr Array to find number in.
     * @param k K'th smallest number must be returned.
     * @return The k'th smallest number in arr.
     */
    public int quickSelect(int[] arr, int k) {
        // TODO
        return quickSelect(arr, k, 0, arr.length-1);
    }

    public int quickSelect(int[] arr, int k, int start, int end) {
        int pivotIndex = partition(arr, start, end);

        if (pivotIndex == k-1) {
            return arr[pivotIndex];
        } else if (pivotIndex < k-1) {
            return quickSelect(arr, k, pivotIndex+1, end);
        } else {
            return quickSelect(arr, k, start, pivotIndex-1);
        }
    }

    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
