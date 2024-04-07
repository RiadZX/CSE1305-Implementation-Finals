import java.util.*;

class Street {

    // Use the fields below to store the heads and tails of the even and odd side of the street
    // Do NOT change the names of the fields or how they are initialised in the constructor!
    // You may NOT add more fields to the class.
    Node headEven;

    Node tailEven;

    Node headOdd;

    Node tailOdd;

    /**
     * Make an empty street.
     */
    public Street() {
        headEven = new Node();
        tailEven = new Node();
        headEven.setNext(tailEven);
        tailEven.setPrev(headEven);
        headOdd = new Node();
        tailOdd = new Node();
        headOdd.setNext(tailOdd);
        tailOdd.setPrev(headOdd);
    }

    /**
     * Removes a house from the street.
     * If the house does not exist, the street should be unchanged and the method should return null.
     *
     * This method must run in O(n + m) time.
     * Here is n the number of even houses, and m the number of odd houses.
     *
     * @param houseNumber number of the house to be removed from this street, if present
     * @return if present, the removed house, otherwise null
     */
    public House removeHouse(int houseNumber) {
        // TODO
        Node opnode = null;

        Node evenwalker = headEven.getNext();
        Node oddwalker =  headOdd.getNext();

        while (evenwalker.getHouse() != null) {
            if(evenwalker.getHouse().getHouseNumber() == houseNumber) {
                opnode = evenwalker;
                break;
            } else {
                evenwalker = evenwalker.getNext();
            }
        }

        while (oddwalker.getHouse() != null) {
            if(oddwalker.getHouse().getHouseNumber() == houseNumber) {
                opnode = oddwalker;
                break;
            } else {
                oddwalker = oddwalker.getNext();
            }
        }

        if (opnode != null) {
            Node before = opnode.getPrev();
            Node after = opnode.getNext();

            List<Node> opposingNodes = new LinkedList<>();

            for(House h : opnode.getOpposingHouses()) {
                opposingNodes.add(getNode(h.getHouseNumber()));
            }

            for(Node n : opposingNodes) {
                n.getOpposingHouses().remove(opnode.getHouse());
            }

            before.setNext(after);
            after.setPrev(before);

            return opnode.getHouse();
        } else {
            return null;
        }
    }

    public Node getNode(int houseNumber) {
        if (houseNumber % 2 == 0 ){
            Node evenwalker = headEven.getNext();
            while (evenwalker.getHouse() != null) {
                if(evenwalker.getHouse().getHouseNumber() == houseNumber) {
                    return evenwalker;
                } else {
                    evenwalker = evenwalker.getNext();
                }
            }
            return null;
        } else {
           Node oddwalker =  headOdd.getNext();
            while (oddwalker.getHouse() != null) {
                if(oddwalker.getHouse().getHouseNumber() == houseNumber) {
                    return oddwalker;
                } else {
                    oddwalker = oddwalker.getNext();
                }
            }
            return null;
        }
    }

    /**
     * Puts all the houses in the street in a list in increasing order of house number.
     *
     * This method must run in O(n+m) time.
     * Here n is the number of even houses, and m is the number of odd houses.
     *
     * @return a list of all the houses in the street in increasing order of house number
     */
    public List<House> collectAllHouses() {
        // TODO
        List<House> houses = new LinkedList<>();
        Node evenwalker = headEven.getNext();
        Node oddwalker =  headOdd.getNext();

        while (evenwalker.getHouse() != null && oddwalker.getHouse() != null) {
            if (evenwalker.getHouse().getHouseNumber() < oddwalker.getHouse().getHouseNumber()) {
                houses.add(evenwalker.getHouse());
                evenwalker = evenwalker.getNext();
            } else {
                houses.add(oddwalker.getHouse());
                oddwalker = oddwalker.getNext();
            }
        }

        while (evenwalker.getHouse() != null) {
            houses.add(evenwalker.getHouse());
            evenwalker = evenwalker.getNext();
        }

        while (oddwalker.getHouse() != null) {
            houses.add(oddwalker.getHouse());
            oddwalker = oddwalker.getNext();            
        }

        return houses;
    }

    public void print(List<House> houses) {
        for (House house : houses) {
            System.out.println("House no. " + house.getHouseNumber());
        }
        System.out.println("--------------------");
    }

    /**
     * Gets the number of even numbered houses in the street.
     *
     * @return the number of houses with an even house number
     */
    public int getNumEven() {
        // TODO
        int count = 0;
        Node walker = headEven.getNext();

        while (walker.getHouse() != null) {
            count++;
            walker = walker.getNext();
        }
        return count;
    }
}


class House {

    private int houseNumber;

    public House(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return houseNumber == house.houseNumber;
    }

    @Override
    public String toString() {
        return "House{" + houseNumber + '}';
    }
}

class Node {

    private House house;

    private Node prev;

    private Node next;

    private HashSet<House> opposingHouses = new HashSet<>();

    public Node(House house) {
        this.house = house;
    }

    public Node(House house, Node prev, Node next) {
        this.house = house;
        this.prev = prev;
        this.next = next;
    }

    public Node() {}

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public HashSet<House> getOpposingHouses() {
        return opposingHouses;
    }

    public void setOpposingHouses(HashSet<House> opposingHouses) {
        this.opposingHouses = opposingHouses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(house, node.house)
                && Objects.equals(prev, node.prev)
                && Objects.equals(next, node.next)
                && Objects.equals(opposingHouses, node.opposingHouses);
    }

    @Override
    public String toString() {
        return "Node{" + house + ", opposingHouses=" + opposingHouses.toString() + '}';
    }
}
