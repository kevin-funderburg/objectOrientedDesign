/***************************************************************************
 * Kevin Funderburg
 * CS 3354 - Programming Assignment 3 pt 1
 * Quadratic Formula
 *
 * Contents of QuadraticForm.java
 *****************************************************************************/

package com.funderburg;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;

public class QuadraticForm {

    public static class Complex {
        double real, imaginary;

        public Complex() {
            real = 0.0;
            imaginary = 0.0;
        }

        private Complex(double r, double i) {
            real = r;
            imaginary = i;
        }
    }

    public static class Quadratic {
        double p, q, discrim;

        Quadratic() {
            p = 0.0;
            q = 0.0;
            discrim = 0.0;
        }

        public Quadratic(double a, double b, double c) {
            discrim = b * b - 4 * a * c;

            if (discrim > 0) {
                p = (-b + Math.sqrt(discrim)) / (2 * a);
                q = (-b - Math.sqrt(discrim)) / (2 * a);
            } else if (discrim == 0) {
                p = q = (-b + Math.sqrt(discrim)) / (2 * a);
            } else {
                int i = -1;
                double realPart = (-b / 2 * a);
                double imaginary = (Math.sqrt(discrim * i) / (2 * a));

                Complex temp = new Complex(realPart, imaginary);
                p = temp.real + temp.imaginary;
                q = temp.real - temp.imaginary;
            }
        }
    }

    public static void main(String[] args) {
        String currDir = "/Users/kevinfunderburg/Dropbox/Documents/School/2018/Fall/Object Oriented Design/Projects/assignment3/src/com.funderburg/";
        String inPath = currDir + "input.txt";
        String outPath = currDir + "output.txt";

        double a = 0, b = 0, c = 0;
        try (FileWriter fw = new FileWriter(outPath))
        {
            fw.write("-----------TEST CASES OUTPUT----------\n");
            for (int i = 0; i < 3; i++)
            {
                if (i == 0) {
                    a = 7;
                    b = 10;
                    c = 2;   // real & distinct
                } else if (i == 1) {
                    a = -4;
                    b = 12;
                    c = -9; // real & equal
                } else {
                    a = 2.3;
                    b = 4;
                    c = 5.6; // imaginary & distinct
                }

                fw.write("\n=============================\n");
                fw.write("====        CASE " + (i + 1) + "       ====\n");
                fw.write("=============================\n");
                fw.write("a is " + a + "\nb is " + b + "\nc is " + c + "\n");

                Quadratic quad = new Quadratic(a, b, c);

                if (quad.discrim > 0)
                    fw.write("Roots are real and distinct\n");
                else if (quad.discrim == 0)
                    fw.write("Roots are real and equal\n");
                else
                    fw.write("Roots are complex and distinct\n");


                double lhs1 = quad.p + quad.q;
                double rhs1 = -(b / a);

                double lhs2 = quad.p * quad.q;
                double rhs2 = c / a;

                if (lhs1 == rhs1)
                    fw.write("\nDoes p + q == -(b / a)? " + true);
                else
                    fw.write("\nDoes p + q == -(b / a)? " + false);

                if (lhs2 == rhs2)
                    fw.write("\nDoes p * q == c/a? " + true);
                else
                    fw.write("\nDoes p * q == c/a? " + false);

                fw.write("\n");
            }
        } catch (IOException exc) {
            System.out.println("I/O error" + exc);
        }
    }

}