/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue < Item > implements Iterable < Item > {

    private Item[] queue;
    private int current;

    public RandomizedQueue() {
        this.queue = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return this.current == 0;
    }

    public int size() {
        return this.current;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        this.queue[this.current++] = item;

        if (this.current == this.queue.length) {
            resize(this.current * 2);
        }
    }

    private void resize(int size) {
        if (size == 0) {
            size = 1;
        }
        Item[] newQueue = (Item[]) new Object[size];
        int j = 0;
        for (int i = 0; i < this.queue.length; i++) {
            if (this.queue[i] != null) {
                newQueue[j++] = this.queue[i];
            }
        }

        this.queue = newQueue;
    }


    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int randIndex = StdRandom.uniform(this.current);
        Item temp = this.queue[randIndex];
        this.queue[randIndex] = null;
        this.current--;
        if (this.current >= 0 && this.current == this.queue.length / 4) {
            resize(this.queue.length / 2);
        } else {
            resize(this.queue.length);
        }
        return temp;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return this.queue[StdRandom.uniform(this.current)];
    }

    public Iterator < Item > iterator() {
        return new RandomizedIterator();
    }
    private class RandomizedIterator implements Iterator < Item > {
        private Item[] queueI;
        private int count;

        public RandomizedIterator() {
            this.queueI = (Item[]) new Object[queue.length];
            this.count = 0;
            while (this.count < size()) {
                int rand = StdRandom.uniform(size());
                if (this.queueI[rand] == null) {
                    this.queueI[rand] = queue[this.count++];
                }
            }
            this.count = 0;
        }
        public boolean hasNext() {
            if (isEmpty()) {
                return false;
            }
            return (this.count < size());
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (isEmpty()) {
                throw new java.util.NoSuchElementException();
            }
            if (!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            while (this.queueI[this.count] == null) {
                this.count++;
            }
            return this.queueI[this.count++];
        }
    }

    private void print() {
        if (isEmpty()) {
            return;
        }
        Iterator < Item > ri = iterator();
        while (ri.hasNext()) {
            System.out.print(ri.next() + "\t");
        }
        System.out.println();
    }

    private void print2(){
        if (isEmpty()) {
            return;
        }

        for (int i = 0; i < this.queue.length; i++){
            System.out.print(this.queue[i]+ " ");
        }
        System.out.println("Length: "+this.queue.length);
    }

    public static void main(String[] args) {
        RandomizedQueue < Integer > rq = new RandomizedQueue < Integer > ();
        for (int  i= 0;i<4;i++){
            rq.enqueue(i);
        }
        rq.print();

    }
}