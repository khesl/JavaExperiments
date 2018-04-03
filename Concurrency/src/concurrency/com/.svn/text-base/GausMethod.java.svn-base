package concurrency.com;

import concurrency.com.utility.Matrix;

import java.util.Enumeration;
import java.util.Vector;

public class GausMethod {
    private Vector v = new Vector(M);
    private Matrix m;
    private static int M = 4;
    private static int N = 4;
    
    public GausMethod() {
        super();
    }

    public static void main(String[] args) {
        GausMethod gausMethod = new GausMethod();
        gausMethod.start();
    }
    
    public void start(){
        for (int i = 0; i<M; i++){
            v.add( i, (int)(Math.random()*10)+1);
        }
        double[][] tempM = new double[M][N]; 
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                tempM[i][j] = (int)(Math.random()*10)+1;
        m = new Matrix(tempM);
    
        viewMatrix(m);
        viewVector(v);
        Gauss(m, v);
    }
    
    public void Gauss(Matrix m, Vector v){
        m = m.extendMatrix(v);
        viewMatrix(m);
        
        m = m.sortRows(0, 0);
        viewMatrix(m);
    }
    
    public void viewVector(Vector v){
        // enumerate the elements in the vector.
        Enumeration vEnum = v.elements();
        System.out.println("\nElements in vector:");
              
        while(vEnum.hasMoreElements())
            System.out.print(vEnum.nextElement() + " ");
        System.out.println();
    }
    public void viewMatrix(Matrix m){
        System.out.println("\nElements in matrix:");
        for (int i = 0; i < m.getM(); i++) {
            for (int j = 0; j < m.getN(); j++) 
                System.out.print(m.getData()[i][j] + " ");
            System.out.println();
        }
    }
}
