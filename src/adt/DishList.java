package adt;
import java.io.Serializable;

public class DishList<T> implements DishListInterface<T>, Serializable {
    private final static int MAX_SIZE = 100;
    private T[] dishList;
    private int numberOfEntries;

    public DishList() {
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
    public boolean isFull() {
        return numberOfEntries == dishList.length;
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
