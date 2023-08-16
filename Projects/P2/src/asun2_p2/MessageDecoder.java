package asun2_p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The MessageDecoder class read input file once and gets the unravel message.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class MessageDecoder {

    static final char SPACE_CHAR = ' ';  // constant value of space char
    private LinkList codeList;      // holds code list
    private Scanner inputFile;      // holds input file

    /**
     * Constructor.
     */
    public MessageDecoder() {}

    /**
     * The setCodeList method create a LinkList object, assigns to attribute
     * codeList.
     */
    public void setCodeList() {
        codeList = new LinkList();
    }

    /**
     * The setInputFile method is setter of attribute inputFile. It would
     * read the file once and close the file.
     *
     * @param file A File Object to be read.
     * @throws FileNotFoundException
     */
    public boolean setInputFile(File file) throws FileNotFoundException {
        final boolean SUCCESS = true;           // success read and sort
        final boolean FAILED = false;           // failed read and sort

        this.inputFile = new Scanner(file);     // set inputFile

        // Catch the exception while reading data from file
        try {
            readFile();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return FAILED;
        }

        inputFile.close();
        return SUCCESS;
    }

    /**
     * The readFile method set the sorted data in inputFile to codeList.
     */
    private void readFile() {
        final String SPACE_STR = " ";               // space String
        final int FIRST = 0, SECOND = 1, LAST = 2;  // the position in strings
        int code;                                   // holds the integer to add
        char dict;                                  // holds the char to add
        String str;                             // temp holds the data form file
        String[] strings;                       // holds the spilt strings

        // Read until EOF.
        while (inputFile.hasNextLine()) {
            str = inputFile.nextLine();
            // Store strings seperated by space
            strings = str.split(SPACE_STR);

            // If there is one space separate two string: char and number
            if (strings.length == 2) {
                dict = strings[FIRST].charAt(FIRST);
                code = Integer.parseInt(strings[SECOND]);
            }
            else {
                dict = SPACE_CHAR;  // Set dict to space

                // Catch missing number exception
                try {
                    code = Integer.parseInt(strings[LAST]);
                } catch (Exception e) {
                    throw new IllegalArgumentException("missing char " +
                                "numbers!");
                }

            }

            // If duplicate number
            if (codeList.search(code))
                throw new IllegalArgumentException("duplicate " +
                        "numbers!");
            // If number negative
            else if (code < 0)
                throw new IllegalArgumentException("illegal " +
                        "negative " + "number!");

            // Otherwise, set codeList with sorted data.
            codeList.sortAdd(dict, code);
        }

    }

    /**
     * The getPlainTextMessage method returns the unravel message in codeList.
     * @return A string object containing the unravel message.
     */
    public String getPlainTextMessage() {
        return codeList.toString();
    }
}

/**
 * The LinkList class implements a single LinkList.
 */
class LinkList {
    private Node first;         // the first Node in LinkList
    private Node last;          // the second Node in LinkList

    /**
     * The Node class implements every Node in LinkList.
     */
    private class Node {
        private char dict;      // holds the char value
        private int code;       // holds the integer value
        private Node next;      // the next Node

        /**
         * Constructor.
         *
         * @param dict the char value.
         * @param code the integer value.
         * @param next the next Node.
         */
        private Node(char dict, int code, Node next) {
            this.dict = dict;
            this.code = code;
            this.next = next;
        }

        /**
         * Constructor of the first and the last Node.
         * @param dict the char value.
         * @param code the integer value.
         */
        public Node(char dict, int code) {
            this.dict = dict;
            this.code = code;
            this.next = null;
        }

    }

    /**
     * The LinkList Constructor.
     */
    public LinkList() {
        first = last = null;
    }

    /**
     * The isEmpty method returns the boolean flag of LinkList is empty or not.
     * @return A boolean value represents LinkList is empty or not.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * The search method search the given integer in LinkList.
     *
     * @param code A given integer.
     * @return A flag represents found status of the given integer.
     */
    public boolean search(int code) {
        if (isEmpty())
            return false;

        else {
            Node current = first;

            while (current != null) {
                if (current.code == code)
                    return true;
                current = current.next;
            }

        }

        return false;
    }

    /**
     * The sortAdd method add given element in non-descending order.
     *
     * @param dict A given dict to add.
     * @param code A given code to add.
     */
    public void sortAdd(char dict, int code) {
        if (isEmpty())
            first = last = new Node(dict, code);

        else {
            /* Check if new element's code is smaller than first, if so, sign
            it to new first.
             */
            if (code < first.code)
                first = new Node(dict, code, first);

            // Else keep tracking until a Node's code is bigger than code
            else {
                Node current = first;

                while (current.next != null) {

                    if (code < current.next.code) {
                        // Put element before the current Node's next
                        current.next = new Node(dict, code, current.next);
                        // End of the program
                        return;
                    }

                        // update current Node
                        current = current.next;
                }

                // go through all the Node, make element a new last
                last.next = new Node(dict, code);
                last = last.next;
            }

        }
    }

    /**
     * The toString method.
     * @return A String Object containing LinkList's char data.
     */
    public String toString() {
        // Create a StringBuilder.
        StringBuilder ll = new StringBuilder();

        Node current = first;           // holds current Node

        while (current != null) {
            ll.append(current.dict);
            current = current.next;
        }

        return ll.toString();
    }
}