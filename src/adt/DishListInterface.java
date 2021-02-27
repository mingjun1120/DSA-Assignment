package adt;

public interface DishListInterface<T> extends ListInterface<T>{

    void doubleSize();
    /*
     * Description   - double the size of dish list
     * Precondition  - dish list remain unchanged
     * Postcondition - size of dish list become double
     */

    boolean isFull();
    /*
     * Description   - check the list is empty or not
     * Postcondition - list remain unchanged
     * Return        - return true if the list is empty; false otherwise
     */
}
