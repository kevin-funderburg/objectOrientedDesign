package com.funderburg;

import java.io.*;
import java.lang.Math;
import java.util.Random;

public class QuadraticForm {

    class CalculateQ {
        // Will be solving for formula ax^2 + bx + c = 0

//        int a, b, c, x;
//        Discriminant d = new Discriminant(a,b,c);
//
//
//        double p = b + Math.sqrt(d);
//        double q = b - Math.sqrt(d);



//        class int Discriminant(a,b,c) {
//            int i = b^2 - 4 * a * c;
//            return i;
//        }
        // take left side and compare to right side and determine if true or false
    }


    public static void main(String [] args) {
        String currDir = "/Users/kevinfunderburg/Dropbox/Documents/School/2018/Fall/Object Oriented Design/Projects/assignment2.1/src/com/funderburg/",
                inPath = currDir + "input.txt",
                outPath = currDir + "output.txt",
                output = "",
                str = "";

        Random rand = new Random();

        double a = 7.0,
                b = 10.0,
                c = 2.0,
                discrim = 0.0,
                x;

        a = 7; b = 10; c = 2;   // real & distinct
        a = -4; b = 12; c = -9; // real & equal

//        double a = rand.nextInt(10 + 1),
//                b = rand.nextInt(10 + 1),
//                c = rand.nextInt(10 + 1),
//                x;

        double p = 0.0, q = 0.0;

        discrim = b * b - 4 * a * c;

        if (discrim > 0) {

            p = (-b + Math.sqrt(discrim)) / (2*a);
            q = (-b - Math.sqrt(discrim)) / (2*a);

            System.out.println("roots are real and unequal");
        } else if (discrim == 0) {

            p = (-b + Math.sqrt(discrim)) / (2*a);
            System.out.println("roots are real and equal");

        } else {
            // complex
            System.out.println("roots are imaginary");
        }

        double lhs1 = p + q;
        double rhs1 = -(b / a);

        double lhs2 = p * q;
        double rhs2 = c / a;


        if ((lhs1 == rhs1) && (lhs2 == rhs2)) {
            System.out.println("cases have been proven");
        } else
            System.out.println("cases not proven");

    }
}
