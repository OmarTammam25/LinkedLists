import java.util.*;
interface ILinkedList {
    /**
    * Inserts a specified element at the specified position in the list.
    * @param index
    * @param element
    */
    public void add(int index, Object element);
    /**
    * Inserts the specified element at the end of the list.
    * @param element
    */
    public void add(Object element);
    /**
    * @param index
    * @return the element at the specified position in this list.
    */
    public Object get(int index);
    
    /**
    * Replaces the element at the specified position in this list with the
    * specified element.
    * @param index
    * @param element
    */
    public void set(int index, Object element);
    /**
    * Removes all of the elements from this list.
    */
    public void clear();
    /**
    * @return true if this list contains no elements.
    */
    public boolean isEmpty();
    /**
    * Removes the element at the specified position in this list.
    * @param index
    */
    public void remove(int index);
    /**
    * @return the number of elements in this list.
    */
    public int size();
    /**
    * @param fromIndex
    * @param toIndex
    * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
    */
    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
    * @param o
    * @return true if this list contains an element with the same value as the specified element.
    */
    public boolean contains(Object o);
    }

public class DoubleLinkedList {
    
public static class DLLNode {
    private Object element;
    private DLLNode prevNode;
    private DLLNode nextNode;

    public DLLNode(Object element,DLLNode prevNode,DLLNode nextNode){
        this.element=element;
        this.prevNode=prevNode;
        this.nextNode=nextNode;
    }
    // return the element of this node
    public Object getElement(){return element;}

    // return the pravious node of this node 
    public DLLNode getPrev(){return prevNode;}

    // return the next node of this node 
    public DLLNode getNext(){return nextNode;}

    // set the element of this node
    public void setElement(Object newElement){element=newElement;}

    // set the pravious node of this node 
    public void setPrev(DLLNode newNode){prevNode=newNode;}

    // set the next node of this node
    public void setNext(DLLNode newNode){nextNode=newNode;}
}

public static class DLL implements ILinkedList {
    DLLNode head =new DLLNode(null,null,null);
    DLLNode tail =new DLLNode(null,head,null);
    private int size;


    //public void DLL(){
        public DLL(){
            head.setNext(tail);
            tail.setPrev(head);
            size =0;
        }

        public void add(int index, Object element){
            if (index>size||index<0) {
                System.out.println("Error");
                return;
            }
            DLLNode getnode=head;//.getNext();//variable to iterate till the required index
            for (int i=0;i<index;i++){
                getnode=getnode.getNext();
            }
            DLLNode newNode=new DLLNode(element,null,getnode.getNext());
            newNode.setPrev(getnode);
            getnode.getNext().getNext().setPrev(newNode);
            getnode.setNext(newNode);
            size++;
        }

        public void add(Object element){
            DLLNode getnode=tail.getPrev();
            DLLNode newnode=new DLLNode(element,getnode,tail);
            tail.setPrev(newnode);
            getnode.setNext(newnode);
            size++;
        }

        public Object get(int index){
            if (index>size-1||index<0) {
                System.out.println("Error");
                return null;
            }
            else{
                DLLNode getnode = head.getNext();
                for(int i=0;i<index;i++){
                    getnode=getnode.getNext();
                }
                return getnode.getElement();
            }
        }

        public void set(int index, Object element){
            if (index>size-1||index<0) {
                System.out.println("Error");
                return;
            }
            DLLNode getnode = head.getNext();
            for(int i=0;i<index;i++){
                getnode=getnode.getNext();
            }
            getnode.setElement(element);
        }

        public void clear(){
            head.setNext(tail);
            tail.setPrev(head);
            size=0;
        }

        public boolean isEmpty(){
            return (head.getNext()==tail);//or// return (size!=0);
        }

        public void remove(int index){
            if (index>size-1||index<0) {
                System.out.println("Error");
                return ;
            }
            DLLNode getnode = head;
            for(int i=0;i<index;i++){
                getnode=getnode.getNext();
            }
            getnode.setNext(getnode.getNext().getNext());
            getnode.getNext().setPrev(getnode);
            size--;
        }

