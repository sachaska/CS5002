package asun2_lab6;

import java.util.Scanner;

/**
 * The RPNCalculator program implements an application of RPN calculator.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class RPNCalculator {
    /**
     * The main method takes user input for calculation, keep execute the
     * calculate process until user input blank.
     *
     * @param args A String array containing command line arguments.
     */
    public static void main(String[] args) {
        boolean isBlankLine;            // the empty status of current input

        // Create a Scanner object.
        Scanner keyboard = new Scanner(System.in);
        // Create a RPN object.
        RPN rpn = new RPN();

        System.out.println(getHello());

        // Calculate user input until blank line.
        do {
            isBlankLine = getResult(rpn, keyboard);
        } while (!isBlankLine);

        System.out.println(getBye());
    }

    /**
     * The getResult method pass the user input to RPN object for
     * calculation, the empty status of user input is returned.
     *
     * @param rpn An RPN Object.
     * @param keyboard A Scanner object for capturing user input.
     * @return A boolean represents input is empty or not.
     */
    public static boolean getResult(RPN rpn, Scanner keyboard) {
        final boolean IS_EMPTY = true;      // input String empty
        final boolean NOT_EMPTY = false;    // input String not empty

        System.out.print("calc> ");
        String input = keyboard.nextLine();

        if (input.equals(""))
            return IS_EMPTY;
        else {
            System.out.println(rpn.evaluate(input));
            return NOT_EMPTY;
        }
    }

    /**
     * The getHello method return the hello message.
     *
     * @return A String reference containing hello message.
     */
    public static String getHello() {
        return "RPN Calculator\n" +
                "\n" +
                "(blank line to quit)";
    }

    /**
     * The getBye method return the goodbye message.
     *
     * @return A String reference containing goodbye message.
     */
    public static String getBye() {
        return "\n" + "Bye!";
    }
}
