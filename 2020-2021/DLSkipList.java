import java.util.*;

class CoatiList {

  private CoatiNode head;

  private CoatiNode tail;

  public CoatiNode getHead() {
    return head;
  }

  public CoatiNode getTail() {
    return tail;
  }

  /**
   * Adds value v in a new node to the head of the list.
   *
   * @param v Value to add.
   */

  public void addFirst(int v) {
  // TODO
    if (head == null) {
      CoatiNode node = new CoatiNode(v);
      head = node;
      tail = node;
    } else {
      CoatiNode node = new CoatiNode(v);
      node.setNext(head);
      head.setPrevious(node);
      head = node;

      if (getSkipAhead(head) != null) {
        CoatiNode skip = getSkipAhead(head);
        head.setSkipAhead(skip);
        skip.setSkipBack(head);
      }

    }
  }

  public CoatiNode getSkipAhead(CoatiNode node) {
    int i = 0;
    CoatiNode walker = node;
    while( i<3 && walker != null) {
      walker = walker.getNext();
      i++;
    }
    return walker;
  }

  /**
   * Remove the first node in the list and return its value.
   *
   * @return The value of the head node.
   * @throws EmptyListException If the list is empty.
   */
  public int removeFirst() {
  // TODO
    if (head == null) {
      throw new EmptyListException();
    } else if (head == tail) {
      CoatiNode oldhead = head;
      head = null;
      tail = null;
      return oldhead.getValue();
    } else {
      CoatiNode oldhead = head;
      head = head.getNext();
      head.setPrevious(null);

      if (getSkipAhead(oldhead) != null) getSkipAhead(oldhead).setSkipBack(null);

      return oldhead.getValue(); 
    }
  }

  /**
   * Returns the value of node at the given position.
   * @param pos Position to look for.
   * @return The value of the node at the given position.
   * @throws IllegalIndexException If there is no node at this index.
   */
  public int getValueAtPosition(int pos) {
  // TODO
    if (head == null || pos < 0) throw new IllegalIndexException();

    int i1 = 0;
    int i2 = pos % 3;
    int j1 = 0;
    int j2 = pos/3;

    CoatiNode walker = head;

    while(j1 < j2 && walker != null) {
      walker = walker.getSkipAhead();
      j1++;
    }

    while(i1 < i2 && walker != null) {
      walker = walker.getNext();
      i1++;
    }

    if (walker != null) return walker.getValue();
    throw new IllegalIndexException();
  }
}


class CoatiNode {

  private int value;

  private CoatiNode next;

  private CoatiNode previous;

  private CoatiNode skipAhead;

  private CoatiNode skipBack;

  public CoatiNode(int value) {
    this.value = value;
  }

  public CoatiNode(int value, CoatiNode previous, CoatiNode next) {
    this(value);
    this.previous = previous;
    this.next = next;
  }

  public CoatiNode(int value, CoatiNode previous, CoatiNode next, CoatiNode skipBack, CoatiNode skipAhead) {
    this(value, previous, next);
    this.skipBack = skipBack;
    this.skipAhead = skipAhead;
  }

  public int getValue() {
    return value;
  }

  public CoatiNode getNext() {
    return next;
  }

  public void setNext(CoatiNode next) {
    this.next = next;
  }

  public CoatiNode getPrevious() {
    return previous;
  }

  public void setPrevious(CoatiNode previous) {
    this.previous = previous;
  }

  public CoatiNode getSkipAhead() {
    return skipAhead;
  }

  public void setSkipAhead(CoatiNode skipAhead) {
    this.skipAhead = skipAhead;
  }

  public CoatiNode getSkipBack() {
    return skipBack;
  }

  public void setSkipBack(CoatiNode skipBack) {
    this.skipBack = skipBack;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    CoatiNode coatiNode = (CoatiNode) o;
    return value == coatiNode.value && Objects.equals(next, coatiNode.next) && Objects.equals(previous, coatiNode.previous) && Objects.equals(skipAhead, coatiNode.skipAhead) && Objects.equals(skipBack, coatiNode.skipBack);
  }
}

class EmptyListException extends RuntimeException {

  private static final long serialVersionUID = 42L;
}

class IllegalIndexException extends RuntimeException {

  private static final long serialVersionUID = 42L;
}
