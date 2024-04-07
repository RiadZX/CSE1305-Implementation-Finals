import java.util.*;

class Solution {
  /**
   * Sorts a list of words using MSD radix sort.
   *
   * @param words
   *     The list of words to sort.
   * @return The sorted list of words.
   * @throws NullPointerException
   *     If `words` equals `null`.
   */
  static List<String> radixSortMSD(List<String> words) {
    // TODO
    if (words == null) throw new NullPointerException();
    if (words.size() < 2) return words;
    return radixSortMSD(words, 0);
  }

  @SuppressWarnings({"unchecked"})
  static List<String> radixSortMSD(List<String> words, int radix) {

    List<String> sorted = new LinkedList<>();
    List<String>[] buckets = new LinkedList[26];

    for(int i = 0; i < 26; i++) {
      buckets[i] = new LinkedList<>();
    }

    for (String word : words) {
      if (word.length() <= radix) {
        sorted.add(word); 
      } else {
        int index = (int) word.charAt(radix) - 'a';
        buckets[index].add(word);
      }
    }

    if(sorted.size() != words.size()) {
      for (List<String> bucket : buckets) {
        if (!bucket.isEmpty()) sorted.addAll(radixSortMSD(bucket, radix+1));
      }
    }

    return sorted;
  }
}