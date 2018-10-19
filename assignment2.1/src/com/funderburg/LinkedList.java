package com.funderburg;

/***************************************************************************
 * Kevin Funderburg
 * CS 3354 - Programming Assignment 2
 * Linked Lists
 *
 * Contents of LinkedList.java
 *****************************************************************************/

import java.io.*;
import java.util.*;
import java.util.Random;

public class LinkedList<AnyType> implements Iterable<AnyType>
{
    private Node<AnyType> head;

    /**
     *  Constructs an empty list
     */
    public LinkedList()
    {
        head = null;
    }
    /**
     *  Returns true if the list is empty
     *
     */
    public boolean isEmpty()
    {
        return head == null;
    }
    /**
     *  Inserts a new node at the beginning of this list.
     *
     */
    public void addFirst(AnyType item)
    {
        head = new Node<AnyType>(item, head);
    }
    /**
     *  Returns a string representation
     *
     */
    public String toString()
    {
        StringBuffer result = new StringBuffer();
        for(Object x : this)
            result.append(x + " ");

        return result.toString();
    }

    /*******************************************************
     *
     *  The Node class
     *
     ********************************************************/
    private static class Node<AnyType>
    {
        private AnyType data;
        private Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> next)
        {
            this.data = data;
            this.next = next;
        }
    }

    /*******************************************************
     *
     *  The Iterator class
     *
     ********************************************************/

    public Iterator<AnyType> iterator()
    {
        return new LinkedListIterator();
    }

    private class LinkedListIterator  implements Iterator<AnyType>
    {
        private Node<AnyType> nextNode;

        public LinkedListIterator()
        {
            nextNode = head;
        }

        public boolean hasNext()
        {
            return nextNode != null;
        }

        public AnyType next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            AnyType res = nextNode.data;
            nextNode = nextNode.next;
            return res;
        }

        public void remove() { throw new UnsupportedOperationException(); }
    }



    public static void main(String[] args) {
        String currDir = "/Users/kevinfunderburg/Dropbox/Documents/School/2018/Fall/Object Oriented Design/Projects/assignment2.1/src/com/funderburg/",
                inPath = currDir + "input.txt",
                outPath = currDir + "output.txt",
                output = "",
                str = "";

        Random rand = new Random();

        for (int x = 0; x < 3; x++) {                           // Repeat 3 times for 3 test cases
            List<Integer> intList = new ArrayList<Integer>();
            for (int i = 0; i < 5; i++) {
                intList.add(rand.nextInt(50) + 1);       // Create an integer list of 5 random ints
            }

            LinkedList orgList = createLinkedList(intList);     // Create a linked list of the integer list

            output += "ORIGINAL LINKED LIST:\t\t" + orgList + "\n" +         // Append output to be written to file
                      "REVERSED LINKED LIST:\t\t" + reverseLinkedList(orgList) + "\n\n";
        }

        try (FileWriter fw = new FileWriter(outPath)) {
            fw.write("-------------TEST CASES OUTPUT------------\n\n");
            fw.write(output);
            fw.write("------------------------------------------\n");
        } catch (IOException exc) {
            System.out.println("I/O error" + exc);
        }
    }

    private static LinkedList createLinkedList(List<Integer> integerList)
    {
        LinkedList<Integer> list = new LinkedList <Integer>();
        for (int i = 0; i < integerList.size(); i++) {
            list.addFirst(integerList.get(i));
        }
        return list;
    }

    private static LinkedList reverseLinkedList(LinkedList inputList)
    {
        LinkedList revList = new LinkedList();

        Node<Integer> tmp = inputList.head;
        while(tmp != null)
        {
            revList.addFirst( tmp.data );
            tmp = tmp.next;
        }
        return revList;
    }
}

