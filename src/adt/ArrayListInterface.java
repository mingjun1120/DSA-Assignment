package adt;

public interface ArrayListInterface<T> extends ListInterface<T>{

    void doubleSize();
    /*
     * Description   - double the size of array list
     * Precondition  - array list remain unchanged
     * Postcondition - size of array list become double
     */

    boolean isFull();
    /*
     * Description   - check the list is empty or not
     * Postcondition - list remain unchanged
     * Return        - return true if the list is empty; false otherwise
     */
}
