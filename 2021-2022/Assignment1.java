package weblab;

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
        Map<String, Integer> m = new HashMap<>();
        for(int i =0; i < s.length();i++){
            int endIndex = i+k;
            if(endIndex>s.length()) continue;
            String substring = s.substring(i,endIndex);
            
            if(m.get(substring)==null){
                m.put(substring,1);
            }else{
                m.put(substring,m.get(substring)+1);
            }
        }
        return m;
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
        Map<String, Integer> m = new LinkedHashMap <>();
        for(int i =0; i <= s.length(); i++){
            //i is the length.
            for(int j = 0; j < s.length(); j++){
                int length = i;
                //System.out.println("length: " + length);
                if(j+i>s.length()) continue;
                String substring = s.substring(j,j+i);
                Map<String, Integer> r = countSubstringOccurrences(substring,length);
                //so now i have the map for each length, each string.
                //add it to the main map.
                for(String k : r.keySet()){
                    System.out.println(k);
                    if(m.get(k)==null){
                        m.put(k,r.get(k));
                    }else{
                        m.put(k,r.get(k)+m.get(k));
                    }
                    
                }
            }
        }


        return m;
    }

    /**
     * Simplifies a given map of substrings and their occurrences by removing substrings that do not repeat.
     * Must be implemented in-place.
     *
     * @param substringsMap
     *     The map from which substrings that do not repeat (occur only once) are to be removed.
     */
    public static void repeatedSubstrings(Map<String, Integer> substringsMap) {
        Set<String> k =  substringsMap.keySet();
        List<String> a = new ArrayList<>();

        for(String s : k){
            a.add(s);
        }

        for(int i =0; i < a.size();i++){
            if(substringsMap.get(a.get(i))==1){
                substringsMap.remove(a.get(i));
            }
        }
    }
}
