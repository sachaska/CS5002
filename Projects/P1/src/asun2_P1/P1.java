package asun2_P1;

import java.util.Scanner;

/**
 * The P1 program implements an application that letting user plays 3*3
 * Tic-Tac-Toe game as many times as they command.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class P1 {
    static final char STANDARD_YES = 'Y';   // the standard input of repeat
    static final char STANDARD_NO = 'N';    // the standard input of exit

    /**
     * The main method create TicTacToe object for the game, play the game
     * and display game results, let player repeat the game as they want.
     *
     * @param args A String reference containing command line arguments.
     */
    public static void main(String[] args) {
        TicTacToe tic = new TicTacToe();
        Scanner keyboard = new Scanner(System.in);
        System.out.println(getHello());
        do {
            singleGame(tic, keyboard);
        } while (getRepeat(keyboard));
        System.out.println(getBye());
        keyboard.close();
    }

    /**
     * The singleGame method initialize every single game.
     *
     * @param tic TicTacToe Object of the game.
     * @param keyboard A Scanner object for capturing user input.
     */
    public static void singleGame(TicTacToe tic, Scanner keyboard) {
        // Play game, display process and winner
        displayWinner(getWinner(tic, keyboard), tic);
        // Display result board
        displayBoard(tic);
        // Display stats
        displayStats(tic);
    }

    /**
     * The displayStats method display game stats.
     *
     * @param tic TicTacToe Object of the game.
     */
    public static void displayStats(TicTacToe tic) {
        final String TITLE = "Game Stats\n";
        String xWon = "X has won " +
                tic.getStatus(Winner.X.ordinal()) + " " +
                "games.\n";
        String oWon = "O has won " +
                tic.getStatus(Winner.O.ordinal()) + " " +
                "games.\n";
        String tie = "There have been " +
                tic.getStatus(Winner.TIE.ordinal()) + " " +
                "tie games.";
        System.out.println("\n" + TITLE + xWon + oWon + tie);
    }

    /**
     * The getWinner method gets the current game winner.
     *
     * @param tic TicTacToe Object of the game.
     * @param keyboard A Scanner object for capturing user input.
     * @return Winner Object.
     */
    public static Winner getWinner(TicTacToe tic, Scanner keyboard) {
        Winner winner;
        tic.newGame();
        do {
            displayBoard(tic);
            if (tic.getRound()%2 == 0)
                playerMove(keyboard, Player.X, tic);
            else
                playerMove(keyboard, Player.O, tic);
            tic.setWinner();
            winner = tic.getWinner();
        }while (winner == null);
        keyboard.nextLine();
        return winner;
    }

    /**
     * The displayBoard method displays current board.
     * @param tic TicTacToe Object of the game.
     */
    public static void displayBoard(TicTacToe tic) {
        System.out.print("\n" +tic);
    }


    /**
     * The playerMove method capture user input for moving chess, pass the
     * values to updateBoard method implements the move.
     *
     * @param keyboard A Scanner object for capturing user input.
     * @param p A Player object implements the move.
     * @param tic TicTacToe Object of the game.
     */
    public static void playerMove(Scanner keyboard, Player p, TicTacToe tic) {
        System.out.print(p.name() + ", it is your turn.\n" +
                "Which row? ");
        int row = keyboard.nextInt();
        System.out.print("Which column? ");
        int col = keyboard.nextInt();
        if (!tic.updateBoard(p, row, col)) {
            System.out.println("Bad location, try again...");
        }
    }

    /**
     * The displayWinner method display game result of each game, and update
     * the status.
     *
     * @param winner Winner Object of winner.
     * @param tic TicTacToe Object of the game.
     */
    public static void displayWinner(Winner winner, TicTacToe tic) {
        String str;
        if (winner.ordinal() == Winner.X.ordinal())
            str = "X is the winner!";
        else if(winner.ordinal() == Winner.O.ordinal())
            str = "O is the winner!";
        else
            str = "No winner - it was a tie!";
        System.out.println(str);
        tic.updateStatus(winner.ordinal());
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
        final String PROMPT_MSG = "Do you want to play again? ";
        do {
            System.out.print(PROMPT_MSG);
            try {
                input = keyboard.nextLine().toUpperCase().charAt(0);
            } catch (Exception e) {
                input = TicTacToe.DEFAULT;
            }
        } while (input != STANDARD_YES && input != STANDARD_NO);
        System.out.println();
        return input == STANDARD_YES;
    }

    /**
     * The getHello method return the hello message.
     *
     * @return A String reference containing hello message.
     */
    public static String getHello() {
        return "Welcome to TicTacToe!\n";
    }

    /**
     * The getBye method return the goodbye message.
     *
     * @return A String reference containing goodbye message.
     */
    public static String getBye() {
        return "Thank you for playing TicTacToe!";
    }
}
