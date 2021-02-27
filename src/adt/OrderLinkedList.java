package adt;
import java.io.Serializable;

public class OrderLinkedList<T> implements OrderListInterface<T>, Serializable {
    private Node firstNode; // references node at front of queue
    private Node lastNode;  // references node at back of queue
    private int size;

    @Override
    public void add(T newEntry) {
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
    public T remove(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= size)) {
            if (givenPosition == 1) {        // case 1: remove first entry
                result = firstNode.data;     // save entry to be removed
                firstNode = firstNode.next;  // assign secondNode as firstNode
            } else {                         // case 2: givenPosition > 1
                Node nodeBefore = firstNode;
                for (int i = 1; i < givenPosition - 1; ++i) {
                    nodeBefore = nodeBefore.next;		// advance nodeBefore to its next node
                }
                result = nodeBefore.next.data;          // save entry to be removed
                nodeBefore.next = nodeBefore.next.next;	// make node before point to node after the
            } 									        // one to be deleted (to disconnect node from chain)
            size--;
        }
        return result; // return removed entry, or null if operation fails
    }

    @Override
    public T getEntry(int givenPosition) {
        T data = null;
        if(givenPosition >= 1 && givenPosition <= size) {
            Node currentNode = firstNode;
            for(int index = 1; index < givenPosition; index++){
                currentNode = currentNode.next;
            }
            data = currentNode.data;
        }
        return data;
    }

    @Override
    public boolean isEmpty() {
        return (firstNode == null) && (lastNode == null);
    }

    @Override
    public int getLength() {
        return size;
    }

    @Override
    public void offer(T newEntry) {
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

    private class Node {

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
