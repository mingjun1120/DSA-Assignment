
package adt;


public interface StackInterface<T> {
    /**
     * task: adds a new entry to the top of the stack
     * @param newEntry an object to be added to the stack
     */
    public void push(T newEntry);
    /**
     *
     * task: removes and returns the stack's top entry
     *
     * @return either the object at the top of the stack or. if the stack is
     * empty before the operation null
     */


    public T pop();

    /**
     * task: retrieves the stack's top entry
     *
     * @return either the object at the top of the stack or null if the stack is
     * empty
     */

    public T peek();
    /**
     * task: detects whether the stack is empty
     *
     * @return true if the stack is empty
     */

    public boolean isEmpty();
    /**
     *
     * task: removes all entries from the stack
     */

    public void clear();
    /**
     * end stackInterface
     */
}
    

