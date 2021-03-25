package adt;
import java.util.Iterator;

public interface QueueInterface<T> {

    int size();
    /*
     * Description   - Tell the current number of entries in this queue
     * Precondition  - None
     * Postcondition - None
     * Return        - the current number of entries in this queue
     **/

    void enqueue(T newEntry);
    /*
     * Description   - Insert an object of type T into the queue
     * Postcondition - T object is inserted at the last of the queue
     * Return        - None
     */

    T dequeue();
    /*
     * Description   - Retrieve the first T object and remove it from the queue
     * Precondition  - Queue is not empty
     * Postcondition - The first T object is removed, the subsequent object is moved forward
     * Return        - T Object at the front
     */

    T getFront();
    /*
     * Description   - Retrieve the first T object
     * Precondition  - Queue is not empty
     * Postcondition - Queue remains unchanged
     * Return        - T Object at the front; null otherwise if the queue is empty
     */

    T getLast();
    /*
     * Description   - Retrieve the first T object
     * Precondition  - Queue is not empty
     * Postcondition - Queue remains unchanged
     * Return        - T Object at the front; null otherwise if the queue is empty
     */

    boolean remove(T t);
    /*
     * Description   - Remove the object of type T from the queue
     * Precondition  - None
     * Postcondition - The object of type T is removed
     * Return        - True if it is removed, otherwise false
     */

    boolean isEmpty();
    /*
     * Description   - Check if the queue is empty
     * Precondition  - None
     * Postcondition - Queue remains unchanged
     * Return        - True if the queue is empty, otherwise false
     */

    void clear();
    /*
     * Description   - Removes all entries from the queue.
     * Precondition  - None
	 * Postcondition - The queue become empty
	 * Return        - None
	 */

    T[] getAllEntries();
    /*
     * Description   - Retrieves all entries from the queue into an array.
     * Precondition  - The queue is not empty
     * Postcondition - None
     * Return        - An array of all T object in the queue
     */

    Iterator<T> getIterator();
    /*
     * Description   - Iterate through the queue and save each entry in the queue.
     * Precondition  - The queue is not null.
     * Postcondition - The queue remains unchanged.
     * Return        - An iterator contains all the entries in the queue.
     */
}
