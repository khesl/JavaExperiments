package concurrency.com;

import concurrency.com.utility.Matrix;

import java.util.Enumeration;
import java.util.Vector;

public class Matrix_X_Vector {
    private Vector v = new Vector(5);
    private int[][] matrix;
    private Matrix m;
    
    public Matrix_X_Vector() {
        super();
    }

    public static void main(String[] args) {
        Matrix_X_Vector matrix_X_Vector = new Matrix_X_Vector();
        matrix_X_Vector.start();
    }
    
    public void start(){
        for (int i = 0; i<5; i++){
            v.add( i, (int)(Math.random()*10)+1);
        }
        int M = 5;
        int N = 5;
        double[][] tempM = new double[M][N]; 
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                tempM[i][j] = (int)(Math.random()*10)+1;
        m = new Matrix(tempM);
        
        
        
        viewMatrix(m);
        viewVector(v);
        //System.out.print("\tVectorByMultVector "); viewVector(m.vectorByMultVectorColumn(v));
        System.out.print("\tMatrixByMultVector "); viewMatrix(m.matrixByMultVectorRow(v));
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
