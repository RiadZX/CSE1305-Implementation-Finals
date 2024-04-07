import java.util.*;

class GraphChecker {

    /**
     * Traverses the given graph depth first and returns the IDs of visited vertices in order, starting from the given vertex.
     * In case multiple choices are possible for a next vertex, they are visited in increasing order of ID.
     * Runs in O(V+E) time. Does not modify the input graph.
     * @param g Graph to traverse.
     * @param v Vertex to start the traversal from.
     * @return List of IDs of vertices found using DFS traversal.
     */
    public List<Integer> traverseDepthFirst(Graph g, Vertex v) {
        Set<Integer> s = new HashSet<>();
        return traverseDepthFirstHelper(g,v,s);
    }
    public List<Integer> traverseDepthFirstHelper(Graph g,Vertex v, Set<Integer> s){
        List<Integer> result = new ArrayList<>();
        result.add(v.getId());
        s.add(v.getId());
        for(Vertex next:v.getNeighbours()){
            if(!s.contains(next.getId())){
                result.addAll(traverseDepthFirstHelper(g,next,s));
            }
        }
        return result;
    }

    /**
     * Returns the transpose of the given graph, i.e. a graph with all edges reversed.
     * Runs in O(V+E) time. Does not modify the input graph.
     * @param g Graph to create the transpose of.
     * @return Transpose of the given graph.
     */
    public Graph transpose(Graph g) {
        Graph result = new Graph();
        List<Vertex> vertexes =  g.getAllVertices();
        for(Vertex next: vertexes){
            result.addVertex(new Vertex(next.getId()));
        }

        for(Vertex next:vertexes){
            //  System.out.print(next.getId());
            for(Vertex neighbour : next.getNeighbours()){
                // System.out.print(neighbour.getId());
                result.addEdge(result.getVertex(neighbour.getId()),
                        result.getVertex(next.getId()));
            }
            // System.out.println();
        }
        return result;
    }

    /**
     * Returns true if the given graph is strongly connected, false otherwise.
     * Runs in O(V+E) time. Does not modify the input graph.
     * @param g Graph to check for strong connectedness.
     * @return True if the graph is strongly connected, false otherwise.
     */
    public boolean isStronglyConnected(Graph g) {
        List<Integer> normalGraphList = traverseDepthFirst(g,g.getVertex(0));
        List<Integer> transposeGraphList = traverseDepthFirst(transpose(g),(transpose(g)).getVertex(0));
        return normalGraphList.size() == g.getAllVertices().size() &&
                normalGraphList.size() == transposeGraphList.size();
    }
}


class Vertex {

    private final int id;

    private final Set<Vertex> neighbours;

    public Vertex(int id) {
        this.id = id;
        neighbours = new TreeSet<>(Comparator.comparing(Vertex::getId));
    }

    public int getId() {
        return id;
    }

    public void addNeighbour(Vertex v) {
        neighbours.add(v);
    }

    public List<Vertex> getNeighbours() {
        // Sorted in ascending order of ID
        return new ArrayList<>(this.neighbours);
    }

    @Override
    public String toString() {
        return "V" + id;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Vertex v && this.id == v.id && this.neighbours.equals(v.neighbours);
    }
}

class Graph {

    private final Map<Integer, Vertex> vertices = new HashMap<>();

    public void addVertex(Vertex v) {
        this.vertices.put(v.getId(), v);
    }

    public Vertex getVertex(int id) {
        return this.vertices.get(id);
    }

    public List<Vertex> getAllVertices() {
        return new ArrayList<>(this.vertices.values());
    }

    public void addEdge(Vertex v, Vertex w) {
        v.addNeighbour(w);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("<Graph");
        for (Vertex v : vertices.values()) {
            sb.append(" <V").append(v.getId());
            if (v.getNeighbours().size() > 0) {
                sb.append(", neighbours:");
                for (Vertex n : v.getNeighbours()) {
                    sb.append(" ").append(n.getId());
                }
            }
            sb.append(">");
        }
        sb.append(">");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Graph g && this.vertices.equals(g.vertices);
    }
}
