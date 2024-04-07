import java.util.*;

class Solution {

    /**
     * See the description of the exercise.
     */
    public static List<Integer> bfs(MultiWaySearchTree tree) {
        if (tree == null || tree.getRoot() == null) return null;

        List<Integer> list = new LinkedList<>();
        Queue<MWSTNode> queue = new LinkedList<>();
        queue.add(tree.getRoot());

        while(!queue.isEmpty()) {
            MWSTNode opnode = queue.poll();
            list.addAll(opnode.getKeys());

            for(MWSTNode child : opnode.getChildren()) {
                if (child != null) queue.add(child);
            }
        }

        return list;
    }

    public static MWSTNode getNearestLeftCousin(MultiWaySearchTree tree, MWSTNode node) {
        // TODO
        if (tree == null || tree.getRoot() == null || node == null) return null;
        if (node.getParent() == null || node.getParent().getParent() == null) return null;

        MWSTNode grandParent = node.getParent().getParent();
        MWSTNode parent = node.getParent();
        
        int i = 0;
        while( i < grandParent.getChildren().size()) {
            if(grandParent.getChildren().get(i) == parent) break;
            i++;
        }
        i--;

        if (i >= 0) {
            MWSTNode relative = grandParent.getChildren().get(i);
            print(relative);
            int j = relative.getChildren().size()-1;
            while (j >= 0) {
                if (relative.getChildren().get(j) != null) break;
                j--;
            }

            return relative.getChildren().get(j);
        } else {
            return null;
        }

    }

    public static void print(MWSTNode node) {
        System.out.print(" Node is ");
        for(int key : node.getKeys()) {
            System.out.print(key + " ");
        }
        System.out.print("\n");
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
            this.children = new LinkedList<>(Collections.nCopies(keys.size() + 1, null));
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MWSTNode mwstNode = (MWSTNode) o;
        return Objects.equals(keys, mwstNode.keys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keys);
    }
}