        public int size(){
            return size;
        }

        public DLL sublist(int fromIndex, int toIndex){//ILinkedList
            if (head.nextNode==tail || fromIndex>toIndex || fromIndex<0 || toIndex>size-1) {
                System.out.println("Error");//get error exception
                return null;
            }
            DLLNode getnode = head.getNext();
            for(int i=0;i<fromIndex;i++){
                getnode=getnode.getNext();
            }
            DLL newDLL=new DLL();
            int newsize=toIndex-fromIndex;
            for(int j =0;j<=newsize;j++){
                newDLL.add(getnode);
                newDLL.set(j,getnode.getElement());
                getnode=getnode.getNext();
            }
            return newDLL;
        }

        public boolean contains(Object o){
            boolean found=false;
            DLLNode getnode=head;
            for(int i=0;i<size;i++){
                getnode=getnode.getNext();
                found=getnode.getElement().equals(o.toString())?true:false;
                if(found){
                    return true;
                }
            }
            return false;
        }
        
        public void print(DLL dll,boolean arrFormat){
            if(arrFormat){
                System.out.print("[");
                if(dll!=null){
                    for(int i=0;i<dll.size;i++){
                        System.out.print(dll.get(i));
                        if(i!=dll.size-1) System.out.print(", ");
                    }
                }
                System.out.println("]");
            }
            else{
                if(dll!=null){
                    for(int i=0;i<dll.size;i++){
                        System.out.print(dll.get(i)+" ");
                    }
                }
                System.out.println();
            }

        }
}

    
    public static void main(String[] args) {
        Scanner scn =new Scanner(System.in);
        while(true){
        String[] li =scn.nextLine().replaceAll("\\[|\\]|\\ ", "").split(",");
        DLL dll=new DoubleLinkedList.DLL();
        for (int i=0;i<li.length;i++){
            if(!li[i].equals(""))
            dll.add(li[i]);
        }
        
        String operation =scn.nextLine();
        int ind;
        int val;
        switch(operation){
            case "add":
                val =Integer.parseInt(scn.nextLine());
                dll.add(val);

                dll.print(dll,true);
                break;
            
            case "addToIndex":
                ind =Integer.parseInt(scn.nextLine());
                val =Integer.parseInt(scn.nextLine());
                dll.add(ind,val);
                if (!(ind>dll.size||ind<0)) {
                    dll.print(dll,true);//print after error
                }
                
                break;
            
            case "get":
                ind=Integer.parseInt(scn.nextLine());
                Object result=dll.get(ind);
                if (!(ind>dll.size-1||ind<0)) {
                    System.out.println(result);
                }
                 
                break;
        
            case "set":
                ind =Integer.parseInt(scn.nextLine());
                val =Integer.parseInt(scn.nextLine());
                dll.set(ind,val);
                if (!(ind>dll.size-1||ind<0)) {
                    dll.print(dll,true);
                }
                
                break;
    
            case "clear":
                dll.clear();
                dll.print(dll,true);
                break;

            case "isEmpty":
                System.out.println((dll.isEmpty()?"True":"False"));
                break;
            
            case "remove":
                ind = Integer.parseInt(scn.nextLine());
                int sizeBeforRemove=dll.size;
                dll.remove(ind);
                if (!(ind>sizeBeforRemove-1||ind<0)) {
                    dll.print(dll,true);
                }
                
                break;
        
            case "sublist":
                int first_ind =Integer.parseInt(scn.nextLine());
                int second_ind =Integer.parseInt(scn.nextLine());
                DLL sub= dll.sublist(first_ind,second_ind);
                if (!(dll.head.nextNode==dll.tail || first_ind>second_ind || first_ind<0 || second_ind>dll.size-1)) {
                    dll.print(sub,true);
                }
                
                break;
    
            case "contains":
                val =Integer.parseInt(scn.nextLine());
                System.out.println((dll.contains(val)?"True":"False"));
                break;
            case "size":
                System.out.println(dll.size());
                break;
            
        }
        dll.clear();

    }
    }
    
}
