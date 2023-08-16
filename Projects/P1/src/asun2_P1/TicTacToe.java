package asun2_P1;

import java.util.Arrays;

enum Player {X, O}
enum Winner {X, O, TIE}

/**
 * The TicTacToe class implements essential functions of game 3*3 Tic-Tac-Toe.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class TicTacToe {
    public static final int DEFAULT = 0;
    public static final String EMPTY = " ";
    public static final int SIZE = 3;
    public static final int FULL = SIZE * SIZE;
    private int round;
    private Winner winner;
    private String[][] board;
    private int[] stats;

    /**
     * Constructor.
     */
    public TicTacToe() {
        board = new String[SIZE][SIZE];
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
     * The setWinner method sets winner attribute.
     */
    public void setWinner() {
        String winner = null;
        for (String[] strings : board) {
            if (!strings[0].equals(EMPTY) && strings[0].equals(strings[1])
                    && strings[1].equals(strings[2]))
                winner = strings[0];
        }
        for (int l = 0; l < board[0].length; l++) {
            if (!board[0][l].equals(EMPTY) && board[0][l].equals(board[1][l])
                    && board[1][l].equals(board[2][l]))
                    winner = board[0][l];
        }
        if (!board[0][0].equals(EMPTY) && board[0][0].equals(board[1][1])
                && board[1][1].equals(board[2][2]))
            winner = board[0][0];
        else if (!board[0][2].equals(EMPTY) && board[0][2].equals(board[1][1])
                && board[1][1].equals(board[2][0]))
            winner = board[0][2];
        if (winner != null)
            this.winner =  Winner.valueOf(winner);
        else if(getRound() == FULL)
            this.winner = Winner.TIE;
    }

    /**
     * The getWinner method gets value of winner attribute.
     * @return A Winner object of attribute winner.
     */
    public Winner getWinner() {
        return winner;
    }

    /**
     * The toString method return class object to string.
     * @return A String reference containing current board.
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("   ");
        for (int i = 0; i < board.length; i++)
            str.append(i).append("   ");
        str.append("\n");
        for (int i = 0; i < board.length; i++) {
            str.append(i).append(" ");
            for (int j = 0; j < board[i].length; j++) {
                str.append(" ").append(board[i][j]).append(" |");
            }
            str.append("\n" + "  ------------" + "\n");
        }
        return str.toString();
    }

}

