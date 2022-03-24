public class SingleLinkedList implements ILinkedList {
    private int length;
    private SLNode head;

    public SingleLinkedList() {
        head = null;
        length = 0;
    }

    public int getLength() {return length;}

    @Override
    public void add(int index, Object element) {
        // create a new node
        SLNode node = new SLNode(element, null);
        // Reference to head node, made to iterate the list
        SLNode temp = head;
        int i=0; // iterator
        if(index < 0) {
            // if user inputs a negative index
            // TODO output a message if user inputs a negative index.
        }else if(isEmpty()) {
            head = node;
            length++;
        }else if(index == 0) {
            // add from the start of the list
            node.setNext(head);
            head = node;
            length++;
        }else{
             // iterate until the index - 1
            while(temp.getNext() != null && i < index) {
                temp = temp.getNext();
                i++;
            }
            node.setNext(temp.getNext());
            temp.setNext(node);
            length++;
        }
    }

    @Override
    public void add(Object element) {
        add(length, element);
    }

    @Override
    public Object get(int index) {
        SLNode temp = head;
        int i = 0;
        if(isEmpty()){
            //TODO generate error
            return null;
        }else{
            while(temp.getNext()!=null && i < index){
                temp = temp.getNext();
                i++;
            }
            return temp.getElement();
        }
    }

    @Override
    public void set(int index, Object element) {
        SLNode temp = head;
        int i = 0;
        if(isEmpty() || index<0 || index >= length){
            //TODO generate error
        }else{
            while(temp.getNext() != null && i < index){
                temp = temp.getNext();
                i++;
            }
            temp.setElement(element);
        }
    }

    @Override
    public void clear() {
        head = null;
        length = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public int size() {
        //TODO delete this function. redundant.
        return length;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }
}
