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
  // TODO
    if (arr == null ) return null;
    if (arr.length == 0) return new int[0];
    if (arr.length == 1) return new int[]{0};
    int[] indexArray = new int[arr.length];

    for (int i = 0; i < arr.length; i++) {
      indexArray[i] = i;
    }

    for(int i = 1; i < arr.length; i++) {
      int j = i;
      while (j > 0 && arr[j].compareTo(arr[j-1]) < 1) {
        swap(arr, j, j-1);
        swap(indexArray, j, j-1);
        j--;
      }
    }
    return indexArray;
  }

  public static void swap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  public static void swap(String[] arr, int a, int b) {
    String temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }
}