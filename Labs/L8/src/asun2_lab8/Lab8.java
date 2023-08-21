package asun2_lab8;

/**
 * This program tests the Stack and Queue classes.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class Lab8 {

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        // Test genetic Stack
        Stack<Test> stack = new Stack<>();
        testStack(stack, builder);

        //Test genetic Queue
        Queue<Test> queue = new Queue<>();
        System.out.println(testQueue(queue, builder));
    }

    public static String testQueue(Queue<Test> queue, StringBuilder builder) {
        builder.append("*****A QUEUE OF TEST OBJECT.*****\n" +
                "Testing empty method: " + "(true) ")
                .append(queue.empty()).append("\n");
        Test objectA = new Test('A');
        Test objectB = new Test('B');
        queue.enqueue(objectA);
        queue.enqueue(objectB);
        builder.append("Testing enqueue method. " +
                "ADDED TWO TEST OBJECT. NOW QUEUE IS:\n").append(queue);
        builder.append("Testing empty method: " + "(false) ")
                .append(queue.empty()).append("\n");
        Queue<Test> otherQueue = queue.copy();
        builder.append("Testing copy method. NEW QUEUE IS:\n" + otherQueue);
        queue.append(otherQueue);
        builder.append("Testing append method. RESULT:\n" + queue);
        queue.dequeue();
        builder.append("Testing dequeue method. NOW QUEUE IS:\n" + queue);
        builder.append("Testing equals method: (false)" +
                queue.equals(otherQueue));
        return builder.toString();
    }

    public static String testStack(Stack<Test> stack, StringBuilder builder) {
        builder.append("*****A STACK OF TEST OBJECT.*****\n" +
                        "Testing empty method: " + "(true) ")
                .append(stack.empty()).append("\n");
        Test objectC = new Test('C');
        Test objectD = new Test('D');
        Test objectE = new Test('E');
        stack.push(objectC);
        stack.push(objectD);
        stack.push(objectE);
        builder.append("Testing push method. " +
                "ADDED THREE TEST OBJECT. NOW STACK IS:\n").append(stack);
        builder.append("Testing empty method: " + "(false) ")
                .append(stack.empty() +
                        "\n");
        Stack<Test> otherStack = stack.copy();
        builder.append("Testing copy method. NEW STACK IS:\n" + otherStack);
        builder.append("Testing equals method: (true)" +
                stack.equals(otherStack));
        Test popObject = stack.pop();
        builder.append("Testing pop method. NOW STACK IS:\n"
                + stack).append("The OBJECT been pop:")
                .append(popObject).append("\n");

        return builder.toString();
    }
}

class Test {
    private static int number = 0;
    private char code;
    private int index;

    public char getCode() {
        return code;
    }

    public Test(char code) {
        this.code = code;
        index = number++;
    }

    public String toString() {
        return index + " " + code + "\n";
    }

    public boolean equals(Test obj) {
        return code == obj.getCode();
    }
}