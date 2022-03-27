import java.util.Scanner;

public class testPoly {
    public static void main(String[] args) {
        PolynomialSolver myObj = new PolynomialSolver();

        Scanner sc = new Scanner(System.in);
        String operation = sc.nextLine();
        while(true){
            switch (operation){
                case "set":
                    char myList = sc.nextLine().charAt(0);
                    String input = sc.nextLine().replaceAll("\\[|\\]", "");
                    String[] nums = input.split(",");
                    if(nums[0].length() == 0){
                        System.out.println("Error");
                        return;
                    }
                    myObj.setPolynomial(myList, oneToTwoD(nums));
                    break;
                case "print":
                    char myList_print = sc.nextLine().charAt(0);
                    myObj.print(myList_print);
                    break;
                case "add":
                    char add1 = sc.nextLine().charAt(0);
                    char add2 = sc.nextLine().charAt(0);
                    myObj.add(add1, add2);
                    break;
                case "sub":
                    char sub1 = sc.nextLine().charAt(0);
                    char sub2 = sc.nextLine().charAt(0);
                    myObj.subtract(sub1, sub2);
                    break;
                case "mult":
                    char mult1 = sc.nextLine().charAt(0);
                    char mult2 = sc.nextLine().charAt(0);
                    myObj.multiply(mult1, mult2);
                    break;
                case "clear":
                    char in = sc.nextLine().charAt(0);
                    myObj.clearPolynomial(in);
                    break;
                case "eval":
                    char inEv = sc.nextLine().charAt(0);
                    float val = sc.nextFloat();
                    float ans = myObj.evaluatePolynomial(inEv, val);
                    if(ans == -999999){
                        break;
                    }
                    System.out.println((int) ans);
                    break;
                default:
                    System.out.println("Error");
                    break;

            }
            try{
                if(sc.hasNextLine() == true)
                    operation = sc.nextLine();
            } catch (java.util.NoSuchElementException e){
                return;
            }
        }

    }

        public static int[][] oneToTwoD(String[] arr){
        if (arr.length == 0){
            System.out.println("Error");
            return new int[0][];
        }
        int[][] sol = new int[1][arr.length];
        int j = 0;
        for(String i : arr){
            sol[0][j++] = Integer.parseInt(i);
        }
        return sol;
    }
}
