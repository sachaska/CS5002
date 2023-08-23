package asun2_p3;

import java.util.Scanner;

public class SillyCardGames {
    static final char STANDARD_YES = 'Y';   // the standard input of repeat
    static final char STANDARD_NO = 'N';    // the standard input of exit
    public static void main(String[] args) {
        final int PLAYER_COUNT = 2;
        Scanner keyboard = new Scanner(System.in);
        GameModel model = new GameModel(PLAYER_COUNT);

        System.out.println(getHello());

        do {
            model.gameProcess();
            System.out.println(model);
        } while (getRepeat(keyboard));

        System.out.println(getBye());
        keyboard.close();
    }

    /**
     * The getRepeat method captures user input for repeat the program or not,
     * the boolean value is returned.
     *
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
     *
     * @return A String reference containing hello message.
     */
    public static String getHello() {
        return """
                This program implements a card game between two players.
                """;
    }

    /**
     * The getBye method return the goodbye message.
     *
     * @return A String reference containing goodbye message.
     */
    public static String getBye() {
        return "\nThank you for playing the card game!";
    }

}
