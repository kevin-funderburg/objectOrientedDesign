package com.funderburg;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class matrixOps {

    static class Matrix {

        int rows, columns;
        int[][] mtx;

        public Matrix(int n, int m) {

            rows = n;
            columns = m;

            mtx = new int[rows][columns];
            Random rand = new Random();
            int j = 0;
            for (int i = 0; i < rows; i++) {
                for (j = 0; j < columns; j++)
                    mtx[i][j] = rand.nextInt(100 + 1);
            }
//            System.out.println(mtx[3][1]);
        }

        public Matrix add(Matrix m2) {
            return m2;
        }

//        publiatrix c
    }



    public static void main(String [] args) {

        Matrix m1 = new Matrix(5, 5);
        Matrix m2 = new Matrix(5, 5);

    }


}