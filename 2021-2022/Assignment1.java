import java.util.*;

public class Solution {

    /**
     * Counts the number of occurrences of substring of length k in string s.
     *
     * @param s
     *     The String in which to look for substrings.
     * @param k
     *     The length of the substrings to look for.
     * @return Map with entries in the form of (sub, numOcc), where sub denotes a substring with numOcc occurrences in s.
     * Note: There should be no entries in the map for substrings that do not occur in string s.
     */
    public static Map<String, Integer> countSubstringOccurrences(String s, int k) {
        // TODO
        Map<String, Integer> result = new HashMap<>();
        int i = 0;
        int j = k;

        while (j < s.length()+1) {
            String substring = s.substring(i, j);
            int occurence = 1;
            if(result.containsKey(substring)){
                occurence += result.get(substring);
            }

            result.put(substring, occurence);

            i++;
            j++;
        }

        return result;
    }

    public static void print(Map<String, Integer> map) {
        Set<String> keyset = map.keySet();
        for(String key : keyset) {
            System.out.print("Key: " + key + " - Value: " +map.get(key) + "\n");
        }
    }

    /**
     * Counts the occurrences of substrings (of **all lengths**) in a given string s.
     * Should make use of the method countSubstringOccurrences(String s, int k).
     *
     * @param s
     *     The String in which to look for substrings.
     * @return Map with entries in the form of (sub, numOcc), where sub denotes a substring with numOcc occurrences in s.
     * Note 1: There should be no entries in the map for substrings that do not occur in string s.
     * Note 2: When iterating through the returned map, the substrings should be found in increasing order of their length.
     */
    public static Map<String, Integer> countSubstringOccurrences(String s) {
        // TODO
        Map<String, Integer> result = new LinkedHashMap<>();
        for(int k = 1; k <= s.length(); k++) {
            result.putAll(countSubstringOccurrences(s, k));
        }

        print(result);
        return result;
    }

    /**
     * Simplifies a given map of substrings and their occurrences by removing substrings that do not repeat.
     * Must be implemented in-place.
     *
     * @param substringsMap
     *     The map from which substrings that do not repeat (occur only once) are to be removed.
     */
    public static void repeatedSubstrings(Map<String, Integer> substringsMap) {
        substringsMap.values().removeIf(val -> 1 == val);
    }
}
