package adt;
import java.util.Iterator;

public interface SetInterface<T extends Comparable<T>> {

    public boolean add(T newEntry);
    /*
     * Description   - Adds the given element into the array.
     * Postcondition - The given element has been added into the array.
     * Return        - True if given element was successfully added to the array; false otherwise.
     */

    public void clear();
    /*
     * Description   - Clears all the elements in the array.
     * Postcondition - The array is cleared and become an empty array.
     */

    public boolean contains(T anEntry);
    /*
     * Description   - Checks whether the array contains the given element.
     * Postcondition - The array remains unchanged.
     * Return        - True if the array contains the specified element; false otherwise.
     */

    public boolean isEmpty();
    /*
     * Description   - Checks whether the array is empty or not.
     * Postcondition - The array remains unchanged.
     * Return        - True if the array is empty; false otherwise.
     */

    public int size();
    /*
     * Description   - Obtains the number of elements in the array.
     * Postcondition - The array remains unchanged.
     * Return        - The number of elements in the array.
     */

//    public boolean remove(T anEntry);
//    /*
//     * Description   - Checks whether the array is empty or not.
//     * Postcondition - The array remains unchanged.
//     * Return        - True if the array is empty; false otherwise.
//     */

    public void reverse();
}
