package com.funderburg;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class matrixOps {

    static class Matrix {

        private int[][] mtx;

        int rows, columns;

        public Matrix(int n, int m) {
            rows = n;
            columns = m;
            mtx = new int[rows][columns];
        }

        public void random() {
            Random rand = new Random();
            int j = 0;
            for (int i = 0; i < rows; i++) {
                for (j = 0; j < columns; j++) {
                    mtx[i][j] = rand.nextInt(10 + 1);
                }
            }
        }

        public void print() {
            for (int c = 0; c < rows; c++)
            {
                for (int d = 0; d < columns; d++) {
                    System.out.print(mtx[c][d] + "\t");
                }
                System.out.println();
            }
        }

        public Matrix add(Matrix m) {
            Matrix sum = new Matrix(rows, columns);

            for (int i = 0; i < rows; i++)
                for (int j = 0; j < columns; j++)
                    sum.mtx[i][j] = mtx[i][j] + m.mtx[i][j];

            return sum;
        }

        public Matrix multiply(Matrix m) {
            if ((columns != m.rows)) throw new AssertionError("Matrices can't be multiplied: Incorrect dimensionality");
            Matrix prod = new Matrix(rows, m.columns);
            System.out.println("Matrix 1:");
            print();
            System.out.println("Matrix 2:");
            m.print();
            System.out.println();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < m.columns; j++) {
                    for (int k = 0; k < m.rows; k++) {
                        System.out.print("(" + mtx[i][k] + " * " + m.mtx[k][j] + ")");
                        prod.mtx[i][j] += mtx[i][k] * m.mtx[k][j];
                        if (k < columns - 1) System.out.print(" += ");
                    }
                    System.out.println();
                }
            }
            System.out.println("Product of the matrices:");
            prod.print();
            return prod;
        }

    }



    public static void main(String [] args) {

        Matrix m1 = new Matrix(2, 2);
        m1.random();
        Matrix m2 = new Matrix(2, 2);
        m2.random();
        Matrix sum1 = m1.add(m2);

        System.out.println("Matrix 1:");
        m1.print();
        System.out.println("Matrix 2:");
        m2.print();
        System.out.println("Sum of the matrices:");
        sum1.print();

        Matrix m3 = new Matrix(3, 5);
        m3.random();
        Matrix m4 = new Matrix(3, 5);
        m3.random();
        Matrix sum2 = m3.add(m4);

        System.out.println("Matrix 1:");
        m3.print();
        System.out.println("Matrix 2:");
        m4.print();
        System.out.println("Sum of the matrices:");
        sum2.print();

        Matrix m5 = new Matrix(2, 2);
        m5.random();
        Matrix m6 = new Matrix(2, 2);
        m6.random();
        Matrix prod1 = m5.multiply(m6);

        Matrix m7 = new Matrix(2, 2);
        m7.random();
        Matrix m8 = new Matrix(2, 3);
        m8.random();
        Matrix prod2 = m7.multiply(m8);

    }


}