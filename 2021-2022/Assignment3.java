import java.util.*;

public class Solution {

    /**
     * Sorts and merges `in[start, ..., start + inc - 1]` and `in[start + inc, ..., start + 2*inc - 1]` into `out`.
     *
     * @param in The input array, sorted in their intervals `in[start, ..., start + inc - 1]` and `in[start + inc, ..., start + 2*inc - 1]`.
     * @param out The output array that should be updated to have `in[start, ..., start + inc - 1]` and `in[start + inc, ..., start + 2*inc - 1]`
     *            in sorted fashion at `out[start, start + 2*inc - 1]`.
     * @param start The index of the first element to be merged into out.
     * @param inc The size of each of the intervals to be merged into out.
     */
    public static void merge(int[] in, int[] out, int start, int inc) {
        // TODO
        int end1 = Math.min(start+inc, in.length);
        int end2 = Math.min(start+2*inc, in.length);

        int x = start;
        int y = start+inc;
        int z = start;

        while(x < end1 && y < end2) {
            if (in[x] < in[y]) {
                out[z++] = in[x++];
            } else {
                out[z++] = in[y++];
            }
        }

        while(x < end1) {
            out[z++] = in[x++];
        }

        while(y < end2) {
            out[z++] = in[y++];
        }
    }

    /**
     * Sorts the input array using bottom-up merge sort.
     *
     * @param array The array to be sorted.
     * @return The resulting sorted array.
     */

     //     public static int[] mergeSortBottomUp(int[] array) {
//         // TODO
//         if (array == null || array.length == 0) return array;
// 
//         int[] res = Arrays.copyOf(array, array.length);
//         int n = array.length;
//         for(int inc = 1; inc < n; inc*=2) {
//             for(int start = 0; start < n; start += 2*inc) {
//                 merge(res, array, start, inc);
//             }
//             int[] temp = res;
//             res = array;
//             array = temp;
//         }
// 
//         return res;
//     }
    public static int[] mergeSortBottomUp(int[] array) {
        // TODO
        if (array == null || array.length == 0) return array;

        int[] res = Arrays.copyOf(array, array.length);
        int n = array.length;

        for(int inc = 1; inc < n; inc *= 2) {
            for(int start = 0; start < n; start += 2*inc) {
                merge(res, array, start, inc);
            }

            int[] temp = res;
            res = array;
            array = temp;
        }

        return res;
    }
}
