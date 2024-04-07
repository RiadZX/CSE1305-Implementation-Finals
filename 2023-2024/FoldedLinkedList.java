import java.util.NoSuchElementException;
import java.util.*;
class FoldedLinkedList<T> {

    Node<T> head;

    public FoldedLinkedList() {}

    /**
     * Returns the middle element from the list.
     *
     * @return The middle element from the list.
     * @throws NoSuchElementException iff the list is empty.
     */
    public T getMiddle() throws NoSuchElementException {
        // TODO
        if (head == null) throw new NoSuchElementException();
        if (head.getElement() == head.getOpposite()) return head.getOpposite().getElement();

        Node<T> iter = head;
        Node<T> next = head.getNext();

        while(!iter.getOpposite().equals(next)){
            iter = next;
            next = next.getNext();
        }

        return next.getElement();
    }

    /**
     * Inserts an element and its opposite at the head and tail.
     *
     * @param x Element to insert at the head.
     * @param y Element to insert at the tail.
     */
    public void addEnds(T x, T y) {
        Node<T> node1 = new Node(x);
        Node<T> node2 = new Node(y);

        if (head == null) {
            head = node1;
            node1.setOpposite(node2);
            node1.setNext(node2);
            node2.setOpposite(node1);
        } else {
            head.getOpposite().setNext(node2);
            node2.setOpposite(node1);
            node1.setNext(head);
            node1.setOpposite(node2);
            head = node1;
        }
    }

    /**
     * Removes a pair of elements: head and tail of the list.
     *
     * @return Head node of the list
     * @throws NoSuchElementException iff list is empty
     */
    public Node<T> removeEnds() throws NoSuchElementException {
        // TODO
        if (head == null) throw new NoSuchElementException();
        head.getNext().getOpposite().setNext(null);
        Node<T> oldhead = head;
        head = head.getNext();
        oldhead.setNext(null);
        return oldhead;
    }
}

class Node<T> {

    private T element;

    private Node<T> next;

    private Node<T> opposite;

    // Constructor: Creates a Node object with element = e
    public Node(T element) {
        this.element = element;
    }

    // This function returns the element variable of the Node
    public T getElement() {
        return element;
    }

    // This function returns the next Node
    public Node<T> getNext() {
        return next;
    }

    // This function returns the opposite Node
    public Node<T> getOpposite() {
        return opposite;
    }

    // This function gets Node n as input and sets the next variable of the current Node object as n
    public void setNext(Node<T> next) {
        this.next = next;
    }

    // This function gets Node n as input and sets the opposite variable of the current Node object
    // as n
    public void setOpposite(Node<T> opposite) {
        this.opposite = opposite;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node<?>)) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(element, node.element) && Objects.equals(next, node.next);
    }

    public String toString() {
        String oppositeValue = "null";
        if (opposite != null) oppositeValue = Objects.toString(opposite.element);
        String nextValue = "null";
        if (next != null) nextValue = Objects.toString(next.element);
        return "Node{element="
                + this.element.toString()
                + ", opposite="
                + oppositeValue
                + ", next="
                + nextValue
                + "}";
    }
}
