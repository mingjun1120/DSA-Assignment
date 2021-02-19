package adt;

public interface DishListInterface<T> {

    void add(T newEntry);
    /*
     * Description   - add a type T object into dish list
     * Precondition  - dish list is not full
     * Postcondition - a new type T object will be added into dish list
     */

    T remove(int givenPosition);
    /*
     * Description   - remove an object from dish list
     * Precondition  - dish list is not full
     * Postcondition - The selected object  has been removed from the dish list
     */

    T getEntry(int givenPosition);
    /*
     * Description   - get an object from dish list
     * Precondition  - position must greater than 0 and smaller and equal to dish list size
     * Postcondition - dish list remain unchanged
     * Return        - return an object of type T
     */

    boolean isEmpty();
    /*
     * Description   - check the dish list is empty or not
     * Postcondition - dish list remain unchanged
     * Return        - return true if dish list is empty; false otherwise
     */

    int getLength();
    /*
     * Description   - get the size of dish list
     * Postcondition - dish list remain unchanged
     * Return        - return the size of the dish list
     */

    boolean isFull();
    /*
     * Description   - check the dish list is empty or not
     * Postcondition - dish list remain unchanged
     * Return        - return true if dish list is empty; false otherwise
     */


    void doubleSize();
    /*
     * Description   - double the size of dish list
     * Precondition  - dish list remain unchanged
     * Postcondition - size of dish list become double
     */
}
