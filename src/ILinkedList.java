public interface ILinkedList {

    /**
     * Inserts a specified element at the specified position in the list.
     * @param index The index you want to insert the node in
     * @param element
     */
    void add(int index, Object element);

    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */

    void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    void set(int index, Object element);
    /**
     * Removes all of the elements from this list.
     */
    void clear();
    /**
     * @return true if this list contains no elements.
     */
    boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    int size();
    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    ILinkedList sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    boolean contains(Object o);


}
