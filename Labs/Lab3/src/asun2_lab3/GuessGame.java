package asun2_lab3;

import java.util.Random;

/**
 * The guessing game class allows you to guess a number. If your guess is
 * too high, the program will state whether your guess is too high/low.
 * If you guess the number, the program will say that you've guess the number.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class GuessGame {
    static final int DEFAULT = 0;               // the default value
    private int rangeMinimum;                   // target minimum range
    private int rangeMaximum;                   // target maximum range
    private Random randomNumber = new Random(); // random object
    private int target;                         // target number
    private int count;                          // guess time

    /**
     * Constructor initializes all variables,
     * pass in the range for choosing target integers.
     */
    public GuessGame(int rangeMinimum, int rangeMaximum) {
        this.rangeMinimum = rangeMinimum;
        this.rangeMaximum = rangeMaximum;
        count = DEFAULT;
    }

    /**
     * The displayStatistics displays number of guesses.
     */

    public void displayStatistics() {
        System.out.printf("""
                That's correct!
                You guessed %d times.
                """, getCount());
    }

    /**
     * The newTarget method randomly chooses a new integer in this game's range
     * which is now the new target.
     */
    public void newTarget() {
        target = randomNumber.nextInt(getRangeMinimum() - 1,
                getRangeMaximum()) + 1;
    }

    /**
     * The guess method processes the user's guess,
     * returns false if number is incorrect;
     * otherwise, returns true.
     *
     * @param num the users guess.
     * @return boolean value represents guess is true or false.
     */
    public boolean guess(int num) {
        boolean guess;
        guess = num == getTarget();
        if (!guess)
            displayHint(num);
        return guess;
    }

    /**
     * The getRangeMinimum method gets rangeMinimum.
     * @return RangeMinimum.
     */
    public int getRangeMinimum() {
        return rangeMinimum;
    }

    /**
     * The getRangeMaximum method gets rangeMaximum.
     * @return rangeMaximum.
     */
    public int getRangeMaximum() {
        return rangeMaximum;
    }

    /**
     * The getTarget method gets target.
     * @return target.
     */
    public int getTarget() {
        return target;
    }

    /**
     * The setCount method sets count.
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * The setCount method is an accumulator of count.
     */
    public void setCount() {count++;}

    /**
     * The getCount method is getter of count.
     * @return count.
     */
    public int getCount() {
        return count;
    }

    /**
     * The displayHint method called from guess function;
     * displays whether the guess was too high or too low.
     *
     * @param num the users guess.
     */
    private void displayHint(int num) {
        String msg;
        if (getTarget() > num) {
            msg = " is too low.";
        }
        else {
            msg = " is too high.";
        }
        System.out.println(num + msg);
    }
}
