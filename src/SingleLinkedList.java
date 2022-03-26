public class SingleLinkedList implements ILinkedList {
    private int length;
    private SLNode head;

    public SLNode getHead() {
        return head;
    }

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
        if(index < 0 || index > size()) {
            // if user inputs a negative index
            System.out.println("Error");
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
            while(temp.getNext() != null && i < index-1) {
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
        if(isEmpty() || index >= length || index < 0){
            System.out.println("Error");
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
            System.out.println("Error");
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
        SLNode current = head;
        SLNode delete_node;
        if(head == null || index >= length || index < 0) {
            System.out.println("Error");
        }else if(index == 0){ // delete from front
            head = head.getNext();
            current.setNext(null);
            length--;
        }else if(index == length-1){ // delete from tail
            for (int i = 0; i < index-1; i++){
                current = current.getNext();
            }
            current.setNext(null);
            length--;
        }else{ // delete from middle
            // iterate through the list until index - 1 node
            for (int i = 0; i < index-1; i++){
                current = current.getNext();
            }
            delete_node = current.getNext();
            current.setNext(delete_node.getNext()); // change the k-1 node to point to the node after the deleted one
            delete_node.setNext(null);
            length--;
        }
    }

    @Override
    public int size() {
        //TODO delete this function. redundant.
        if(isEmpty())
            return 0;
        return length;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        SingleLinkedList myList = new SingleLinkedList();
        SLNode temp = head;
        // iterate until first node
        if(head == null || fromIndex > toIndex || fromIndex < 0 || toIndex < 0 || toIndex >= length || fromIndex >= length){
            System.out.println("Error");
            return null;
        }else {
            for (int i = 0; i < fromIndex; i++) {
                temp = temp.getNext();
            }
            //iterate until last index
            for (int i = fromIndex; i <= toIndex; i++) {
                myList.add(temp.getElement());
                temp = temp.getNext();
            }
            return myList;
        }
    }

    @Override
    public boolean contains(Object o) {
        SLNode current = head;
        for (int i =0; i < size(); i++) {
            if(o == current.getElement()){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void printList(SingleLinkedList myList){
        Object temp;
        if(myList == null){
            System.out.println("Error");
        }else{
            for (int i = 0; i < myList.getLength(); i++) {
                temp = myList.get(i);
                System.out.println(temp);
            }
        }
    }
}
