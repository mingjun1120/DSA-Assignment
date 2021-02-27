package adt;

public interface ListInterface<T> {

    void add(T newEntry);
    /*
     * Description   - add a type T object into the list
     * Precondition  - list is not full
     * Postcondition - a new type T object will be added into the list
     */

    T remove(int givenPosition);
    /*
     * Description   - remove an object from the list
     * Precondition  - list is not full
     * Postcondition - The selected object has been removed from the list
     */

    T getEntry(int givenPosition);
    /*
     * Description   - get an object from the list
     * Precondition  - position must greater than 0 and smaller and equal to the list size
     * Postcondition - list remain unchanged
     * Return        - return an object of type T; null otherwise
     */

    boolean isEmpty();
    /*
     * Description   - check the list is empty or not
     * Postcondition - list remain unchanged
     * Return        - return true if the list is empty; false otherwise
     */

    int getLength();
    /*
     * Description   - get the size of the list
     * Postcondition - list remain unchanged
     * Return        - return the size of the list
     */

    void clear();
}
