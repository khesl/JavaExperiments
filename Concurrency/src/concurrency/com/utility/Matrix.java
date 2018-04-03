package concurrency.com.utility;

import java.util.Vector;

final public class Matrix {
    private final int M;             // number of rows
    private final int N;             // number of columns
    private final double[][] data;   // M-by-N array

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // create matrix based on 2d array
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    this.data[i][j] = data[i][j];
    }

    // copy constructor
    private Matrix(Matrix A) { this(A.data); }

    // create and return a random M-by-N matrix with values between 0 and 1
    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    // create and return the N-by-N identity matrix
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // swap rows i and j
    private void swap(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // create and return the transpose of the invoking matrix
    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // return C = A + B
    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }


    // return C = A - B
    public Matrix minus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    // does A = B exactly?
    public boolean eq(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }

    // return C = A * B
    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }

    // return x = A^-1 b, assuming A is square and has full rank
    public Matrix solve(Matrix rhs) {
        if (M != N || rhs.M != N || rhs.N != 1)
            throw new RuntimeException("Illegal matrix dimensions.");

        // create copies of the data
        Matrix A = new Matrix(this);
        Matrix b = new Matrix(rhs);

        // Gaussian elimination with partial pivoting
        for (int i = 0; i < N; i++) {

            // find pivot row and swap
            int max = i;
            for (int j = i + 1; j < N; j++)
                if (Math.abs(A.data[j][i]) > Math.abs(A.data[max][i]))
                    max = j;
            A.swap(i, max);
            b.swap(i, max);

            // singular
            if (A.data[i][i] == 0.0) throw new RuntimeException("Matrix is singular.");

            // pivot within b
            for (int j = i + 1; j < N; j++)
                b.data[j][0] -= b.data[i][0] * A.data[j][i] / A.data[i][i];

            // pivot within A
            for (int j = i + 1; j < N; j++) {
                double m = A.data[j][i] / A.data[i][i];
                for (int k = i+1; k < N; k++) {
                    A.data[j][k] -= A.data[i][k] * m;
                }
                A.data[j][i] = 0.0;
            }
        }

        // back substitution
        Matrix x = new Matrix(N, 1);
        for (int j = N - 1; j >= 0; j--) {
            double t = 0.0;
            for (int k = j + 1; k < N; k++)
                t += A.data[j][k] * x.data[k][0];
            x.data[j][0] = (b.data[j][0] - t) / A.data[j][j];
        }
        return x;
   
    }

    // Matrix and Vector functions
    
    /** Matrix multiply VectorColumn<br>
     * (a11 a12 ... a1n)_(b1)_(a11*b1 + a1n*bn)_(c1)<br>
     * (a21 a22 ... a2n)*(b2)=(a21*b1 + a2n*bn)=(c2)<br>
     * (... ... ... ...)_(..)_(......___......)_(..)<br>
     * (an1 am2 ... amn)_(bn)_(am1*b1 + amn*bn)_(cm)<br>
     * 
     * @param v - input vector
     * @return Vector
     * */
    public Vector vectorByMultVectorColumn(Vector C){
        Matrix A = this;
        if (C.size() != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Vector D = new Vector(M);
        for (int i = 0; i < M; i++){
            double tempS = 0f;
            for (int j = 0; j < N; j++){
                tempS += A.data[i][j] * Double.valueOf(C.get(j).toString());
            }
            D.add(i, tempS);
        }
        return D;
    }
    
    /** Matrix multiply VectorRow<br>
     * (a1)_______________(a1*b1 + a1*bn)_(c11 c12 ... c1n)<br>
     * (a2)*(b1 b2 .. bn)=(a2*b1 + a2*bn)=(c21 c22 ... c2n)<br>
     * (..)_______________(.....___.....)_(... ... ... ...)<br>
     * (an)_______________(an*b1 + an*bn)_(cn1 cn2 ... cnn)<br>
     * 
     * @param v - input vector
     * @return Matrix
     * */
    public Matrix matrixByMultVectorRow(Vector D){
        Matrix A = this;
        if (N == 1 || D.size() != A.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < M; j++)
                C.data[i][j] = A.data[i][N-1] * Double.valueOf(D.get(j).toString());
        return C;
    }

    /** getExtend Matrix
     *
     * @param V
     * @return Matrix
     */
    public Matrix extendMatrix(Vector V){
        Matrix A = this;
        if (V.size() != A.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N + 1);
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j];
            C.data[i][N] = Double.valueOf(V.get(i).toString());
        }
        return C; 
    }
    
    public Matrix sortRows(int sortIndexRow, int sortIndexColumn){
        Matrix A = new Matrix(this);
        double MaxElement = A.data[sortIndexRow][sortIndexColumn];
        int MaxElementIndex = sortIndexRow;
        for (int i = sortIndexRow + 1; i < M; i++)
            if (A.data[i][sortIndexColumn] > MaxElement){
                MaxElement = A.data[i][sortIndexColumn];
                MaxElementIndex = i;
            }
        //теперь найден максимальный элемент ставим его на верхнее место
        if (MaxElementIndex > sortIndexRow) {
            double Temp;
            
            for (int i = 0; i < N; i++){
                Temp = A.data[MaxElementIndex][i];
                A.data[MaxElementIndex][i] = A.data[sortIndexRow][i];
                A.data[sortIndexRow][i] = Temp;
            }
          }
        if (sortIndexRow < M - 1) A = A.sortRows(sortIndexRow + 1, sortIndexColumn);
        return A;
    }

    public int getM() {
        return M;
    }
    public int getN() {
        return N;
    }
    public double[][] getData() {
        return data;
    }
}
