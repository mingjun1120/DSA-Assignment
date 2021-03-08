package adt;
import java.io.Serializable;
import java.util.Iterator;

public class ArraySet<T> implements SetInterface<T>, Serializable {
    private final static int DEFAULT_CAPACITY = 50;
    private T[] array;
    private int numberOfEntries;

    public ArraySet() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        numberOfEntries = 0;
    }

    @Override
    public boolean add(T newElement) {
        if(!isFull()){
            boolean exist = contains(newElement);
            if (!exist){
                array[numberOfEntries++] = newElement;
                return true;
            }
        } else {
            doubleSize();
            add(newElement);
        }
        return false;
    }

    @Override
    public boolean remove(T anElement) {
        if (contains(anElement)){
            int i = 0;
            boolean found = false;
            while (i < numberOfEntries && !found) {
                if (array[i].equals(anElement))
                    found = true;
                else
                    i++;
            }
            numberOfEntries--;
            for (int j = i; j < numberOfEntries; j++){
                array[j] = array[j + i];
            }
            return true;
        }
        return false;
    }

    @Override
    public T getEntry(int givenPosition) {
        if (!isEmpty()) {
            if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
                return array[givenPosition - 1];
            }
        }
        return null;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean checkSubset(SetInterface anotherSet) {
        ArraySet<T> givenSet = (ArraySet<T>)anotherSet;
        boolean exist = true;
        for(int i = 0; i < givenSet.numberOfEntries && exist; i++){
            exist = contains(givenSet.array[i]);
        }
        return exist;
    }

    @Override
    public void union(SetInterface anotherSet) {
        ArraySet<T> givenSet = (ArraySet<T>)anotherSet;
        for (int i = 0; i < givenSet.numberOfEntries; i++){
            add(givenSet.array[i]);
        }
    }

    @Override
    public SetInterface intersection(SetInterface anotherSet) {
        ArraySet<T> givenSet = (ArraySet<T>)anotherSet;
        ArraySet<T> resultSet = new ArraySet<>();
        for(int i = 0; i < givenSet.numberOfEntries; i++){
            boolean exist = false;
            for (int j = 0; j < numberOfEntries && !exist; j++){
                if (givenSet.array[i].equals(array[j])){
                    exist = true;
                    resultSet.add(givenSet.array[i]);
                }
            }
        }
        return resultSet;
    }

    @Override
    public boolean isEmpty() { return numberOfEntries == 0; }

    @Override
    public boolean isFull() { return numberOfEntries == array.length; }

    public boolean contains(T newElement) {
        boolean exist = false;
        int i = 0;
        while (i < numberOfEntries && !exist){
            if(array[i].equals(newElement))
                exist = true;
            else
                i++;
        }
        return exist;
    }

    @Override
    public void doubleSize() {
        T[] tempDishList = (T[]) new Object[array.length * 2];

        for (int i = 0; i < numberOfEntries; i++){
            tempDishList[i] = array[i];
        }
        array = tempDishList;
    }

    @Override
    public Iterator<T> getIterator() {
        return new SetIterator();
    }

    private class SetIterator implements Iterator<T> {
        private int iteratorIndex = 0;

        @Override
        public boolean hasNext() {
            return iteratorIndex < numberOfEntries;
        }

        @Override
        public T next() {
            if (hasNext()){
                return array[iteratorIndex++];
            }
            return null;
        }
    }
}
