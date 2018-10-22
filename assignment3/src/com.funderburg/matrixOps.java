package com.funderburg;

import java.io.FileWriter;
import java.io.File;
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

        public String print() {
            String output = "";
            for (int c = 0; c < rows; c++)
            {
                for (int d = 0; d < columns; d++) {
                    System.out.print(mtx[c][d] + "\t");
                    output += mtx[c][d] + "\t";
                }
                output += "\n";
                System.out.println();
            }
            return output;
        }

        public Matrix add(Matrix m) {
            Matrix sum = new Matrix(rows, columns);

            for (int i = 0; i < rows; i++)
                for (int j = 0; j < columns; j++)
                    sum.mtx[i][j] = mtx[i][j] + m.mtx[i][j];

            return sum;
        }

        public Matrix multiply(Matrix m) {
            if ((columns != m.rows))
                throw new AssertionError("Matrices can't be multiplied: Incorrect dimensionality");
            Matrix prod = new Matrix(rows, m.columns);
            System.out.println("Matrix 1:");
            print();
            System.out.println("Matrix 2:");
            m.print();
            System.out.println();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < m.columns; j++) {
                    for (int k = 0; k < m.rows; k++) {
                        prod.mtx[i][j] += mtx[i][k] * m.mtx[k][j];
                    }
                }
            }
            return prod;
        }
    }



    public static void main(String [] args) {
        String currDir = "/Users/kevinfunderburg/Dropbox/Documents/School/2018/Fall/Object Oriented Design/Projects/assignment3/src/com.funderburg/";
        String outPath = currDir + "matrixOutput.txt";
        File file = new File(outPath);
        FileWriter fr = null;

        try
        {
            fr = new FileWriter(file, true);
            Matrix m1 = new Matrix(2, 2);
            Matrix m2 = new Matrix(2, 2);
            m1.random();
            m2.random();
            Matrix sum1 = m1.add(m2);

            fr.write("============= MATRIX ADDITION =============\n\n");
            System.out.println("Matrix 1:");
            fr.write("Matrix 1:\n");
            fr.write(m1.print());
            fr.write("Matrix 2:\n");
            fr.write(m2.print());
            fr.write("Sum of the matrices:\n");
            fr.write(sum1.print());
            fr.write("\n");

            Matrix m3 = new Matrix(3, 5);
            Matrix m4 = new Matrix(3, 5);
            m3.random();
            m4.random();
            Matrix sum2 = m3.add(m4);

            fr.write("Matrix 3:\n");
            fr.write(m3.print());
            fr.write("Matrix 4:\n");
            fr.write(m4.print());
            fr.write("Sum of the matrices:\n");
            fr.write(sum2.print());

            fr.write("\n\n============= MATRIX MULTIPLICATION =============\n\n");

            Matrix m5 = new Matrix(2, 2);
            Matrix m6 = new Matrix(2, 2);
            m5.random();
            m6.random();
            Matrix prod1 = m5.multiply(m6);

            fr.write("Matrix 5:\n");
            fr.write(m5.print());
            fr.write("Matrix 6:\n");
            fr.write(m6.print());
            fr.write("Product of the matrices:\n");
            fr.write(prod1.print());
            fr.write("\n");

            Matrix m7 = new Matrix(2, 2);
            Matrix m8 = new Matrix(2, 3);
            m7.random();
            m8.random();
            Matrix prod2 = m7.multiply(m8);

            fr.write("Matrix 7:\n");
            fr.write(m7.print());
            fr.write("Matrix 8:\n");
            fr.write(m8.print());
            fr.write("Product of the matrices:\n");
            fr.write(prod2.print());

        } catch (IOException exc) {
            System.out.println("I/O error" + exc);
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}