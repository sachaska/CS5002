package asun2_lab6;

import java.util.Scanner;

/**
 * The RPN class implements a Reverse Polish Notation (RPN) calculator.
 */
public class RPN {
    private final int SIZE = 6;      // stack size
    private Stack stack;             // stack for calculate

    /**
     * Constructor.
     */
    public RPN() {
        stack = new Stack(SIZE);
    }

    /**
     * The evaluate method calculate the result.
     *
     * @param input A String reference of user input.
     * @return A double value of result.
     */
    public double evaluate(String input) {
        double first, last, result;   // holds first number pop from stack
                                      // holds second number pop from stack
                                      // holds the current result

        // Create a new Scanner.
        Scanner inputString = new Scanner(input);

        // Read until EOL
        while (inputString.hasNext()) {

            // Push in every number
            if (inputString.hasNextDouble())
                stack.push(inputString.nextDouble());
            else {
                // Take the operator
                String operator = inputString.next();

                try {
                    last = stack.pop();
                    first = stack.pop();

                    // Calculate and push result in the stack
                    switch (operator) {
                        case "+" -> stack.push(first + last);
                        case "-" -> stack.push(first - last);
                        case "*" -> stack.push(first * last);
                        case "/" -> stack.push(first / last);
                        default ->
                                throw new IllegalArgumentException("Unknown " +
                                        "operator: " + operator);
                    }

                } catch(EmptyStackException e) {
                    throw new IllegalArgumentException("too many operators!");
                }

            }
        }
        // Pop result and return
        result = stack.pop();

        if (!stack.empty())
            throw new IllegalArgumentException("not enough operators!");
        else
            return result;
    }
}
