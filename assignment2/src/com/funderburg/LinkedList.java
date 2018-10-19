import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinkedListManip {

    public static void main(String[] args) {
        String currDir = "/Users/kevinfunderburg/Dropbox/Documents/School/2018/Fall/Object Oriented Design/Projects/assignment2/src/com/funderburg/",
                inPath = currDir + "input.txt",
                outPath = currDir + "output.txt",
                output = "",
                str = "";

        Random rand = new Random();

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(rand.nextInt(50) + 1);
        }

        /* Start with the empty list. */
        LinkedList llist = new LinkedList();

        llist.head       = new Node(1);
//        Node second      = new Node(2);
//        Node third       = new Node(3);
//
//        llist.head.next = second; // Link first node with the second node
//        second.next = third; // Link first node with the second node
//
//        llist.printList();

//        LinkedList theLinkedList = createLinkedList(list);
//
//        System.out.println("Original Linked List Content: " + theLinkedList);


//        LinkedList<Integer> Numbers = new LinkedList<Integer>();

//        try {
//            BufferedReader br = new BufferedReader(new FileReader(inPath));
//            while ((str = br.readLine()) != null)
//            {
//                String evens = "",
//                       odds = "";
//
//                for (int i = 0; i < str.length(); i++) {
//                    if (i % 2 == 0) {
//                        evens += str.charAt(i);
//                    } else
//                        odds += str.charAt(i);
//                }
//
//                output += "ORIGINAL STRING:\t\t" + str + "\n" +
//                          "Reversed String:\t\t" + reverseLinkList(str) + "\n" +
//                          "[evens], [odds]:\t\t" + evens + ", " + odds + "\n" +
//                          "Reversed Substrings:\t" + reverseLinkList(evens) + ", " + reverseLinkList(odds) + "\n" +
//                          "OUTPUT:\t\t\t\t\t" + reverseLinkList(evens) + reverseLinkList(odds) + "\n" +
//                          "---------------------------------------\n";
//            }
//
//            try (FileWriter fw = new FileWriter(outPath)) {
//                fw.write("-----------TEST CASES OUTPUT----------\n");
//                fw.write(output);
//            } catch (IOException exc) {
//                System.out.println("I/O error" + exc);
//            }
//        } catch (IOException exc) {
//            System.out.print("I/O error" + exc);
//        }
    }


//
//
//
//
//        /* This function prints contents of linked list starting from head */
//        public void printList()
//        {
//            Node n = head;
//            while (n != null)
//            {
//                System.out.print(n.data+" ");
//                n = n.next;
//            }
//        }
//    }

    public LinkedList createLinkedList(List<Integer> integerList)
    {
//        LinkedList<Integer> Numbers = new LinkedList<Integer>();
        LinkedList Numbers = new LinkedList();



//        for(int i = 0; i < integerList.size(); i++) {
//            Numbers.add(integerList.get(i));
//        }



        return Numbers;
    }

    private static LinkedList reverseLinkList(LinkedList orgList)
    {
//        LinkedList<Integer> revLinkedList = new LinkedList<Integer>();
        LinkedList revLinkedList = new LinkedList();




        return revLinkedList;
    }
}

// A simple Java program for traversal of a linked list
class LinkedList {
    Node head;  // head of list

    /* Linked list Node.  This inner class is made static so that
       main() can access it */
    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        } // Constructor
    }

    /* This function prints contents of linked list starting from head */
    public void printList() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
    }
