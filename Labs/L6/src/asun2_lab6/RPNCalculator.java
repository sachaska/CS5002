package asun2_lab6;

import java.util.Scanner;

public class RPNCalculator {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean isBlankLine;
        RPN rpn = new RPN();

        System.out.println(getHello());

        do {
            isBlankLine = getResult(rpn, keyboard);
        } while (!isBlankLine);

        System.out.println(getBye());
    }

    public static boolean getResult(RPN rpn, Scanner keyboard) {
        System.out.print("calc> ");
        String input = keyboard.nextLine();

        if (input.equals(""))
            return true;
        else {
            System.out.println(rpn.evaluate(input));
            return false;
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
