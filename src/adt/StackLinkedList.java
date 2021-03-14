
package adt;


public class StackLinkedList<T> implements StackInterface<T> {
    private Node topNode;

    public StackLinkedList(){
        topNode = null;
    }

    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = topNode;
        topNode = newNode;
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

    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}