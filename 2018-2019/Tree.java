class Solution {
    /**
     * Counts the number of nodes in the tree at a certain level.
     *
     * @param tree
     *     The binary tree to count nodes in.
     * @param level
     *     The specified level to count nodes in.
     * @return the number of nodes at that level, or 0 if tree is null.
     */
    public static int countNodesAtLevel(BinaryTree tree, int level) {
      // TODO
      return countNodesAtLevel(tree, level, 0);
    }
  
    public static int countNodesAtLevel(BinaryTree tree, int level, int currentLevel){
      if (tree == null) return 0;
      if (currentLevel != level) return countNodesAtLevel(tree.getLeft(), level, currentLevel+1) + 
        countNodesAtLevel(tree.getRight(), level, currentLevel+1);
      return 1;
    }
  }
  
  class BinaryTree {

    private int key;
  
    private BinaryTree left, right;
  
    /**
     * Simple constructor.
     *
     * @param key
     *     to set as key.
     */
    public BinaryTree(int key) {
      this.key = key;
    }
  
    /**
     * Extended constructor.
     *
     * @param key
     *     to set as key.
     * @param left
     *     to set as left child.
     * @param right
     *     to set as right child.
     */
    public BinaryTree(int key, BinaryTree left, BinaryTree right) {
      this.key = key;
      this.left = left;
      this.right = right;
    }
  
    public int getKey() {
      return key;
    }
  
    /**
     * @return the left child.
     */
    public BinaryTree getLeft() {
      return left;
    }
  
    /**
     * @return the right child.
     */
    public BinaryTree getRight() {
      return right;
    }
  
    public boolean hasLeft() {
      return left != null;
    }
  
    public boolean hasRight() {
      return right != null;
    }
  }
  