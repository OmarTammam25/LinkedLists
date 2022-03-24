public class driverCode {
    public static void main(String[] args) {
        // driver code
        SingleLinkedList myList = new SingleLinkedList();

        myList.add(2, "omar");
        myList.add("omar 2");
        myList.add("omar and moustafa") ;
        myList.set(2, "ana elly kan 3arfny");
        myList.clear();

        System.out.println("size of list = " + myList.getLength());
        printList(myList);
    }

    public static void printList(SingleLinkedList myList){
        Object temp;
        for (int i = 0; i < myList.getLength(); i++) {
            temp = myList.get(i);
            System.out.println(temp);
        }

    }
}
