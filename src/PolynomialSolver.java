public class PolynomialSolver implements IPolynomialSolver{
    private SingleLinkedList A = new SingleLinkedList();
    private SingleLinkedList B = new SingleLinkedList();
    private SingleLinkedList C = new SingleLinkedList();
    private SingleLinkedList R = new SingleLinkedList();



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
            } else{
                if(j==0){ // if first term
                        System.out.print(myList.get(j) + "x^" + i);
                }else {
                    if ((Integer) myList.get(j) > 0) { // if positive
                        if (i > 1) {
                            System.out.print("+" + myList.get(j) + "x^" + i);
                        } else if (i == 1) {
                            System.out.print("+" + myList.get(j) + "x");
                        } else {
                            System.out.print("+" + myList.get(j));
                        }
                    } else { // if negative
                        if (i > 1) {
                            System.out.print(myList.get(j) + "x^" + i);
                        } else if (i == 1) {
                            System.out.print(myList.get(j) + "x");
                        } else {
                            System.out.print(myList.get(j));
                        }
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
            case 'R':
                printHelper(R);
                break;
        }
        return null;
    }

    @Override
    public void clearPolynomial(char poly) {

    }

    public float evaluateHelper(SingleLinkedList l, float value){
        int[] exponent = new int[l.size()];
        // get exponents
        for(int i = l.size()-1, j = 0; i >= 0; i--, j++){
            exponent[j] = i;
        }
        float solution  = 0;
        for(int i = 0; i < l.size(); i++){
            solution += (Integer)l.get(i) * Math.pow(value, exponent[i]);
        }
        return solution;
    }
    @Override
    public float evaluatePolynomial(char poly, float value) {
        float sol = 0;
        switch (poly) {
            case 'A':
                sol=evaluateHelper(A, value);
                break;
            case 'B':
                sol=evaluateHelper(B, value);
                break;
            case 'C':
                sol=evaluateHelper(C, value);
                break;
        }
        return sol;
    }
    
    private int[][] addHelper(SingleLinkedList l1, SingleLinkedList l2, SingleLinkedList R){
        int[] exponent1 = new int[l1.size()];
        int[] exponent2 = new int[l2.size()];
        // get exponents
        for(int i = l1.size()-1, j = 0; i >= 0; i--, j++){
            exponent1[j] = i;
        }
        for(int i = l2.size()-1, j = 0; i >= 0; i--, j++){
            exponent2[j] = i;
        }

        int i=0, j=0;
        while(i < l1.size() || j < l2.size()){
            if(exponent1[i] > exponent2[j]){
                R.add(l1.get(i));
                i++;
            }else if(exponent1[i] < exponent2[j]){
                R.add(l2.get(j));
                j++;
            }else{
                R.add((Integer)l1.get(i) + (Integer)l2.get(j));
                i++;
                j++;
            }
        }

        while(i < l1.size()){
            R.add(l1.get(i));
            i++;
        }
        while(j < l2.size()){
            R.add(l2.get(j));
            j++;
        }
        int[][] sol = new int[1][R.size()];

        return listToTwoD(R, sol);
    }

    private int[][] listToTwoD(SingleLinkedList l, int[][] arr){
        for(int i = 0; i < l.size(); i++){
            arr[0][i] = (Integer)l.get(i);
        }
        return  arr;
    }
    @Override
    public int[][] add(char poly1, char poly2) {
        if(poly1 == 'A' && poly2 == 'A'){
           return addHelper(A, A, R);
        }else if(poly1 == 'A' && poly2 == 'B'){
            return addHelper(A, B, R);
        }else if(poly1 == 'A' && poly2 == 'C'){
            return addHelper(A, C, R);
        }else if(poly1 == 'B' && poly2 == 'A'){
            return addHelper(B, A, R);
        }else if(poly1 == 'B' && poly2 == 'B'){
            return addHelper(B, B, R);
        }else if(poly1 == 'B' && poly2 == 'C'){
            return addHelper(B, C, R);
        }else if(poly1 == 'C' && poly2 == 'A'){
            return addHelper(C, A, R);
        }else if(poly1 == 'C' && poly2 == 'B'){
            return addHelper(C, B, R);
        }else if(poly1 == 'C' && poly2 == 'C'){
            return addHelper(C, C, R);
        }
        return new int[0][];
    }



    private int[][] subHelper(SingleLinkedList l1, SingleLinkedList l2, SingleLinkedList R){
        int[] exponent1 = new int[l1.size()];
        int[] exponent2 = new int[l2.size()];
        // get exponents
        for(int i = l1.size()-1, j = 0; i >= 0; i--, j++){
            exponent1[j] = i;
        }
        for(int i = l2.size()-1, j = 0; i >= 0; i--, j++){
            exponent2[j] = i;
        }

        int i=0, j=0;
        while(i < l1.size() || j < l2.size()){
            if(exponent1[i] > exponent2[j]){
                R.add(l1.get(i));
                i++;
            }else if(exponent1[i] < exponent2[j]){
                R.add(l2.get(j));
                j++;
            }else{
                R.add((Integer)l1.get(i) - (Integer)l2.get(j));
                i++;
                j++;
            }
        }

        while(i < l1.size()){
            R.add(l1.get(i));
            i++;
        }
        while(j < l2.size()){
            R.add(l2.get(j));
            j++;
        }
        int[][] sol = new int[1][R.size()];

        return listToTwoD(R, sol);
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        if(poly1 == 'A' && poly2 == 'A'){
            return subHelper(A, A, R);
        }else if(poly1 == 'A' && poly2 == 'B'){
            return subHelper(A, B, R);
        }else if(poly1 == 'A' && poly2 == 'C'){
            return subHelper(A, C, R);
        }else if(poly1 == 'B' && poly2 == 'A'){
            return subHelper(B, A, R);
        }else if(poly1 == 'B' && poly2 == 'B'){
            return subHelper(B, B, R);
        }else if(poly1 == 'B' && poly2 == 'C'){
            return subHelper(B, C, R);
        }else if(poly1 == 'C' && poly2 == 'A'){
            return subHelper(C, A, R);
        }else if(poly1 == 'C' && poly2 == 'B'){
            return subHelper(C, B, R);
        }else if(poly1 == 'C' && poly2 == 'C'){
            return subHelper(C, C, R);
        }
        return new int[0][];
    }



    private int[][] multHelper(SingleLinkedList l1, SingleLinkedList l2, SingleLinkedList R){
        int[] exponent1 = new int[l1.size()];
        int[] exponent2 = new int[l2.size()];
        // get exponents
        for(int i = l1.size()-1, j = 0; i >= 0; i--, j++){
            exponent1[j] = i;
        }
        for(int i = l2.size()-1, j = 0; i >= 0; i--, j++){
            exponent2[j] = i;
        }

        int i=0, j=0;
        while(i < l1.size() || j < l2.size()){
            if(exponent1[i] > exponent2[j]){
                R.add(l1.get(i));
                i++;
            }else if(exponent1[i] < exponent2[j]){
                R.add(l2.get(j));
                j++;
            }else{
                R.add((Integer)l1.get(i) + (Integer)l2.get(j));
                i++;
                j++;
            }
        }

        while(i < l1.size()){
            R.add(l1.get(i));
            i++;
        }
        while(j < l2.size()){
            R.add(l2.get(j));
            j++;
        }
        int[][] sol = new int[1][R.size()];

        return listToTwoD(R, sol);
    }
    @Override
    public int[][] multiply(char poly1, char poly2) {
        return new int[0][];
    }
}
