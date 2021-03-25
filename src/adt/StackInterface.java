package adt;
import java.util.Iterator;

public interface StackInterface<T> {

    public void push(T newEntry);
    /*
     * Description   - Adds a new entry to the top of the stack
     * Precondition  - None
     * Postcondition - The new entry is added at the top of the stack
     * Return        - None
     */

    public T pop();
    /*
     * Description   - Retrieve and remove the top entry out of the stack
     * Precondition  - Check if the entry is in the stack
     * Postcondition - The entry on the top has been removed from the stack
     * Return        - The entry on the top of the stack
     */

    public T peek();
    /*
     * Description   - Retrieve the top entry in the stack
     * Precondition  - Check if the entry is in the stack
     * Postcondition - Stack remains unchanged
     * Return        - The entry on the top of the stack
     */

    public boolean isEmpty();
    /*
     * Description   - Check whether the stack is empty
     * Precondition  - None
     * Postcondition - Stack remains unchanged
     * Return        - True if the stack is empty; False otherwise.
     */

    public void clear();
    /*
     * Description   - Removes all entries from the stack.
     * Precondition  - None
     * Postcondition - The stack become empty
     * Return        - None
     */

    Iterator<T> getIterator();
    /*
     * Description   - Iterate through the stack and save each entry in the stack.
     * Precondition  - The stack is not null.
     * Postcondition - The stack remains unchanged.
     * Return        - An iterator contains all the entries in the stack.
     */
}
    

