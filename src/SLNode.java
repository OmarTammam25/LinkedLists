public class SLNode {
    /**
     * Element of the current node.
     */
    private Object element;
    /**
     * Reference to the next node.
     */
    private SLNode next;

    /**
     * Default constructor
     * @param element
     * @param next
     */
    public SLNode(Object element, SLNode next) {
        this.element = element;
        this.next = next;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public void setNext(SLNode next) {
        this.next = next;
    }

    public Object getElement() {
        return element;
    }

    public SLNode getNext() {
        return next;
    }
}
