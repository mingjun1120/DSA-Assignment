package adt;

public interface OrderListInterface<T> extends ListInterface<T> {
    void offer(T newEntry);
    /*
     * Description   - add a type T object into the list
     * Precondition  - dish list is not full
     * Postcondition - a new type T object will be added into dish list
     */

    T getFront();
    /*
     * Description   - Retrieve the first T object
     * Precondition  - List is not empty
     * Postcondition - List remains unchanged
     * Return        - T Object at the front; null otherwise if the list is empty
     */

    T getLast();
    /*
     * Description   - Retrieve the last T object
     * Precondition  - List is not empty
     * Postcondition - List remains unchanged
     * Return        - T Object at the back; null otherwise if the list is empty
     */



}
