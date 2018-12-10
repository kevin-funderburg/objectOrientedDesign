/***************************************************************************
 * Kevin Funderburg
 * CS 3354 - Programming Assignment 5
 * Queues
 *
 * Contents of MyQueue.java
 *****************************************************************************/

package com.funderburg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class MyQueue {

    public static void main(String[] args) {

        // These variables are just for writing output
        String currDir = "/Users/kevinfunderburg/Dropbox/Documents/School/2018/Fall/Object Oriented Design/Projects/assignment5/src/com/funderburg/",
                outPath = currDir + "output.txt";
        File file = new File(outPath);
        FileWriter fr = null;

        try {
            fr = new FileWriter(file, true);
        } catch (IOException exc) {
            System.out.println("I/O error" + exc);
        }
//        } finally {
//            try {
//                fr.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

//        }
            for (int x = 0; x < 3; x++)
            {
                out("*****************************************\n" +
                        "************** TEST CASE " + (x + 1) + " **************\n" +
                        "*****************************************\n", fr);

                int POPULATION;     // Number of people boarding

                // 3 test cases
                switch (x) {
                    case 0:
                        POPULATION = 50;
                        break;
                    case 1:
                        POPULATION = 20;
                        break;
                    default:
                        POPULATION = 30;
                        break;
                }

                int capacityA = (int) (POPULATION * .7);   // queue A capacity
                int capacityB = POPULATION - capacityA;     // queue B capacity
                int busyTime = 0;                           // time that the checker is busy

                float times[] = new float[POPULATION];      // array of processing times per person
                float currentTime = 0;
                Random rand = new Random();

                char theQ;                                  // which queue a person is added to

                ArrayBlockingQueue<Integer> queueA = new ArrayBlockingQueue<Integer>(capacityA);
                ArrayBlockingQueue<Integer> queueB = new ArrayBlockingQueue<Integer>(capacityB);
                ArrayBlockingQueue<Integer> queueC = new ArrayBlockingQueue<Integer>(POPULATION);

                /**
                 * Queues A and B are filled
                 */
                for (int i = 0; i < POPULATION; i++)
                {
                    // assign a time (in seconds) that a person arrives
                    if (i == 0)
                        times[i] = 0;
                    else
                        times[i] = (rand.nextInt(15) + 1);

                    currentTime += times[i];

                    // Enqueue the person in queue A or B
                    theQ = randomQ(queueA, queueB, "add");

                    if (theQ == 'A')
                        queueA.add(i);
                    else
                        queueB.add(i);

                    out("[time: " + currentTime + "] \tperson " + (i + 1) + "\tq'd at [" + theQ +
                            "] - q sizes: [A]: " + queueA.size() + " \t[B]: " + queueB.size() + " \t[C]: "
                            + queueC.size() + "\n", fr);
                }
                out("\n", fr);

                /**
                 * Now each person is removed from queue A or B and added to queue C
                 */
                for (int i = 0; i < POPULATION; i++)
                {
                    // Dequeue the person from queue A or B
                    theQ = randomQ(queueA, queueB, "remove");

                    if (theQ == 'A') {
                        if (queueA.size() > 0) {
                            busyTime = getBusyTime(queueA);
                            queueA.remove();
                        }
                    } else {
                        if (queueB.size() > 0) {
                            busyTime = getBusyTime(queueB);
                            queueB.remove();
                        }
                    }

                    // Calculate total processing time for person in first queue
                    currentTime += busyTime;
                    queueC.add(i);  // Enqueue
                    out("[time: " + currentTime + "]\tperson " + (i + 1) + "\tdq'd from [" + theQ + "] in "
                            + busyTime + "s busy time then q'd at [C] - q sizes: [A]: " +
                            queueA.size() + "\t [B]: " + queueB.size() + "\t [C]: " + queueC.size() + "\n", fr);
                }
                out("\n", fr);

                /**
                 * Now queue C is processed until completion
                 */
                for (int i = 0; i < POPULATION; i++)
                {
                    busyTime = getBusyTime(queueC);
                    currentTime += busyTime;
                    queueC.remove();  // Dequeue
                    out("[time: " + currentTime + "]\tperson " + (i + 1) + " finished processing - dq'd " +
                            "from [C] in " + busyTime + "s busy time - q sizes: [A]: " + queueA.size() +
                            " \t[B]: " + queueB.size() + " \t[C]: " + queueC.size() + "\n", fr);
                }

                out("\ntotal process-time = " + currentTime + "\n\n", fr);
            }

        try {
            fr.close();
        } catch (IOException e) {
                e.printStackTrace();
            }
//        } catch (IOException exc) {
//            System.out.println("I/O error" + exc);
//        } finally {
//            try {
//                fr.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }


    // Reduce busy time of checker proportionally to queue size
    private static int getBusyTime(ArrayBlockingQueue<Integer> q) {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int qCapacity = q.size() + q.remainingCapacity();
        int MAXBUSYTIME = 15;
        double upperBound = 0;
        double timeReduction = 0;
        double qFullnessProportion = 0;

        qFullnessProportion = (float) (q.size()) / (float) (qCapacity);
        if (qFullnessProportion == 1.0)
            qFullnessProportion = 0.9;
        upperBound = MAXBUSYTIME - (MAXBUSYTIME * qFullnessProportion);

        // Random time between 1-15 mins that checker is busy
        double busyTime = MAXBUSYTIME * 60;
        timeReduction = upperBound * 60;

        if (timeReduction < busyTime)
            busyTime -= timeReduction;

        return (int) busyTime;
    }

    // Generates a random queue for the person to be added to
    private static char randomQ(ArrayBlockingQueue<Integer> qA, ArrayBlockingQueue<Integer> qB, String method) {
        Random rand = new Random();
        char theQ;
        int coinFlip = rand.nextInt(1000) % 2;

        if (method.equals("add")) {
            if (coinFlip == 0) {
                if (qA.remainingCapacity() > 0)
                    theQ = 'A';
                else
                    theQ = 'B';
            } else {
                if (qB.remainingCapacity() > 0)
                    theQ = 'B';
                else
                    theQ = 'A';
            }
        } else {
            if (coinFlip == 0) {
                if (qA.size() > 0)
                    theQ = 'A';
                else
                    theQ = 'B';
            } else {
                if (qB.size() > 0)
                    theQ = 'B';
                else
                    theQ = 'A';
            }
        }

        return theQ;
    }

    // Helper function to write output to a file and output to the console
    private static void out(String str, FileWriter fr) {
        System.out.print(str);
        try {
            fr.write(str);
        } catch (IOException exc) {
            System.out.println("I/O error" + exc);
        }
    }

}