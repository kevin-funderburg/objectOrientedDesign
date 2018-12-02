/***************************************************************************
 * Kevin Funderburg
 * CS 3354 - Programming Assignment 5
 * Queues
 *
 * Contents of MyQueue.java
 *****************************************************************************/

package com.funderburg;

import java.util.*;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.Random;

//TODO - Decide if this class should be utilized
public class MyQueue {

    int capacity;
    int busyTime;
    ArrayBlockingQueue<Integer> myQueue;

    Random rand = new Random();

    MyQueue(int initialCapacity) {
        capacity = initialCapacity;
        busyTime = 0;
        myQueue = new ArrayBlockingQueue<Integer>(capacity);
    }

    public void setBusyTime() {
        // Random time between 1-15 mins that checker is busy
        busyTime = (rand.nextInt(15) + 1) * 60;
    }

    //public class MyQueue implements Runnable {
//
////    Random rand = new Random();
////    int busyTime = rand.nextInt(15) + 1;       // Random time between 1-15 mins that checker is busy
//
//    @Override
//    public void run() {
//
//        long startTime = System.currentTimeMillis();
//        System.out.println("[MyQueue] start-time = " + startTime);
//
//
//
//        long endTime = System.currentTimeMillis();
//        System.out.println("[MyQueue] end-time = " + endTime);
//        System.out.println("[MyQueue] time taken = " + (endTime - startTime));
//
//
//    }
//public class MyQueue extends Thread {
//
////    Random rand = new Random();
////    int busyTime = rand.nextInt(15) + 1;       // Random time between 1-15 mins that checker is busy
//
//    @Override
//    public void run() {
//
//        long startTime = System.currentTimeMillis();
//        System.out.println("[MyQueue] start-time = " + startTime);
//
//
//
//        long endTime = System.currentTimeMillis();
//        System.out.println("[MyQueue] end-time = " + endTime);
//        System.out.println("[MyQueue] time taken = " + (endTime - startTime));
//
//
//    }


    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        System.out.println("[MyQueue] start-time = " + startTime);

        int POPULATION = 10;                        // Number of people boarding

        int capacityA = (int) (POPULATION * .75);   // queue A capacity
        int capacityB = POPULATION - capacityA;     // queue B capacity
        int busyTime = 0;                           // time that the checker is busy

        float times[] = new float[POPULATION];      // array of processing times per person
        Random rand = new Random();

        ArrayBlockingQueue<Integer> queueA = new ArrayBlockingQueue<Integer>(capacityA);
        ArrayBlockingQueue<Integer> queueB = new ArrayBlockingQueue<Integer>(capacityB);
        Queue<Integer> queueC = new LinkedList<>();

//        int theQueue;

        for (int i = 0; i < POPULATION; i++) {

//            theQueue = i % 2;
//            theQueue = rand.nextInt(1) + 1;


            /**
             * Person is added to queue A or B to be processed
             */

            // assign a time (in seconds) that a person arrives
            if (i == 0) {
                times[i] = 0;
            } else {
                times[i] = (rand.nextInt(15) + 1);
            }

            // Enqueue the person in queue A or B
            if (queueA.size() < capacityA) {
                queueA.add(i);
            } else {
                queueB.add(i);
            }
//            if (theQueue == 0 && queueA.size() < capacityA) {
//                queueA.add(i);
//            } else if (theQueue == 1 && queueB.size() < capacityB) {
//                queueB.add(i);
//            }

            System.out.println("[MyQueue] person " + i + " arrived at queue [AorB] at " + times[i]);

            // Random time between 1-15 mins that checker is busy
            busyTime = (rand.nextInt(15) + 1) * 60;

            // Reduce busy time by 1 minute if queues are getting too big
            if (queueA.size() > 5 || queueB.size() > 5) {
                busyTime -= 60;
            }

            // Calculate total processing time for person in first queue
            times[i] = times[i] + busyTime;

            //TODO - Decide on how to remove from queues A&B (seems like queue should be full before passing to queue C?
            queueA.remove();


            /**
             * Now the person is added to queue C to be processed
             */

            queueC.add(i);  // Enqueue
            System.out.println("[MyQueue] person " + i + " arrived at queue [C] at " + times[i]);

            // Random time between 1-15 mins that checker is busy
            busyTime = (rand.nextInt(15) + 1) * 60;

            // Reduce busy time by 1 minute if queues are getting too big
            if (queueC.size() > 5) {
                busyTime -= 60;
            }

            times[i] = times[i] + busyTime;     // Calculate total processing time for person

            System.out.println("[MyQueue] person " + i + " finished processing at " + times[i] + "\n");

        }

        float totalProcessTime = 0;

        for (int i = 0; i < times.length; i++)
            totalProcessTime += times[i];


        System.out.println("[MyQueue] total process-time = " + totalProcessTime);
        long endTime = startTime + (long) (totalProcessTime * 1000);
        System.out.println("[MyQueue] end-time = " + endTime);
        System.out.println("[MyQueue] time taken = " + (endTime - startTime));
    }
}

