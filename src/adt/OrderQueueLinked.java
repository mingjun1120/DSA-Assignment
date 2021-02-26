package adt;
import org.w3c.dom.Node;
import java.io.Serializable;

public class OrderQueueLinked<T> implements OrderQueueInterface<T>, Serializable{

    private Node firstNode; // references node at front of queue
    private Node lastNode;  // references node at back of queue
    private int size;

    public OrderQueueLinked() {
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
    public boolean isEmpty() {
        return (firstNode == null) && (lastNode == null);
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
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

//    @Override
//    public T[] getAllEntries() {
//        T[] temp = (T[]) new Object[size];
//        Node currentNode = firstNode;
//        int i = 0;
//        while(currentNode != null) {
//            temp[i] = currentNode.data;
//            currentNode = currentNode.next;
//            i++;
//        }
//        return temp;
//    }

//    @Override
//    public void getAllEntries() {
//        Node currentNode = firstNode;
//        while(currentNode != null) {
//            System.out.println(currentNode.data.toString());
//            currentNode = currentNode.next;
//        }
//    }

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
