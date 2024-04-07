import java.util.*;

class MultiMap {

  private Map<Integer, List<Integer>> map;
  int size;

  /**
   * Creates a new MultiMap.
   */
  public MultiMap() {
  // TODO
    map = new HashMap<>();
    size = 0;
  }

  /**
   * @return The number of (key, value) pairs in the MultiMap.
   */
  public int size() {
  // TODO
    return size;
  }

  /**
   * @return True if the MultiMap is empty, false otherwise.
   */
  public boolean isEmpty() {
  // TODO
    return size == 0;
  }

//   public void put(int key, int value) {
//   // TODO
//     if(map.get(key) == null) {
//       map.put(key, new LinkedList<>());
//     }
// 
//     map.get(key).add(value);
//     size++;
//   }
  /**
   * Adds the given (key, value) pair to the MultiMap.
   *
   * @param key Key for the new item.
   * @param value New item to add to the MultiMap.
   */
  public void put(int key, int value) {
  // TODO
    if (map.get(key) == null) {
      map.put(key, new LinkedList<>());
    }
    map.get(key).add(value);
    size++;
  }

  /**
   * Returns all values in the MultiMap for the given key.
   *
   * @param key Key to return all entries for.
   * @return A list of all entries for the given key.
   *         If the key is not in the map, return an empty list.
   */
  public List<Integer> get(int key) {
  // TODO
    if (isEmpty() || !map.containsKey(key)) return new LinkedList<>();
    return map.get(key);
  }

  /**
   * Removes the given (key, value) pair from the MultiMap.
   *
   * @param key Key for the value that should be removed.
   * @param value Value to remove.
   * @return True if removal was successful, false otherwise.
   */
  public boolean remove(int key, int value) {
  // TODO
      if (isEmpty() || get(key) == null || get(key).isEmpty()) return false;
      if (!map.get(key).contains(value)) return false;
      List<Integer> list = map.get(key);

      list.remove((Integer) value);
      size--;
      return true;
  }
}
