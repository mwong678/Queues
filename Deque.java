/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class Deque < Item > implements Iterable < Item > {
    private class Node {
        Item item;
        Node next, prev;
    }

    private Node first,
            last;
    private int count;

    public Deque() {
        this.first = null;
        this.last = null;
        this.count = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return this.count;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            this.first = newNode;
            this.last = newNode;
        } else {
            newNode.next = this.first;
            this.first.prev = newNode;
            this.first = newNode;
        }
        this.count++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.next = newNode;
            newNode.prev = this.last;
            this.last = newNode;
        }
        this.count++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Node temp = this.first;
        if (size() == 1) {
            reset();
        } else {
            Node next = temp.next;
            temp.next = null;
            next.prev = null;
            this.first = next;
        }
        this.count--;

        return temp.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        Node temp = this.last;
        if (size() == 1) {
            reset();
        } else {
            Node prev = temp.prev;
            prev.next = null;
            temp.prev = null;
            this.last = prev;
        }

        this.count--;

        return temp.item;
    }

    private void reset() {
        this.first = null;
        this.last = null;
    }

    public Iterator < Item > iterator() {
        return new DequeIterator();

    }
    private void printDeque() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
        } else {
            Iterator < Item > dqi = iterator();
            Item i;
            while (dqi.hasNext()) {
                i = dqi.next();
                System.out.print(i + "->");
            }
            i = dqi.next();
            System.out.print(i + "->");
            System.out.println();
        }
    }

    private class DequeIterator implements Iterator < Item > {
        private Node current = first;
        public boolean hasNext() {
            if (isEmpty()) {
                return false;
            }
            return !(current.next == null);
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (isEmpty()) {
                return null;
            }
            Node temp = current;
            current = current.next;
            return temp.item;
        }
    }

    public static void main(String[] args) {
        Deque < Integer > dq = new Deque < Integer > ();
        dq.addFirst(3);
        dq.addFirst(2);
        dq.addFirst(1);
        dq.addLast(4);
        dq.addLast(5);
        dq.removeFirst();
        dq.removeLast();
        dq.printDeque();
    }
}