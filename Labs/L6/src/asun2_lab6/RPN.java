package asun2_lab6;

import java.util.Scanner;

public class RPN {
    private Stack stack;             // stack for calculate

    private final int SIZE = 6;
    public RPN() {
        stack = new Stack(SIZE);
    }

    public double evaluate(String input) {
        Scanner inputString = new Scanner(input);
        double first, last, result;

        while (inputString.hasNext()) {

            if (inputString.hasNextDouble())
                stack.push(inputString.nextDouble());
            else {
                String operator = inputString.next();

                try {
                    last = stack.pop();
                    first = stack.pop();

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
