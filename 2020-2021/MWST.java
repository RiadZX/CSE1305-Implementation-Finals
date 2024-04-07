package weblab;

class Solution {

  /**
   * See the description of the exercise.
   */
  public static MWSTNode getCousin(MultiWaySearchTree tree, MWSTNode node) {
      //so go to parent, then from there go to its parent..
      //check if there is something more left. -> if not, doesnt exist.
      //go down twice again, if possible most right path.
      if(node==null) return null;
      //System.out.println(node.getKeys());
      MWSTNode parent = node.getParent();
      //System.out.println(parent.getKeys());
      if(parent==null) return null;
      MWSTNode grandParent = parent.getParent();
      //System.out.println(grandParent.getKeys());

      if(grandParent==null) return null;

      //now check if there is something to the left.
      //the left one, must be smaller than the parent value
      MWSTNode unc = null;
      for(MWSTNode uncle : grandParent.getChildren()){
          if(uncle==parent) break;
          if(uncle!=null){
            unc=uncle;
          }
      }
      if(unc==null) return null;
      if(unc==parent) return null;
      //System.out.println(unc.getKeys());
      //System.out.println(unc.getChildren());
      //now we have the unc, get the rightmost child.
      MWSTNode possibleRight = null;
      for(MWSTNode n : unc.getChildren()){
        if(n!=null){
          possibleRight=n;
        }
      }
      return possibleRight;
  }

  /**
   * See the description of the exercise.
   */
  public static MWSTNode getUncle(MultiWaySearchTree tree, MWSTNode node) {
      if(node==null) return null;
      //System.out.println(node.getKeys());
      MWSTNode parent = node.getParent();
      //System.out.println(parent.getKeys());
      if(parent==null) return null;
      MWSTNode grandParent = parent.getParent();
      //System.out.println(grandParent.getKeys());

      if(grandParent==null) return null;

      //now check if there is something to the left.
      //the left one, must be smaller than the parent value
      MWSTNode unc = null;
      for(MWSTNode uncle : grandParent.getChildren()){
          if(uncle!=null){
            unc=uncle;
          }
      }
      
      if(unc==parent) return null;
      return unc;
      
  }

  /**
   * See the description of the exercise.
   */
  public static boolean restrictedSearch(MultiWaySearchTree tree, int key) {
      if(tree==null) return false;
      // we can achieve this with nested for loops :/
      if(isKeyInHerePlease(tree.getRoot(), key)) return true;
      for(MWSTNode r: tree.getRoot().getChildren()){
        if(isKeyInHerePlease(r,key)) return true;
        if(r==null) continue;
        for(MWSTNode c : r.getChildren()){

          if(isKeyInHerePlease(c,key)) return true;
          if(c==null) continue;
        }
      }

      return false;
      
  }

  private static boolean isKeyInHerePlease(MWSTNode node, int key){
    if(node==null) return false;
    for(Integer k : node.getKeys()){
      if(k==key) return true;
    }
    return false;
  }
}
