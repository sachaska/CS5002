package asun2_lab6;

import java.util.ArrayList;

/**
 * The Stack class.
 */
public class Stack {
    private ArrayList<Double> s;    // the ArrayList to holds stack data
    private int top;                // pointer to stack top
    private final int EMPTY = -1;    // pointer value when stack is empty
    private int size;               // the size of Stack

    /**
     * Constructor.
     *
     * @param size A integer for Stack size.
     */
    public Stack(int size) {
        this.size = size;
        s = new ArrayList<>(size);
        top = EMPTY;
    }

    /**
     * The full method check whether stack is full.
     *
     * @return A boolean value of Stack fill status.
     */
    public boolean full() {
        return top == (size - 1);
    }

    /**
     * The empty method check whether stack is empty.
     *
     * @return A boolean value of Stack empty status.
     */
    public boolean empty() {
        return top == EMPTY;
    }

    /**
     * The push method pushs element into Stack.
     * @param element The double value to push.
     */
    public void push(double element) {

        if (full())
            throw new StackOverFlowException("Stack is full!");

        s.add(element);
        top++;
    }

    /**
     * The pop method pops element off the Stack.
     * @return The double value being pop.
     */
    public double pop() {

        if (empty())
            throw new EmptyStackException("Stack is empty!");
        else
            return s.remove(top--);

    }

    /**
     * The peek method peeks element from the Stack.
     * @return The double value being peek.
     */
    public double peek() {

        if (empty())
            throw new EmptyStackException("Stack is empty!");
        else
            return s.get(top);

    }
}

/**
 * The EmptyStackException.
 */
class EmptyStackException extends RuntimeException {
    public EmptyStackException(String message) {
        super(message);
    }
}

/**
 * The StackOverFlowException.
 */
class StackOverFlowException extends RuntimeException {
    public StackOverFlowException(String message) {
        super(message);
    }
}