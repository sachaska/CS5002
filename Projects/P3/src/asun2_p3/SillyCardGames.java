package asun2_p3;

import java.util.Scanner;

/**
 * The SillyCardGames program implements an application for two players play
 * a card game.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class SillyCardGames {
    static final char STANDARD_YES = 'Y';   // the standard input of repeat
    static final char STANDARD_NO = 'N';    // the standard input of exit

    /**
     * The main method has the following functions:
     * 1. Display hello message.
     * 2. Let players play as many round as they want.
     * 3. Display goodbye message.
     * @param args A String array reference containing command line arguments.
     */
    public static void main(String[] args) {
        final int PLAYER_COUNT = 2;         // 2 Players

        // Create a Scanner Object.
        Scanner keyboard = new Scanner(System.in);
        // Create a GameModel Object.
        GameModel model = new GameModel(PLAYER_COUNT);

        System.out.println(getHello());

        // Let users play as many times as they want.
        do {
            // Execute the game process.
            model.gameProcess();
            // Display the game process and result.
            System.out.println(model);
        } while (getRepeat(keyboard));

        System.out.println(getBye());

        // Close the Scanner Object.
        keyboard.close();
    }

    /**
     * The getRepeat method captures user input for repeat the program or not,
     * the boolean value is returned.
     * @param keyboard The Scanner Object to capture user input.
     * @return A boolean value represents user command.
     */
    public static boolean getRepeat(Scanner keyboard) {
        char input;         // holds the user input String
        final String PROMPT_MSG = "Play again?  (no to exit): ";

        do {
            System.out.print(PROMPT_MSG);

            try {
                input = keyboard.nextLine().toUpperCase().charAt(0);
            } catch (Exception e) {
                input = 0;
            }

        } while (input != STANDARD_YES && input != STANDARD_NO);

        return input == STANDARD_YES;
    }


    /**
     * The getHello method return the hello message.
     * @return A String reference containing hello message.
     */
    public static String getHello() {
        return """
                This program implements a card game between two players.
                """;
    }

    /**
     * The getBye method return the goodbye message.
     * @return A String reference containing goodbye message.
     */
    public static String getBye() {
        return "\nThank you for playing the card game!";
    }

}
