package adt;
import java.io.Serializable;
import java.util.Iterator;

public class LinkedStack<T> implements StackInterface<T>, Serializable{
    private Node topNode;

    public LinkedStack(){
        topNode = null;
    }

    @Override
    public void push(T newEntry) {
        
        Node newNode = new Node(newEntry);
        newNode.next = topNode;
        topNode = newNode;

        System.out.println("Hi, I got push!!!");
    }

    @Override
    public String toString(){
        String str = "";
        Node currentNode = topNode;
        while (currentNode != null){
            str += currentNode.data + "";
            currentNode = currentNode.next;
        }
        return str;
    }

    @Override
    public T pop() {
        T topEntry = null;
        if( !isEmpty()){
            topEntry = topNode.data;
            topNode = topNode.next;
        }
        return topEntry;
    }

    @Override
    public T peek() {
        T  topEntry = null;
        if( !isEmpty()){
            topEntry = topNode.data;
        }
        return topEntry;
    }

    @Override
    public boolean isEmpty(){
        return topNode == null;
    }

    @Override
    public void clear(){
        topNode = null;
    }

    @Override
    public Iterator<T> getIterator() {
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<T>, Serializable{

        private Node iteratorPointer;

        public LinkedStackIterator() {
            iteratorPointer = topNode;
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

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node() {
            
        }
    }


}