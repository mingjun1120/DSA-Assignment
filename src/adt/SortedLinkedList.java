package adt;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

public class SortedLinkedList<T extends Comparable<T>> implements SortedLinkedListInterface<T>, Serializable {

    private Node firstNode;
    private int length;

    public SortedLinkedList() {
        firstNode = null;
        length = 0;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        Node nodeBefore = null;
        Node currentNode = firstNode;
        while (currentNode != null && newEntry.compareTo(currentNode.data) > 0) {
            nodeBefore = currentNode;
            currentNode = currentNode.next;
        }

        if (isEmpty() || (nodeBefore == null)) { // CASE 1: add at beginning
            newNode.next = firstNode;
            firstNode = newNode;
        } else {	// CASE 2: add in the middle or at the end, i.e. after nodeBefore
            newNode.next = currentNode;
            nodeBefore.next = newNode;
        }
        length++;
        return true;
    }

    @Override
    public void add(T[] newEntry) {
        for(T t : newEntry){
            add(t);
        }
    }

    @Override
    public boolean remove(T anEntry) {
        if (!isEmpty()) {
            Node previousNode = null;
            Node currentNode = firstNode;
            while (currentNode != null && currentNode.data.compareTo(anEntry) < 0) {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            if (currentNode != null && currentNode.data.equals(anEntry)) {
                if (currentNode == firstNode) {
                    firstNode = firstNode.next;
                } else {
                    previousNode.next = currentNode.next;
                }
                length--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node tempNode = firstNode;
        int pos = 1;

        while (!found && (tempNode != null)) {
            if (anEntry.compareTo(tempNode.data) <= 0) {
                found = true;
            } else {
                tempNode = tempNode.next;
                pos++;
            }
        }
        if (tempNode != null && tempNode.data.equals(anEntry)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        firstNode = null;
        length = 0;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public boolean isEmpty() { return (length == 0); }

    @Override
    public Iterator<T> getIterator() { return new SortedLinkedListIterator(); }

    public String toString() {
        String outputStr = "";
        Node currentNode = firstNode;
        while (currentNode != null) {
            outputStr += currentNode.data + "\n";;
            currentNode = currentNode.next;
        }
        return outputStr;
    }

    private class SortedLinkedListIterator implements Iterator<T>, Serializable{

        private Node iteratorPointer;

        public SortedLinkedListIterator() { iteratorPointer = firstNode; }

        @Override
        public boolean hasNext() { return iteratorPointer != null; }

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

    private class Node implements Serializable{

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
