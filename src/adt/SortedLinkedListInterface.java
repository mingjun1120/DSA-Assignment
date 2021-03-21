package adt;

import java.util.Comparator;
import java.util.Iterator;

public interface SortedLinkedListInterface<T extends Comparable<T>>{

    boolean add (T newEntry);
    /*
     * Description   - Adds the given element into the list.
     * Postcondition - The given element has been added into the list.
     * Return        - True if given set was successfully added to the set; false otherwise.
     */

    void add (T[] newEntry);
    /*
     * Description   - Adds the given element into the list.
     * Postcondition - The given element has been added into the list.
     * Return        - True if given set was successfully added to the set; false otherwise.
     */

    boolean remove (T anEntry);
    /*
     * Description   - Removes the given element from the list.
     * Precondition  - The given elements exist in the list.
     * Postcondition - The given element has been removed from the list.
     * Return        - The element that was removed if it exists in the set; null otherwise.
     */

    boolean contains (T anEntry);
    /*
     * Description   - Checks whether the list contains the given element.
     * Postcondition - The list remains unchanged.
     * Return        - True if the list contains the specified element; false otherwise.
     */

    boolean isEmpty();
    /*
     * Description   - Checks whether the list is empty or not.
     * Postcondition - The list remains unchanged.
     * Return        - True if the list is empty; false otherwise.
     */

    void clear();
    /*
     * Description   - Clears all the elements in the list.
     * Postcondition - The list is cleared and become an empty list.
     */

    int getLength();
    /*
     * Description   - Obtains the number of elements in the list.
     * Postcondition - The list remains unchanged.
     * Return        - The number of elements in the list.
     */

    Iterator<T> getIterator();


}