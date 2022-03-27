public class PolynomialSolver implements IPolynomialSolver{


    private SingleLinkedList A = new SingleLinkedList();
    private SingleLinkedList B = new SingleLinkedList();
    private SingleLinkedList C = new SingleLinkedList();

    private SingleLinkedList R = new SingleLinkedList();



    @Override
    public void setPolynomial(char poly, int[][] terms) {
        if(terms[0].length == 0){
            System.out.println("Error");
            return;
        }
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
            default:
                System.out.println("Error");
        }
    }

    private void printHelper(SingleLinkedList myList){
        if (myList.isEmpty()){
            System.out.println("Error");
            return;
        }else if((Integer)myList.get(0) == 0){
            System.out.print("0");
            return;
        }
        for (int i = myList.size()-1, j = 0; i>=0; i--, j++){
            if(myList.get(j) == (Integer)(1)){ // If term = 1
                if(j==0){ // if first term
                    if(i > 1)
                        System.out.print("x^" + i);
                    else if (i == 1){ // if power 1
                        System.out.print("x");
                    } else{
                        System.out.print("1");
                    }
                }else{
                    System.out.print("+x^" + i);
                }
            }else if(myList.get(j) == (Integer)(-1)){
                if(i > 1)
                    System.out.print("-x^" + i);
                else if (i == 1){ // if power 1
                    System.out.print("-x");
                } else{
                    System.out.print("-1");
                }
            }else{
                if(j==0){ // if first term
                    if(i > 1)
                        System.out.print(myList.get(j) + "x^" + i);
                    else if (i == 1){ // if power 1
                        System.out.print(myList.get(j) + "x");
                    } else{
                        System.out.print(myList.get(j));
                    }
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
            default:
                System.out.println("Error");
        }
        return null;
    }

    @Override
    public void clearPolynomial(char poly) {
        switch (poly){
            case'A':
                A.clear();
                System.out.println("[]");
                break;
            case'B':
                B.clear();
                System.out.println("[]");
                break;
            case'C':
                C.clear();
                System.out.println("[]");
                break;
            default:
                System.out.println("Error");
        }

    }

    public float evaluateHelper(SingleLinkedList l, float value){
        if(l.isEmpty()) {
            System.out.println("Error"); // TODO do this in main
            return -999999;
        }
        int[] exponent = new int[l.size()];
        // get exponents
        for(int i = l.size()-1, j = 0; i >= 0; i--, j++){
            exponent[j] = i;
        }
        float solution  = 0;
        for(int i = 0; i < l.size(); i++){
            if(exponent[i] == 0){
                solution += (Integer)l.get(i);
                break;
            }
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
            default:
                System.out.println("Error"); //TODO do it in main
                return -999999;
        }
        return sol;
    }
    
    private int[][] addHelper(SingleLinkedList l1, SingleLinkedList l2, SingleLinkedList R){
        if(l1.isEmpty() || l2.isEmpty()){
            System.out.println("Error");
            return new int[0][];
        }
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
        printHelper(R);
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
        } else {
            System.out.println("Error");
        }
        return new int[0][];
    }



    private int[][] subHelper(SingleLinkedList l1, SingleLinkedList l2, SingleLinkedList R){
        if(l1.isEmpty() || l2.isEmpty()){
            System.out.println("Error");
            return new int[0][];
        }
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
        printHelper(R);
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
        }else{
            System.out.println("Error");
        }
        return new int[0][];
    }



    private int[][] multHelper(SingleLinkedList l1, SingleLinkedList l2){
        if(l1.isEmpty() || l2.isEmpty()){
            System.out.println("Error");
            return new int[0][];
        }
        int[] exponent1 = new int[l1.size()];
        int[] exponent2 = new int[l2.size()];
        SingleLinkedList exponentR = new SingleLinkedList();
        // get exponents
        for(int i = l1.size()-1, j = 0; i >= 0; i--, j++){
            exponent1[j] = i;
        }
        for(int i = l2.size()-1, j = 0; i >= 0; i--, j++){
            exponent2[j] = i;
        }
            SingleLinkedList temp = new SingleLinkedList();
            SingleLinkedList expTemp = new SingleLinkedList();
        for (int i =0; i < l1.size(); i++){
            for (int j = 0; j < l2.size(); j++){
                temp.add((Integer)l1.get(i) * (Integer)l2.get(j));
                expTemp.add(exponent1[i] + exponent2[j]);
            }
        }
        // add
        SingleLinkedList sol = new SingleLinkedList();
        SingleLinkedList expSol = new SingleLinkedList();

        int exp = (Integer)expTemp.get(0);
        for(int i = 0; i < expTemp.size(); i++){
            int sum = 0;
            for(int j = 0; j< expTemp.size(); j++){
                if(exp == (Integer)expTemp.get(j)){
                    sum+= (Integer)temp.get(j);
                }
            }
            if(sum == 0){
                exp--;
                continue;
            }
            sol.add(sum);
            expSol.add(exp);
            exp--;
        }

        int[][] multResult = new int[2][sol.size()];

        for (int i = 0; i < sol.size(); i++) {
            if((Integer)sol.get(i) == 0){
                continue;
            }
                multResult[0][i] = (Integer)sol.get(i);
                multResult[1][i] = (Integer)expSol.get(i);
        }
        printMult(multResult);
        return multResult;
    }
    private void printMult(int[][] arr){
        if(arr[0].length == 0){
            System.out.println("Error");
            return;
        }
        for (int i = 0, j = 0; i < arr[0].length; i++, j++){
            if(arr[0][i] == 1){ // If term = 1
                if(i == 0 ){ // if first term
                    if(arr[1][i] > 1)
                        System.out.print("x^" + arr[1][i]);
                    else if (arr[1][i] == 1){ // if power 1
                        System.out.print("x");
                    }else{
                        System.out.println("1");
                    }
                }else{
                    System.out.print("+x^" + i);
                }
            } else if(arr[0][i] == -1){
                if(arr[1][i] > 1)
                    System.out.print("-x^" + arr[1][i]);
                else if (arr[1][i] == 1){ // if power 1
                    System.out.print("-x");
                }else{
                    System.out.println("-1");
                }
            } else{
                if(i == 0){ // if first term
                    if(arr[1][i] > 1)
                        System.out.print(arr[0][i] + "x^" + arr[1][i]);
                    else if (arr[1][i] == 1){ // if power 1
                        System.out.print(arr[0][i] + "x");
                    } else{
                        System.out.print(arr[0][i]);
                    }
                }else {
                    if (arr[0][i] > 0) { // if positive
                        if (arr[1][i] > 1) {
                            System.out.print("+" + arr[0][i] + "x^" + arr[1][i]);
                        } else if (arr[1][i] == 1) {
                            System.out.print("+" + arr[0][i] + "x");
                        } else {
                            System.out.print("+" + arr[0][i]);
                        }
                    } else { // if negative
                        if (arr[1][i] > 1) { //TODO negative one coeffiecnt
                            System.out.print(arr[0][i] + "x^" + arr[1][i]);
                        } else if (arr[1][i] == 1) {
                            System.out.print(arr[0][i] + "x");
                        } else {
                            System.out.print(arr[0][i]);
                        }
                    }
                }
            }
        }
        System.out.println();
    }
    @Override
    public int[][] multiply(char poly1, char poly2) {
        if(poly1 == 'A' && poly2 == 'A'){
            return multHelper(A, A);
        }else if(poly1 == 'A' && poly2 == 'B'){
            return multHelper(A, B);
        }else if(poly1 == 'A' && poly2 == 'C'){
            return multHelper(A, C);
        }else if(poly1 == 'B' && poly2 == 'A'){
            return multHelper(B, A);
        }else if(poly1 == 'B' && poly2 == 'B'){
            return multHelper(B, B);
        }else if(poly1 == 'B' && poly2 == 'C'){
            return multHelper(B, C);
        }else if(poly1 == 'C' && poly2 == 'A'){
            return multHelper(C, A);
        }else if(poly1 == 'C' && poly2 == 'B'){
            return multHelper(C, B);
        }else if(poly1 == 'C' && poly2 == 'C'){
            return multHelper(C, C);
        } else {
            System.out.println("Error");
        }
        return new int[0][];

    }
}
