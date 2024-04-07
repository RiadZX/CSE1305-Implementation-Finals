import java.util.*;
import java.util.stream.*;

class Solution {

    /**
     * Returns the number of blocks of connected rooms in the building.
     * @param rooms Adjacency list representation of the rooms in the building.
     * @param scrapped The rooms that are no longer being built.
     * @return The number of blocks of connected rooms in the building.
     */
    public static int numberOfBlocks(List<Set<Integer>> rooms, Set<Integer> scrapped) {
        // TODO
        Set<Integer> known = new HashSet<>();
        int count = 0;
        for(int i = 0; i < rooms.size(); i++) {
            if (!known.contains(i) && !scrapped.contains(i)) {
                count++;
                dfs(rooms, i, known, scrapped);
            }
        }

        return count;
    }

    public static void dfs(List<Set<Integer>> rooms, int start, Set<Integer> known, Set<Integer> scrapped) {
        
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while(!stack.isEmpty()) {
            int opint = stack.pop();
            known.add(opint);
            for(int neighbour : rooms.get(opint)) {
                if (!known.contains(neighbour) && !scrapped.contains(neighbour)) {
                    stack.push(neighbour);
                }
            }
        }
    }
}
