public class testPoly {
    public static void main(String[] args) {
        PolynomialSolver myObj = new PolynomialSolver();
        int[][] A= {{1,-13,50,-56}};
        int[][] B = {{5,41,-50,0}};
        int[][] C = {{1, -9, 26, -24}};
        myObj.setPolynomial('A', A);
        myObj.setPolynomial('B', B);
        myObj.setPolynomial('C', C);
        float sol = myObj.evaluatePolynomial('A', 5);
        System.out.print(sol);
    }
}
