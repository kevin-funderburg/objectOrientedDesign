package com.funderburg;

public class QuadraticForm {

    class CalculateQ {
        // Will be solving for formula ax^2 + bx + c = 0

        int a, b, c, x;
        double root1,root2,
                discriminant = (b^2 - 4*a*c);



        root1 = b + sqrt(discriminant);
        root2 = b - sqrt(discriminant);

    }


    public static void main(String [] args) {

    }
}
