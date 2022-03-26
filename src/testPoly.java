public class testPoly {
    public static void main(String[] args) {
        PolynomialSolver myObj = new PolynomialSolver();
        int[][] A= {{1, -3, 2}};
        int[][] B = {{1, -5, 6}};
        int[][] C = {{1, -9, 26, -24}};
        myObj.setPolynomial('A', A);
        myObj.setPolynomial('B', B);
        myObj.setPolynomial('C', C);
        myObj.print('A');
        myObj.print('B');
        myObj.print('C');
    }
}
