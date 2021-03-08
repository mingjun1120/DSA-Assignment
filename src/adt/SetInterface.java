package adt;
import java.util.Iterator;

public interface SetInterface<T> {

    boolean add(T newElement);
    boolean remove(T anElement);
    T getEntry(int givenPosition);
    int getLength();
    boolean checkSubset(SetInterface anotherSet);
    void union(SetInterface anotherSet);
    SetInterface intersection(SetInterface anotherSet);
    boolean isEmpty();
    boolean isFull();
    void doubleSize();
    Iterator<T> getIterator();
}
