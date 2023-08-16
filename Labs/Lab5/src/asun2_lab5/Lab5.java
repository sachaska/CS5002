package asun2_lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Lab5 program reads integers from file and sort in LinkList, remove
 * duplicates.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class Lab5 {
    /**
     * The main method reads integers from file and display the sorted
     * LinkList, remove duplicates and display Linklist again.
     * @param args A String array reference containing command line arguments.
     */
    public static void main(String [] args) {
        final String ORIGINAL = "Linked list contents after reading:\n";
        final String AFTER = "Linked list contents with no duplicates:\n";
        System.out.println(getHello());
        LinkedList ll = readAndSort();
        if (ll!= null) {
            System.out.println(ORIGINAL + ll);
            ll.removeDuplicates();
            System.out.println(AFTER + ll);
            System.out.println(ll.isInclude(78));
        }
        System.out.println(getBye());
    }

    /**
     * The getHello method returns hello message.
     *
     * @return A String reference containing hello message.
     */
    public static String getHello() {
        return ("This program reads the file lab4.dat and inserts " +
                "the elements into a linked list in non-descending order.\n" +
                "The contents of the linked list are then displayed to the" +
                " user in column form.\n");
    }

    /**
     * The getBye method returns goodbye message.
     *
     * @return A String reference containing goodbye message.
     */
    public static String getBye() {
        return ("Thanks for using the linked list program!");
    }

    /**
     * The readAndSort method read integers from file, sort the data to
     * LinkList, LinkList is returned.
     *
     * @return An ListList reference containing sorted file data.
     */
    public static LinkedList readAndSort() {
        final String FILENAME = "lab4.dat";     // default filepath
        String fileName = FILENAME;             // holds filepath
        int fileReadSign;                       // flag for file read status
        Scanner inputFile = null;               // holds input file

        // Capture user input
        Scanner keyboard = new Scanner(System.in);

        // Managing file not found
        do {
            try {
                File file = new File(fileName);
                inputFile = new Scanner(file);
                fileReadSign = 0;
            } catch (FileNotFoundException e) {
                fileReadSign = 1;
                System.out.print(e + "\n" + "Enter new filePath: ");
                fileName = keyboard.nextLine();
            }
        } while (fileReadSign == 1);

        keyboard.close();

        LinkedList list = sortList(inputFile);
        inputFile.close();
        return list;
    }

    /**
     * The sortList method add and sort the file data to LinkList, the
     * LinkList is returned.
     *
     * @param inputFile A Scanner Object holds inputFile.
     * @return An LinkList containing sorted integers from file.
     */
    public static LinkedList sortList(Scanner inputFile) {
        LinkedList ll = new LinkedList();
        while (inputFile.hasNext()) {
            ll.sortedAdd(inputFile.nextInt());
        }
        return ll;
    }
}

/**
 The LinkedList class implements a Linked list.
 */
class LinkedList
{
    /**
     The Node class stores a list element
     and a reference to the next node.
     */

    private class Node
    {
        int value;
        Node next;

        /**
         Constructor.
         @param val The element to store in the node.
         @param n The reference to the successor node.
         */

        Node(int val, Node n)
        {
            value = val;
            next = n;
        }

        /**
         Constructor.
         @param val The element to store in the node.
         */

        Node(int val)
        {
            // Call the other (sister) constructor.
            this(val, null);
        }
    }

    private Node first;  // list head

    /**
     Constructor.
     */

    public LinkedList()
    {
        first = null;
    }

    /**
     The isEmpty method checks to see
     if the list is empty.
     @return true if list is empty,
     false otherwise.
     */

    public boolean isEmpty()
    {
        return first == null;
    }

    public boolean isInclude(int element) {
        Node current = first;
        while (current != null) {
            if (current.value == element)
                return true;
            else
                current = current.next;
        }
        return false;
    }

    /**
     The toString method computes the string
     representation of the list.
     @return The string form of the list.
     */

    public String toString()
    {
        StringBuilder strBuilder = new StringBuilder();

        // Use p to walk down the linked list
        Node p = first;
        while (p != null)
        {
            strBuilder.append(p.value + "\n");
            p = p.next;
        }
        return strBuilder.toString();
    }

    /**
     * The sortedAdd method add element to LinkList in ascending order.
     *
     * @param element The integer to add.
     */
    public void sortedAdd(int element) {
        Node temp;
        // If LinkList is empty, create first Node
        if (isEmpty()) {
            first = new Node(element);
            return;
        }

        // If isn't empty, see if it's smallest
        if (first.value > element) {
            // Add element to the first
            temp = first;
            first = new Node(element, temp);
            first.next = temp;
            return;
        }

        // If not, finding the value greater than element
        // Add element before the Node
        Node prev = first;
        while (prev.next != null) {
            if (prev.next.value > element) {
                temp = prev.next;
                prev.next = new Node(element, temp);
                return;
            }
            prev = prev.next;
        }

        // Go through all Node, adding element at the end of LinkList
        prev.next = new Node(element);
        // Make new element to new last
    }

    /**
     * The removeDuplicates method remove duplicate elements in LinkList.
     */
    public void removeDuplicates() {
        if(first == null);
        else {
            Node current = first;
            while (current != null && current.next != null) {
                if (current.value == current.next.value)
                    current.next = current.next.next;
                else
                current = current.next;
            }
        }
    }
}
