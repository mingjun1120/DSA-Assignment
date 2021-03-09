package adt;

import java.io.Serializable;
import java.util.Iterator;

public class LinkedQueue<T> implements QueueInterface<T>, Serializable{

    private Node firstNode; // references node at front of queue
    private Node lastNode;  // references node at back of queue
    private int size;

    public LinkedQueue() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(T newEntry) {
        Node newNode = new Node(newEntry, null);

        if (isEmpty()) {
            firstNode = newNode;
        } else {
            lastNode.next = newNode;
        }
        lastNode = newNode;
        size++;
    }

    @Override
    public T dequeue() {
        T front = null;

        if (!isEmpty()) {
            front = firstNode.data;
            firstNode = firstNode.next;

            if (firstNode == null) {
                lastNode = null;
            }
        }
        if (front != null) {
            size--;
        }
        return front;
    }

    @Override
    public T getFront() {
        T front = null;

        if (!isEmpty()) {
            front = firstNode.data;
        }
        return front;
    }

    @Override
    public T getLast() {
        T last = null;

        if (!isEmpty()) {
            last = lastNode.data;
        }
        return last;
    }

    @Override
    public boolean isEmpty() {
        return (firstNode == null) && (lastNode == null);
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    @Override
    public boolean remove(T t) {
        Node current = firstNode;
        Node previous = null;

        while(current != null) {
            if(current.data.equals(t)) {
                if(previous == null)
                    firstNode = current.next;
                else
                    previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current  = current.next;
        }
        return false;
    }

    @Override
    public T[] getAllEntries() {
        T[] temp = (T[]) new Object[size];
        Node currentNode = firstNode;
        int i = 0;
        while(currentNode != null) {
            temp[i] = currentNode.data;
            currentNode = currentNode.next;
            i++;
        }
        return temp;
    }

    @Override
    public Iterator<T> getIterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<T>, Serializable {

        private Node iteratorPointer;

        public LinkedQueueIterator() {
            iteratorPointer = firstNode;
        }

        @Override
        public boolean hasNext() {
            return iteratorPointer != null;
        }

        @Override
        public T next() {
            T returnData = null;
            if (hasNext()) {
                returnData = iteratorPointer.data;
                iteratorPointer = iteratorPointer.next;
            }
            return returnData;
        }
    }

    private class Node implements Serializable {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
