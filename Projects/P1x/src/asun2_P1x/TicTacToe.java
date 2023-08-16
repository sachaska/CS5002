package asun2_P1x;

import java.util.Arrays;
/**
 * The TicTacToe class implements essential functions of game changeable
 * Tic-Tac-Toe.
 *
 * @author Ai Sun
 * @version 1.0
 */
enum Player {X, O}
enum Winner {X, O, TIE}
public class TicTacToe {
    public static final int DEFAULT = 0;
    public static final String EMPTY = " ";
    public int size;
    private int round;
    private Winner winner;
    private String[][] board;
    private int[] stats;

    /**
     * Constructor.
     *
     * @param size A integer value holds size of board.
     */
    public TicTacToe(int size) {
        this.size = size;
        board = new String[size][size];
        stats = new int[Winner.values().length];
    }

    /**
     * The updateRound method update the attribute round.
     */
    private void updateRound() {
        round++;
    }

    /**
     * The updateStatus method update value of certain index of attribute
     * status.
     *
     * @param index Index to update value.
     */
    public void updateStatus(int index) {
        stats[index]++;
    }

    /**
     * The getStatus method gets value of certain index of attribute status.
     *
     * @param index Index to return value.
     * @return Integer value.
     */
    public int getStatus(int index) {
        return stats[index];
    }

    /**
     * The newGame method sets attribute round, winner and board to default.
     */
    public void newGame() {
        round = DEFAULT;
        winner = null;
        for (String[] strings : board) {
            Arrays.fill(strings, EMPTY);
        }
    }

    /**
     * The isEmpty method returns boolean value of certain index of attribute
     * board has been place chess or not.
     *
     * @param row A value of row.
     * @param column A value of column.
     * @return A boolean value represents has been place chess or not.
     */
    private boolean isEmpty(int row, int column) {
        return board[row][column].equals(EMPTY);
    }

    /**
     * The isFull method returns is board full or not.
     * @return A boolean value represents is board full or not.
     */
    private boolean isFull() {
        return round == size*size;
    }

    /**
     * The updateBoard methods implements user move, place String type X\O to
     * certain index of attribute board, returns is move successful or not.
     * If move successful, update attribute round.
     *
     * @param piece A Player object.
     * @param row A value of row.
     * @param column A value of column.
     * @return A boolean value represents move successful or not.
     */
    public boolean updateBoard(Player piece, int row, int column) {
        final boolean ADDED = true;
        final boolean FAILED = false;
        if (row > board.length - 1 || column > board[0].length - 1
        || (!isEmpty(row, column))) {
            return FAILED;
        }
        else {
            updateRound();
            board[row][column] = piece.name();
            return ADDED;
        }
    }

    /**
     * The getRound methods gets value of attribute round.
     *
     * @return A integer value of round.
     */
    public int getRound() {
        return round;
    }

    /**
     * The toString method return class object to string.
     * @return A String reference containing current board.
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("    ");
        for (int i = 0; i < board.length; i++)
            str.append(String.format("%2d", i)).append("  ");
        str.append("\n");
        for (int i = 0; i < board.length; i++) {
            str.append(String.format("%2d", i)).append(" ");
            for (int j = 0; j < board[i].length; j++) {
                str.append(" ").append(board[i][j]).append(" |");
            }
            str.append("\n");
            //str.append("\n" + "  ------------" + "\n");
        }
        return str.toString();
    }

    /**
     * The setWinner method sets winner attribute.
     */
    public void setWinner() {
        String winner = null;
        int equalCount;

        // Check winner on each row
        for (String[] strings : board) {
            equalCount = 1;
            if (!strings[0].equals(EMPTY)) {
                for (int i = 1; i < board.length; i++) {
                    if (strings[0].equals(strings[i]))
                        equalCount++;
                }
                if (equalCount == board.length)
                    winner = strings[0];
            }
        }

        // Check winner on each column
        for (int l = 0; l < board[0].length; l++) {
            equalCount = 1;
            if (!board[0][l].equals(EMPTY)) {
                for (int i = 1; i < board.length; i++) {
                    if(board[0][l].equals(board[i][l]))
                        equalCount++;
                }
                if (equalCount == board.length)
                    winner = board[0][l];
            }
        }

        // Check winner on diagonal
        if(!board[0][0].equals(EMPTY)) {
            equalCount = 1;
            for (int i = 1; i < board.length; i++) {
                if (board[0][0].equals(board[i][i]))
                    equalCount++;
                if (equalCount == board.length)
                    winner = board[0][0];
            }
        }

        // Check winner on side diagonal
        if (!board[0][board.length - 1].equals(EMPTY)) {
            equalCount = 1;
            for (int i = 1; i < board.length; i++) {
                if(board[0][board.length - 1].
                        equals(board[i][board.length - 1 - i]))
                    equalCount++;
            }
            if (equalCount == board.length) {
                    winner = board[0][board.length - 1];
            }
        }
        if (winner != null) {
            this.winner = Winner.valueOf(winner);
        }
        else if(isFull())
            this.winner = Winner.TIE;
    }

    /**
     * The getWinner method gets value of winner attribute.
     * @return A Winner object of attribute winner.
     */
    public Winner getWinner() {
        return winner;
    }
}

