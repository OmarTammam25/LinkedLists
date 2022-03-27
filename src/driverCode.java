import java.util.Scanner;

public class driverCode {
    public static void main(String[] args) {
        // take input and put it in myList
        Scanner sc = new Scanner(System.in);
        SingleLinkedList myList = new SingleLinkedList();
        String input = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] nums = input.split(", ");

        if(!nums[0].isEmpty()){
            for(String i : nums){
                myList.add(Integer.parseInt(i));
            }
        }

        String operation = sc.nextLine();
        switch (operation){
            case "add":
                int index = sc.nextInt();
                myList.add(index);
                printListBrackets(myList);
                break;
            case "addToIndex":
                int index2 = sc.nextInt();
                int value = sc.nextInt();
                myList.add(index2, value);
                if(index2 >= 0 && index2 <= myList.size()){
                    printListBrackets(myList);
                }
                break;
            case "get":
                Object input_get = myList.get(sc.nextInt());
                if(input_get != null)
                    System.out.println(input_get);
                break;
            case "set":
                int set_index = sc.nextInt();
                int set_value = sc.nextInt();
                if(myList.isEmpty() || set_index<0 || set_index >= myList.getLength()){
                    myList.set(set_index, set_value);
                } else {
                    myList.set(set_index, set_value);
                    printListBrackets(myList);
                }
                break;
            case "clear":
                myList.clear();
                printListBrackets(myList); // TODO see if this works
                break;
            case "isEmpty":
                if(myList.isEmpty())
                    System.out.println("True");
                else
                    System.out.println("False");
                break;
            case "remove":
                int index_remove = sc.nextInt();
                if(myList.isEmpty() || index_remove >= myList.getLength() || index_remove < 0) {
                    System.out.println("Error");
                    return;
                }else{
                    myList.remove(index_remove);
                    printListBrackets(myList);
                }
                break;
            case "sublist":
                SingleLinkedList newList =(SingleLinkedList) myList.sublist(sc.nextInt(), sc.nextInt());
                if(newList != null){
                    printListBrackets(newList);
                }
                break;
            case "contains":
                if(myList.contains(sc.nextInt())){
                    System.out.println("True");
                }else{
                    System.out.println("False");
                }
                break;
            case "size":
                System.out.println(myList.size());
                break;
        }
    }
    public static void printListBrackets(SingleLinkedList myList){
        Object temp;
        if(myList == null || myList.size() == 0){
            System.out.print("[]");
        }else{
            System.out.print("[");
            for (int i = 0; i < myList.getLength(); i++) {
                temp = myList.get(i);
                if(myList.getLength() - i == 1){
                    System.out.print(temp + "]");
                    break;
                }
                System.out.print(temp + ", ");
            }
        }

    }
}
