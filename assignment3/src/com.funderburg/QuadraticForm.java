package com.funderburg;

import java.lang.Math;


public class QuadraticForm {

    class CalculateQ {
        // Will be solving for formula ax^2 + bx + c = 0

        int a, b, c, x;
//        double discriminant = (b^2 - 4*a*c);
        Discriminant d = new Discriminant(a,b,c);


        double p = b + Math.sqrt(d);
        double q = b - Math.sqrt(d);



        class int Discriminant(a,b,c) {
            int i = b^2 - 4 * a * c;
            return i;
        }
    }




    public static void main(String [] args) {

    }
}
