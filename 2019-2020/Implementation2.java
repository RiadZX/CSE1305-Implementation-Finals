package weblab;

class Solution {

  /**
   * Sorts the indices of the array based on the corresponding value in alphabetical order.
   * Returns null if the input array is null.
   *
   * Example: The array ["c","a","b"] will result in [1, 2, 0].
   *
   * @param arr array of Strings that stored the values
   * @return the indices in sorted order
   */
  public static int[] indexSort(String[] arr) {
      if(arr==null) return null;
      int[] indices = new int[arr.length];
      
      for(int i=0;i<indices.length;i++){
        indices[i]=i;
      }

      for (int i = 1; i < indices.length; i++) {
        // store the current index and value
        int index = indices[i];
        String value = arr[index];
        // find the correct position for the current index in the sorted subarray
        int j = i - 1;
        while (j >= 0 && arr[indices[j]].compareTo(value) > 0) {
            // shift the larger indices to the right
            indices[j + 1] = indices[j];
            j--;
        }
        // insert the current index at the correct position
        indices[j + 1] = index;
        }

  return indices;
}
}
