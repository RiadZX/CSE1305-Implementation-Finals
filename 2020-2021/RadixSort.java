import java.util.*;

class Solution {

  /**
   * Sorts the list of student IDs as defined in the description.
   * @param studentIds - list of student IDs
   * @return sorted list of student IDs
   */
  static List<String> sortStudentId(List<String> studentIds) {
  // TODO
    if (studentIds == null) return null;
    if (studentIds.size() < 2) return studentIds;
    for (int exp = 5; exp >= 0; exp--) {
        studentIds = countingSort(studentIds, exp);
    }
    Collections.reverse(studentIds);
    return studentIds;
  }

  public static List<String> countingSort(List<String> arr, int exp) {
    int n = arr.size();
    List<String> output = new LinkedList<>();
    for (int i = 0; i < n; i++) output.add(null);
    int[] count = new int[256];

    // Store count of occurrences in count[]
    for (int i = 0; i < n; i++) {
      int index;
      if (exp == 1 || exp == 3) {
        index = (int) arr.get(i).charAt(exp);
      } else {
        index = (int) arr.get(i).charAt(exp) - 'A';
      }
      count[index]++;
    }

    // Change count[i] so that count[i] now contains actual
    // position of this character in output list
    for (int i = 1; i < 256; i++) {
      count[i] += count[i - 1];
    }

    // Build the output list
    for (int i = n - 1; i >= 0; i--) {
      int index;
      if (exp == 1 || exp == 3) {
        index = (int) arr.get(i).charAt(exp);
      } else {
        index = (int) arr.get(i).charAt(exp) - 'A';
      }
      output.set(count[index] - 1, arr.get(i));
      count[index]--;
    }

    return output;
  }
}