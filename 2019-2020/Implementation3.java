class Solution {

  /**
   * Checks whether the given BinaryTree is a Red Black Tree.
   * @param tree BinaryTree to check.
   * @return True if the given tree is a Red Black Tree, false otherwise.
   */
  public static boolean isRedBlackTree(BinaryTree tree) {
  // TODO
    if(tree == null) return true;
    if(tree.isRed()) return false;
    return isBalancedAndBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE) && redCondition(tree);
  }

  public static boolean isBalancedAndBST(BinaryTree tree, int min, int max) {
    if (tree.getValue() <= min || tree.getValue() >= max) return false;
    if (tree.hasRight() && !isBalancedAndBST(tree.getRight(), tree.getValue(), max)) return false; 
    if (tree.hasLeft() && !isBalancedAndBST(tree.getLeft(), min, tree.getValue())) return false;
    if (getBlackHeight(tree.getLeft()) != getBlackHeight(tree.getRight())) return false;
    return true;
  }

  public static int getBlackHeight(BinaryTree tree) {
    if (tree == null) return 1;
    if (tree.isRed()) return Math.min(getBlackHeight(tree.getLeft()), getBlackHeight(tree.getRight()));
    return 1 + Math.min(getBlackHeight(tree.getLeft()), getBlackHeight(tree.getRight()));
  }

  public static boolean redCondition(BinaryTree tree) {
    if (tree == null) return true;
    if (tree.isRed()) {
      if (tree.hasRight() && tree.getRight().isRed()) return false;
      if (tree.hasLeft() && tree.getLeft().isRed()) return false;
    }
    
    return redCondition(tree.getLeft()) && redCondition(tree.getRight());
  }
}

class BinaryTree {

  private int value;

  private BinaryTree left, right;

  private boolean isRed;

  /**
   * Simple constructor.
   *
   * @param value Value for this tree set as value.
   * @param isRed True if this node is red, false otherwise.
   */
  public BinaryTree(int value, boolean isRed) {
    this.value = value;
    this.isRed = isRed;
  }

  /**
   * Extended constructor.
   *
   * @param value to set as value.
   * @param left to set as left child.
   * @param right to set as right child.
   */
  public BinaryTree(int value, boolean isRed, BinaryTree left, BinaryTree right) {
    this(value, isRed);
    setLeft(left);
    setRight(right);
  }

  /**
   * @return the value of this tree.
   */
  public int getValue() {
    return value;
  }

  /**
   * @param value the new value of this tree.
   */
  public void setValue(int value) {
    this.value = value;
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

  /**
   * @return true if this node is red, false otherwise.
   */
  public boolean isRed() {
    return isRed;
  }

  /**
   * @return true if this node is black, false otherwise.
   */
  public boolean isBlack() {
    return !isRed;
  }

  /**
   * @return True if the tree has a left child, false otherwise.
   */
  public boolean hasLeft() {
    return left != null;
  }

  /**
   * @return True if the tree has a right child, false otherwise.
   */
  public boolean hasRight() {
    return right != null;
  }

  /**
   * @param left Left subtree to set.
   */
  public void setLeft(BinaryTree left) {
    this.left = left;
  }

  /**
   * @param right Right subtree to set.
   */
  public void setRight(BinaryTree right) {
    this.right = right;
  }

  /**
   * @param red True if the new color is red, false otherwise.
   */
  public void setRed(boolean red) {
    isRed = red;
  }
}
