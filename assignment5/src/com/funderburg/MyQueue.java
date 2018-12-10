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
import java.util.*;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.Random;

public class MyQueue {

    public static void main(String[] args) {

        // These variables are just for writing output
        String currDir = "/Users/kevinfunderburg/Dropbox/Documents/School/2018/Fall/Object Oriented Design/Projects/assignment5/src/com/funderburg/",
                outPath = currDir + "output.txt";
        File file = new File(outPath);
        FileWriter fr = null;

        try {
            fr = new FileWriter(file, true);

            for (int x = 0; x < 3; x++) {

                fr.write("*****************************************\n" +
                              "************** TEST CASE " + (x + 1) + " **************\n" +
                              "*****************************************\n");

                long startTime = System.currentTimeMillis();
                fr.write("[MyQueue] start-time = " + startTime + "\n");
                System.out.println("[MyQueue] start-time = " + startTime);

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

                int capacityA = (int) (POPULATION * .75);   // queue A capacity
                int capacityB = POPULATION - capacityA;     // queue B capacity
                int busyTime = 0;                           // time that the checker is busy

                float times[] = new float[POPULATION];      // array of processing times per person
                Random rand = new Random();

                ArrayBlockingQueue<Integer> queueA = new ArrayBlockingQueue<Integer>(capacityA);
                ArrayBlockingQueue<Integer> queueB = new ArrayBlockingQueue<Integer>(capacityB);
                Queue<Integer> queueC = new LinkedList<>();

                /**
                 * Queues A and B are filled
                 */
                for (int i = 0; i < POPULATION; i++) {

                    // assign a time (in seconds) that a person arrives
                    if (i == 0) {
                        times[i] = 0;
                    } else {
                        times[i] = (rand.nextInt(15) + 1);
                    }

                    // Enqueue the person in queue A or B
                    if (queueA.size() < capacityA) {
                        queueA.add(i);
                        fr.write("[MyQueue] person " + (i + 1) + " arrived at queue [A] at " + times[i] + "\n");
                    } else {
                        queueB.add(i);
                        fr.write("[MyQueue] person " + (i + 1) + " arrived at queue [B] at " + times[i] + "\n");
                    }
                }

                /**
                 * Now each person is removed from queue A or B and added to queue C
                 */
                for (int i = 0; i < POPULATION; i++) {

                    // Random time between 1-15 mins that checker is busy
                    busyTime = (rand.nextInt(15) + 1) * 60;

                    // Reduce busy time of checker by 1 min if the queue is getting too big
                    if (queueA.size() > 0) {
                        if (queueTooBig(capacityA, queueA.size()))
                            busyTime -= 60;
                    } else {
                        if (queueTooBig(capacityB, queueB.size()))
                            busyTime -= 60;
                    }

                    // Calculate total processing time for person in first queue
                    times[i] = times[i] + busyTime;

                    // Dequeue the person from queue A or B
                    if (i < capacityA) {
                        queueA.remove();
                    } else {
                        queueB.remove();
                    }

                    queueC.add(i);  // Enqueue

                    fr.write("[MyQueue] person " + (i + 1) + " arrived at queue [C] at " + times[i] + "\n");
                }

                /**
                 * Now queue C is processed until completion
                 */
                for (int i = 0; i < POPULATION; i++) {

                    // Random time between 1-15 mins that checker is busy
                    busyTime = (rand.nextInt(15) + 1) * 60;

                    // Reduce busy time by 1 min if queue is getting too big
                    if (queueTooBig(POPULATION, queueC.size()))
                        busyTime -= 60;

                    times[i] = times[i] + busyTime;     // Calculate total processing time for person

                    queueC.remove();  // Dequeue

                    fr.write("[MyQueue] person " + (i + 1) + " finished processing at " + times[i] + "\n");
                }

                float totalProcessTime = 0;

                for (int i = 0; i < times.length; i++)
                    totalProcessTime += times[i];

                fr.write("[MyQueue] total process-time = " + totalProcessTime + "\n");
                long endTime = startTime + (long) (totalProcessTime * 1000);
                fr.write("[MyQueue] end-time = " + endTime + "\n");
                fr.write("[MyQueue] time taken = " + (endTime - startTime) + "\n\n");
            }

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

    // Helper function to let the checker know the queue is too large and to speed up
    private static boolean queueTooBig(int capacity, int queueSize) {
        return queueSize >= (capacity / 2);
    }

}