package adt;

public class SortedLinkedList<T extends Comparable<T>> implements ReportListInterface<T>{
    private Node firstNode;
    private Node currentNode;
    private int length;

    public SortedLinkedList() {
        firstNode = null;
        length = 0;
    }

    @Override
    public boolean add(T newEntry) {
        if(firstNode == null)
            firstNode = new Node(newEntry, null);
        else{
            Node lastNode = getLastNode(firstNode);
            lastNode.next = new Node(newEntry, null);
        }
        length++;
        return true;
    }

    private Node getLastNode(Node nextNode){
        if(nextNode.next == null)
            return nextNode;
        else
            return getLastNode(nextNode.next);
    }

    @Override
    public boolean remove(T anEntry) {
        Node targetNode = searchNode(anEntry, firstNode);

        if(targetNode == null)
            return false;
        else{
            if(targetNode.equals(firstNode)){
                Node nextNode = targetNode.next;
                nextNode.prev = null;
                firstNode = nextNode;
            }
            else if(targetNode.next == null){
                Node prevNode = targetNode.prev;
                prevNode.next = null;
            }
            else{
                Node prevNode = targetNode.prev;
                Node nextNode = targetNode.next;
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
            }
            length--;

            return true;
        }
    }

    private Node searchNode(T anEntry, Node nextNode){
        if(nextNode.data.equals((anEntry)))
            return nextNode;
        else if(nextNode.next == null)
            return null;
        else
            return searchNode(anEntry, nextNode.next);
    }

    @Override
    public boolean contains(T anEntry){
        return searchNode(anEntry, firstNode) != null;
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null;
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
    public void reverse() {

    }

    @Override
    public String toString() {
        String str = "";
        Node currentNode = firstNode;

        while(currentNode != null){
            str += currentNode.data + "\n";
            currentNode = currentNode.next;
        }
        return str;
    }

    private class Node{
        T data;
        Node prev;
        Node next;

        Node(T newEntry, Node prevNode){
            data = newEntry;
            prev = prevNode;
            next = null;
        }
    }

}