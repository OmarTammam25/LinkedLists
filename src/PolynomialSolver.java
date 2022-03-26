public class PolynomialSolver implements IPolynomialSolver{
    private SingleLinkedList A = new SingleLinkedList();
    private SingleLinkedList B = new SingleLinkedList();
    private SingleLinkedList C = new SingleLinkedList();


    @Override
    public void setPolynomial(char poly, int[][] terms) {
        switch (poly){
            case'A':
                for(int i : terms[0]){
                    A.add(i);
                }
                break;
            case'B':
                for(int i : terms[0]){
                    B.add(i);
                }
                break;
            case'C':
                for(int i : terms[0]){
                    C.add(i);
                }
                break;
        }
    }

    private void printHelper(SingleLinkedList myList){
        for (int i = myList.size()-1, j = 0; i>=0; i--, j++){
            if(myList.get(j) == (Integer)(1)){ // If term = 1
                if(j==0){ // if first term
                    if(i > 1)
                        System.out.print("x^" + i);
                    else if (i == 1){ // if power 1
                        System.out.print("x");
                    }
                }else{
                    System.out.print("+x^" + i);
                }
            } else{ // other terms
                if((Integer)myList.get(j) > 0){ // if positive
                    if(i > 1){
                        System.out.print("+" + myList.get(j) + "x^" + i);
                    }else if (i == 1){
                        System.out.print("+" + myList.get(j) + "x");
                    } else{
                        System.out.print("+" + myList.get(j));
                    }
                }else{ // if negative
                    if(i>1){
                        System.out.print(myList.get(j) + "x^" + i);
                    }else if (i == 1){
                        System.out.print(myList.get(j) + "x");
                    } else{
                        System.out.print(myList.get(j));
                    }
                }
            }
        }
        System.out.println();
    }

    @Override
    public String print(char poly) {
        switch (poly){
            case 'A':
                printHelper(A);
                break;
            case 'B':
                printHelper(B);
                break;
            case 'C':
                printHelper(C);
                break;
        }
        return null;
    }

    @Override
    public void clearPolynomial(char poly) {

    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        return 0;
    }

    
    private int[][] addHelper(SingleLinkedList l1, SingleLinkedList l2){

        return new int[0][];
    }
    @Override
    public int[][] add(char poly1, char poly2) {
        
        return new int[0][];
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        return new int[0][];
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        return new int[0][];
    }
}
