import java.util.*;

class SolutionHashTable {
  public LinkedList<Entry>[] table;
  public int capacity;

  /**
   * Constructs a new HashTable.
   *
   * Capacity of the hash table can not be 0 or negative.
   *
   * @param capacity
   *     to be used as capacity of the table.
   * @throws IllegalArgumentException
   *     if the input capacity is invalid.
   */
  @SuppressWarnings("unchecked")
  public SolutionHashTable(int capacity) {
    // TODO
    if (capacity <= 0) throw new IllegalArgumentException();
    this.capacity = capacity;
    table = new LinkedList[capacity];

    for (int i = 0; i < capacity; i++) {
      table[i] = new LinkedList<>();
    }
  }

  /**
   * Add a new Entry to the hash table,
   * uses separate chaining to deal with collisions.
   *
   * Returns false, if the key is null.
   *
   * @param key
   *     String representing the key of the entry.
   * @param value
   *     String representing the value of the entry.
   * @return true iff entry has been added successfully, else false.
   */
  public boolean put(String key, String value) {
    // TODO
    if (key == null) return false;
    int index = hash(key);

    for (Entry e : table[index]) {
      if (e.getKey() == key) {
        remove(key);
        table[index].add(new Entry(key, value));
        return true;
      }
    }

    table[index].add(new Entry(key, value));
    return true;
  }

  /**
   * Retrieve the value of the entry associated with this key.
   *
   * Returns null, if the key is null.
   *
   * @param key
   *     String representing the key of the entry to look for.
   * @return value of the entry as String iff the entry with this key is found in the hash table, else null.
   */
  public String get(String key) {
    // TODO
    if (key == null) return null;

    int index = hash(key);
    for (Entry e : table[index]) {
      if (e.getKey() == key) {
        return e.getValue();
      }
    }

    return null;
  }

  /**
   * Remove the entry associated with this key from the hash table.
   *
   * Returns false, if the key is null.
   *
   * @param key
   *     String representing the key of the entry to remove.
   * @return true iff the entry has been successfully removed, else false.
   */
  public boolean remove(String key) {
    // TODO
    if (key == null) return false;

    int index = hash(key);
    for (Entry e : table[index]) {
      if (e.getKey() == key) {
        table[index].remove(e);
        return true;
      }
    }

    return false;
  }

  /**
   * Hashes a string representing a key
   *
   * @param key
   *     String that needs to be hashed.
   * @return the hashcode of the string, modulo the capacity of the HashTable.
   */
  public int hash(String key) {
    return Math.abs(key.hashCode()) % capacity;
  }
}

class Entry {
    private String key;
    private String value;
  
    public Entry(String key, String value) {
      this.key = key;
      this.value = value;
    }
  
    public String getKey() {
      return key;
    }
  
    public String getValue() {
      return value;
    }
  }
  