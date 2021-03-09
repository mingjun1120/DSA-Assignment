package adt;
import java.io.Serializable;

public class ArrayList<T> implements ArrayListInterface<T>, Serializable {
    private final static int MAX_SIZE = 5;
    private T[] dishList;
    private int numberOfEntries;
    private static final long serialVersionUID = -3896112140209065945L;

    public ArrayList() {
        dishList = (T[]) new Object[MAX_SIZE];
        numberOfEntries = 0;
    }

    @Override
    public void add(T newEntry) {
        if (!isFull()){
            dishList[numberOfEntries] = newEntry; // numberOfEntries is the index for the next entry
            numberOfEntries++;
        } else {
            doubleSize();
            add(newEntry);
        }
    }

    @Override
    public T remove(int givenPosition) {
        if (!isEmpty()) {
            if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
                T removedEntry = dishList[givenPosition - 1];
                numberOfEntries--;
                for (int i = givenPosition; i <= numberOfEntries; i++) {
                    dishList[i - 1] = dishList[i];
                }
                return removedEntry;
            }
        }
        return null;
    }

    @Override
    public T getEntry(int givenPosition) {
        if (!isEmpty()) {
            if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
                return dishList[givenPosition - 1];
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == dishList.length;
    }

    @Override
    public T[] getAllEntries() {
        T[] temp = (T[]) new Object[numberOfEntries];
        if (numberOfEntries >= 0) System.arraycopy(dishList, 0, temp, 0, numberOfEntries);
        return temp;
    }

    @Override
    public void doubleSize(){
        T[] tempDishList = (T[]) new Object[dishList.length * 2];

        for (int i = 0; i < numberOfEntries; i++){
            tempDishList[i] = dishList[i];
        }
        dishList = tempDishList;
    }
}
