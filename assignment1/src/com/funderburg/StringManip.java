//
// Kevin Funderburg
// CS 3354 - Programming Assignment 1
//
// Contents of StringManip.java
//

package com.funderburg;

import java.io.*;

public class StringManip {

    public static void main(String[] args) {
        String currDir = "/Users/kevinfunderburg/Dropbox/Documents/School/2018/Fall/Object Oriented Design/Projects/assignment1/src/com/funderburg/",
                inPath = currDir + "input.txt",
                outPath = currDir + "output.txt",
                output = "",
                str = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(inPath));
            while ((str = br.readLine()) != null)
            {
                String evens = "",
                       odds = "";

                for (int i = 0; i < str.length(); i++) {
                    if (i % 2 == 0) {
                        evens += str.charAt(i);
                    } else
                        odds += str.charAt(i);
                }

                output += "ORIGINAL STRING:\t\t" + str + "\n" +
                          "Reversed String:\t\t" + reverse(str) + "\n" +
                          "[evens], [odds]:\t\t" + evens + ", " + odds + "\n" +
                          "Reversed Substrings:\t" + reverse(evens) + ", " + reverse(odds) + "\n" +
                          "OUTPUT:\t\t\t\t\t" + reverse(evens) + reverse(odds) + "\n" +
                          "---------------------------------------\n";
            }

            try (FileWriter fw = new FileWriter(outPath)) {
                fw.write("-----------TEST CASES OUTPUT----------\n");
                fw.write(output);
            } catch (IOException exc) {
                System.out.println("I/O error" + exc);
            }
        } catch (IOException exc) {
            System.out.print("I/O error" + exc);
        }
    }

    private static String reverse(String orgStr) {
        char newChars[] = new char[orgStr.length()];
        int i, j;
        // This loop should be faster than a regular loop assignment
        // because it assigns the 2 outer most characters to the new
        // string in one iteration, so iterations are cut in half
        for (i = 0, j = orgStr.length() - 1; i < j + 1; i++, j--) {
            newChars[i] = orgStr.charAt(j);
            newChars[j] = orgStr.charAt(i);
        }
        return new String(newChars);
    }
}
