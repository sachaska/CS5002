package asun2_lab3;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The Lab3 program implements an application that play a guessing game,
 * and ask the user if they would like to play again.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class Lab3 {
    final static Pattern IsIntegerPattern = Pattern.compile("^\\d+$");
                                            // use to detect user input
    static final char STANDARD_YES = 'Y';   // the standard input of repeat
    static final char STANDARD_NO = 'N';    // the standard input of exit

    /**
     * The main method implements the game process.
     *
     * @param args A String reference containing command line arguments.
     */
    public static void main(String[] args) {
        final int MIN = 1;          // the minimum of target
        final int MAX = 2;          // the maximum of target
        // final int MIN = 10000;          // the minimum of target
        // final int MAX = 26000;          // the maximum of target
        boolean guess;                  // flag of guess out the target or not
        int input;                      // holds user input

        Scanner keyboard = new Scanner(System.in);
        GuessGame guessGame = new GuessGame(MIN, MAX);

        // Hello message
        System.out.println("This is a guessing game where you will guess a" +
                " number\n" + "and I tell you if it is too low or too high.\n");

        // Repeat as many times as user wish
        do {
            // Create a new game, set new target
            newGame(guessGame);
            // Repeat until user guess out the target
            do {
                input = getInput(keyboard, MIN, MAX);
                guess = eachGuess(guessGame, input);
            } while (!guess);
            // Display count
            guessGame.displayStatistics();
        } while (getRepeat(keyboard) == STANDARD_YES);
        // Goodbye message
        System.out.println("Thanks for playing!");

        keyboard.close();
    }

    /**
     * The eachGuess method see if user guess is true, calculate the guessing
     * times.
     *
     * @param guessGame the GuessGame object.
     * @param input user input integer.
     * @return A boolean value represent guessing is true or false.
     */
    public static boolean eachGuess(GuessGame guessGame, int input) {
        boolean guess = guessGame.guess(input);
        guessGame.setCount();
        return guess;
    }

    /**
     * The getInput method prompts user for input, capture user input integer,
     * the input is returned.
     *
     * @param keyboard Scanner object for capture input.
     * @param min Mini range of target.
     * @param max Max range of target.
     * @return User input integer.
     */
    public static int getInput(Scanner keyboard, int min, int max) {
        final boolean VALID = true;
        final boolean INVALID = false;
        boolean flag = INVALID;
        int input = GuessGame.DEFAULT;
        String str;
        while (!flag) {
            System.out.printf("Guess a number between %d and %d: ",
                    min, max);
            str = keyboard.nextLine();
            if (IsIntegerPattern.matcher(str).matches()) {
                input = Integer.parseInt(str);
                flag = VALID;
            }
        }
        return input;
    }

    /**
     * The newGame method gets new target number, set guessing time to default.
     *
     * @param guessGame the GuessGame object.
     */
    public static void newGame(GuessGame guessGame) {
        guessGame.newTarget();
        guessGame.setCount(GuessGame.DEFAULT);
    }

    /**
     * The getRepeat method captures user input for repeat the program or not,
     * the char value contains user input is returned.
     *
     * @param keyboard The Scanner Object to capture user input.
     * @return The char value of user input.
     */
    public static char getRepeat(Scanner keyboard) {
        char input;         // holds the user input String
        final String PROMPT_MSG = "\n" + "Ready to play again? (no to quit) ";
        do {
            System.out.print(PROMPT_MSG);
            try {
                input = keyboard.nextLine().toUpperCase().charAt(0);
            } catch (Exception e) {
                input = GuessGame.DEFAULT;
            }
        } while (input != STANDARD_YES && input != STANDARD_NO);
        System.out.println();
        return input;
    }

}
