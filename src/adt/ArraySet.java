package adt;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

public class ArraySet<T extends Comparable<T>> implements SetInterface<T> {
    private T[] array;
    private int length;
    private final static int DEFAULT_CAPACITY = 50;

    public ArraySet(){
        this(DEFAULT_CAPACITY);
    }

    public ArraySet(int initialCapacity){
        length = 0;
        array = (T[]) new Comparable[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        if(contains(newEntry))
            return false;
        array[length] = newEntry;
        length++;
        return true;
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public boolean contains(T anEntry) {
        for(int i = 0 ; i < length ; i++){
            if(array[i] == anEntry)
                return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if(length == 0)
            return true;
        return false;
    }

    @Override
    public int size() {
        return length;
    }

//    @Override
//    public boolean remove(T anEntry) {
//        int position = findIndex(anEntry);
//
//        if(position<length){
//            removeGap(position);
//            length--;
//            return true;
//        }
//        else
//            return false;
//    }


    @Override
    public String toString() {
        String str = "";
        for (int index = 0; index < length; index++) {
            str += array[index] + "\n";
        }
        return str;
    }

    @Override
    public void reverse() {

    }
}
