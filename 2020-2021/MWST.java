import java.util.*;

class Solution {

  /**
   * See the description of the exercise.
   */
  public static MWSTNode getCousin(MultiWaySearchTree tree, MWSTNode node) {
  // TODO
        if (tree == null || node == null || node.getParent() == null || node.getParent().getParent() == null) return null;
        
        MWSTNode parent = node.getParent();
        MWSTNode grandParent = parent.getParent();

        int i = -1;
        while(!grandParent.getChildren().get(i+1).equals(parent)) {
            i++;
        }

        if (i == -1) return null;
        MWSTNode relative = grandParent.getChildren().get(i);

        int j = relative.getChildren().size()-1;

        while(relative.getChildren().get(j) == null) {
            j--;
        }

        return relative.getChildren().get(j);
  }

  /**
   * See the description of the exercise.
   */
   public static MWSTNode getUncle(MultiWaySearchTree tree, MWSTNode node) {
   
     if (tree == null || tree.getRoot() == null || node == null) return null;
     if (node.getParent() == null) return null;
     if (node.getParent().getParent() == null) return null;
 
     MWSTNode grandParent = node.getParent().getParent();
     MWSTNode uncle = null;
 
     int i = 1;
     int size = grandParent.getChildren().size();
     while(uncle == null && uncle != node.getParent() && size-i >= 0){
       uncle = grandParent.getChildren().get(size-i);
       i++;
     }
     return uncle;
   }

  /**
   * See the description of the exercise.
   */
   public static boolean restrictedSearch(MultiWaySearchTree tree, int key) {
    if(tree == null) return false;
    LinkedList<Integer> known = new LinkedList<>();
    Queue<MWSTNode> queue = new LinkedList<>();
    queue.add(tree.getRoot());

    //layer 1
    MWSTNode opnode = queue.poll();
    known.addAll(opnode.getKeys());
    if (known.contains(key)) return true;

    //layer 2
    for(MWSTNode child : opnode.getChildren()) {
      if (child != null) {
        queue.add(child);
        known.addAll(child.getKeys());
        if(known.contains(key)) return true;
        for (MWSTNode grandchild : child.getChildren()) {
          if (grandchild != null) queue.add(grandchild);
        }
      }
    }

    //layer 
    while(!queue.isEmpty()) {
      opnode = queue.poll();
      known.addAll(opnode.getKeys());
      if(known.contains(key)) return true;
    }

    return false;
   }
}


class MultiWaySearchTree {

  private MWSTNode root;

  public MultiWaySearchTree(MWSTNode root) {
    this.root = root;
  }

  public MWSTNode getRoot() {
    return this.root;
  }

  public void setRoot(MWSTNode root) {
    this.root = root;
  }
}

class MWSTNode {

  private List<Integer> keys;

  private MWSTNode parent;

  private LinkedList<MWSTNode> children;

  public MWSTNode(List<Integer> keys, MWSTNode parent, LinkedList<MWSTNode> children) {
    this.keys = keys;
    this.parent = parent;
    // If children is left as null, create a list of m + 1 nulls, where m is the number of keys
    if (children == null) {
      this.children = new LinkedList<>(Collections.nCopies(keys.size(), null));
    } else {
      this.children = children;
    }
  }

  public List<Integer> getKeys() {
    return keys;
  }

  public void setKeys(List<Integer> keys) {
    this.keys = keys;
  }

  public MWSTNode getParent() {
    return parent;
  }

  public void setParent(MWSTNode parent) {
    this.parent = parent;
  }

  public LinkedList<MWSTNode> getChildren() {
    return children;
  }

  public void setChildren(LinkedList<MWSTNode> children) {
    this.children = children;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    MWSTNode mwstNode = (MWSTNode) o;
    return Objects.equals(keys, mwstNode.keys);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keys);
  }
}
