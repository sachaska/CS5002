package asun2_lab6;

import java.util.ArrayList;

public class Stack {
    private ArrayList<Double> s;    // the ArrayList to holds stack data
    private int top;                // pointer to stack top
    private final int EMPTY = -1;    // pointer value when stack is empty
    private int size;               // the size of Stack

    public Stack(int size) {
        this.size = size;
        s = new ArrayList<>(size);
        top = EMPTY;
    }

    public boolean full() {
        return top == (size - 1);
    }

    public void push(double element) {
        if (full())
            throw new StackOverFlowException("Stack is full!");
        s.add(element);
        top++;
    }

    public boolean empty() {
        return top == EMPTY;
    }

    public double pop() {
        if (empty())
            throw new EmptyStackException("Stack is empty!");
        else
            return s.remove(top--);
    }

    public double peek() {
        if (empty())
            throw new EmptyStackException("Stack is empty!");
        else
            return s.get(top);
    }
}
class EmptyStackException extends RuntimeException {
    public EmptyStackException(String message) {
        super(message);
    }
}

class StackOverFlowException extends RuntimeException {
    public StackOverFlowException(String message) {
        super(message);
    }
}