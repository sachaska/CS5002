package asun2_p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The SecretMessage program implements an application for user to unravel
 * and display a single file each time.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class SecretMessage {
    static final char STANDARD_YES = 'Y';   // the standard input of repeat
    static final char STANDARD_NO = 'N';    // the standard input of exit
    private static String fileName;         // holds fileName

    /**
     * The main method asks the user for the name of a file, check to see
     * that the file exists and contains some data, open and read the file
     * exactly once, display the unravel message. The program can repeat
     * as user demand.
     *
     * @param args A String reference containing command line arguments.
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Create a Scanner object.
        Scanner keyboard = new Scanner(System.in);
        // Create a MessageDecoder object.
        MessageDecoder decoder = new MessageDecoder();

        String decodeResult;                // holds decode result

        System.out.println(getHello());

        // Let user decode files as many times as they want.
        do {
            // Set decoder's list to default.
            decoder.setCodeList();

            // If file is valid&!empty, display the unravel message.
            if (getFile(keyboard)) {
                decodeResult = decodeProcess(fileName, decoder);

                if (decodeResult != null)
                    System.out.println(decodeResult);
            }

        } while (getRepeat(keyboard));

        keyboard.close();
        System.out.println(getBye());
    }

    /**
     * The decodeProcess method pass input file for decode and return the
     * result.
     *
     * @param fileName A String reference contains the file name for decode.
     * @param decoder A MessageDecoder object.
     * @return  A String reference contains the result of decode.
     * @throws FileNotFoundException
     */
    public static String decodeProcess(String fileName, MessageDecoder decoder)
            throws FileNotFoundException {
        // Create a File object.
        File file = new File(fileName);
        // Pass File object to decoder.
        if (decoder.setInputFile(file))
        // Return the result.
            return ("Decoded: " + decoder.getPlainTextMessage() + "\n");
        else
            return null;
    }

    /**
     * The getFile method prompts user for filepath input, check if the file
     * is valid, the flag of file valid or not is returned.
     *
     * @param keyboard A Scanner Object for capture user input.
     * @return A boolean value represents file valid status.
     */
    public static boolean getFile(Scanner keyboard) {
        // Ask the user for the name of a file.
        System.out.print("Enter secret file name: ");
        fileName = keyboard.nextLine();

        // Check to see that the file exists, return the boolean result.
        return isValidFile(fileName);
    }

    /**
     * Checks to see that the user-specified file name refers to a valid
     * file on the disk and not a directory. Displays an error message to the
     * user if that is not the case.
     * @param fName file name string to check
     * @return true if file exists on disk and is not a directory
     */
    private static boolean isValidFile(String fName) {
        // Create a File object.
        File path = new File(fName);

        boolean isValid = path.exists() && !path.isDirectory(); // holds flag

        // Displays an error message to the user if that is not the case.
        if (!isValid) {
            System.out.println("Sorry, it's not an valid file path.");
        }

        return isValid;
    }

    /**
     * The getRepeat method captures user input for repeat the program or not,
     * the boolean value is returned.
     *
     * @param keyboard The Scanner Object to capture user input.
     * @return A boolean value represents user command.
     */
    public static boolean getRepeat(Scanner keyboard) {
        char input;                 // holds the user input
        final char DEFAULT = 0;     // the default value of input
        final String PROMPT_MSG = "Would you like to try again? (no to exit): ";
                                    // holds message

        // Repeat when user input is invalid.
        do {
            System.out.print(PROMPT_MSG);

            try {
                input = keyboard.nextLine().toUpperCase().charAt(0);
            } catch (Exception e) {
                input = DEFAULT;
            }

        } while (input != STANDARD_YES && input != STANDARD_NO);

        return input == STANDARD_YES;
    }

    /**
     * The getHello method return the hello message.
     *
     * @return A String reference containing hello message.
     */
    public static String getHello() {
        return "This program reads an encoded message from a file supplied" +
                " by the user and\n" +
                "displays the contents of the message before it was encoded.\n";
    }

    /**
     * The getBye method return the goodbye message.
     *
     * @return A String reference containing goodbye message.
     */
    public static String getBye() {
        return "\nThank you for using the message decoder.";
    }

}
