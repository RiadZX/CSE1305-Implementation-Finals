import java.util.*;

class Solution {

  /**
   * @param village Adjacency map representing the village.
   * @param coatiHouses The IDs of the coati houses that should be connected.
   * @return The roads on which the traps should be dismantled.
   */
  public static Set<Road> regroupTheCoatis(List<Map<Integer, Integer>> village, Set<Integer> coatiHouses) {
  // TODO
    UnionFind uF = new UnionFind(village.size());
    PriorityQueue<Road> pq = new PriorityQueue<>();



    Set<Road> result = new HashSet<>();
    Set<Road> allRoads = new HashSet<>();
    Set<Integer> connectedHouses = new HashSet<>();
    
    for(int i = 0; i < village.size(); i++) {
      Map<Integer, Integer> map = village.get(i);
      for(int dest : map.keySet()) {
        Road newRoad = new Road(i, dest, map.get(dest));
        allRoads.add(newRoad);
      }
    }

    pq.addAll(allRoads);

    while(!pq.isEmpty()) {
      Road minRoad = pq.poll();
      if (coatiHouses.contains(minRoad.getTo()) && coatiHouses.contains(minRoad.getFrom())) {
        if (uF.union(minRoad.getFrom(), minRoad.getTo())) {
          result.add(minRoad);
          connectedHouses.add(minRoad.getFrom());
          connectedHouses.add(minRoad.getTo());
        }
      }
    }

    for (int house : coatiHouses) {
    if (uF.find(house) != uF.find(coatiHouses.iterator().next())) {
        // At least one coati house cannot be connected to others
        return null;
    }
  }

    return result;
  }
}


class Road implements Comparable<Road> {

  private int from;

  private int to;

  private int traps;

  /**
   * Represents a road from `from` to `to` with `traps` traps.
   * @param from ID of the house on one side.
   * @param to ID of the house on the other side.
   * @param traps Amount of traps on this road.
   */
  public Road(int from, int to, int traps) {
    this.from = from;
    this.to = to;
    this.traps = traps;
  }

  public int getFrom() {
    return from;
  }

  public int getTo() {
    return to;
  }

  public int getTraps() {
    return traps;
  }

  /**
   * Compares the amount of traps of this road to that on another.
   * @param o Road to compare to.
   * @return -1 if `this` road has fewer traps, 1 if it has more, or 0 if both have an equal amount of traps.
   */
  @Override
  public int compareTo(Road o) {
    return Integer.compare(this.traps, o.traps);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Road road = (Road) o;
    return ((from == road.from && to == road.to) || (from == road.to && to == road.from)) && traps == road.traps;
  }

  @Override
  public int hashCode() {
    return Objects.hash(Math.min(from, to), Math.max(from, to), traps);
  }

  @Override
  public String toString() {
    return "Road{" + "from=" + from + ", to=" + to + ", traps=" + traps + '}';
  }
}

class UnionFind {

  private int[] parent;

  private int[] rank;

  /**
   * Union Find structure implemented with two arrays for Union by Rank.
   * @param size Size of the UF structure.
   */
  public UnionFind(int size) {
    parent = new int[size];
    rank = new int[size];
    for (int i = 0; i < size; i++) parent[i] = i;
  }

  /**
   * Merges two clusters, if they are not already part of the same cluster.
   *
   * @param i a node in the first cluster
   * @param j a node in the second cluster
   * @return true iff the merge was successful.
   */
  boolean union(int i, int j) {
    int parent1 = find(i);
    int parent2 = find(j);
    if (parent2 == parent1)
      return false;
    if (rank[parent1] > rank[parent2]) {
      parent[parent2] = parent1;
    } else if (rank[parent2] > rank[parent1]) {
      parent[parent1] = parent2;
    } else {
      parent[parent2] = parent1;
      rank[parent1]++;
    }
    return true;
  }

  /**
   * @param i index of a node
   * @return the root of the subtree containing i.
   */
  int find(int i) {
    int parent = this.parent[i];
    if (i == parent) {
      return i;
    }
    return this.parent[i] = find(parent);
  }

  /**
   * @return the ranks of the trees.
   */
  public int[] getRank() {
    return rank;
  }

  /**
   * @return the parents of the trees
   */
  public int[] getParent() {
    return parent;
  }
}
