package asun2_lab8;

import java.util.ArrayList;
import java.util.List;

/**
 * This program tests the Stack and Queue classes.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class Lab8 {
    /**
     * The main method tests the Stack and Queue classes with test data, and
     * display the result in command line.
     *
     * @param args A String array reference containing command line argument.
     */
    public static void main(String[] args) {
        // Create a StringBuilder object.
        StringBuilder builder = new StringBuilder();

        // Test generic Stack.
        Stack<String> strStack = new Stack<>();
        testStack(strStack, "STRING", builder, testData("String"));

        Stack<Double> numStack = new Stack<>();
        testStack(numStack, "DOUBLE", builder, testData("DOUBLE"));


        // Test generic Queue.
        Queue<String> strQueue = new Queue<>();
        testQueue(strQueue, "STRING", builder, testData("String"));

        Queue<Double> numQueue = new Queue<>();
        testQueue(numQueue, "DOUBLE", builder , testData("DOUBLE"));

        // Print out the result.
        System.out.println(builder);
    }

    /**
     * The testData method provides ArrayList test data for test.
     *
     * @param type A String containing the data type of test data.
     * @return An ArrayList object with test data.
     */
    public static ArrayList testData(String type) {
        if (type.equals("DOUBLE"))
            return new ArrayList<>(List.of(1.1, 2.2, 3.3, 4.4));

        else
            return new ArrayList<>(List.of("APP", "BAL", "CAT", "DOG"));
    }

    /**
     * The testQueue method test generic queue.
     * @param queue A queue to test.
     * @param type A String represents data type of queue.
     * @param builder A StringBuilder object to holds result.
     * @param list Test data.
     */
    public static void testQueue(Queue queue,
                                 String type, StringBuilder builder,
                                   ArrayList list) {
        builder.append("*****A QUEUE OF ").append(type).append(" VALUE" +
                        ".*****\n" + "Testing empty method: " + "(true) ")
                .append(queue.empty()).append("\n");

        for (Object o : list)
            queue.enqueue(o);

        builder.append("Testing enqueue method. " +
                "ADDED ELEMENTS. NOW QUEUE IS:\n").append(queue);
        builder.append("Testing empty method: " + "(false) ")
                .append(queue.empty()).append("\n");

        queue.dequeue();

        builder.append("Testing dequeue method. NOW QUEUE IS:\n" + queue);

        builder.append("Testing peek method. THE ELEMENT IS:\n"
                + queue.peek() + "\n")
                .append("Current queue\n").append(queue);
    }

    /**
     * The testStack method test generic stack.
     * @param stack A stack to test.
     * @param type A String represents data type of queue.
     * @param builder A StringBuilder object to holds result.
     * @param list Test data.
     */
    public static void testStack(Stack stack, String type,
                                 StringBuilder builder, ArrayList list) {
        builder.append("*****A STACK OF ").append(type).append(" VALUE" +
                        ".*****\n" +
                        "Testing empty method: " + "(true) ")
                .append(stack.empty()).append("\n");

        for (Object o: list)
            stack.push(o);

        builder.append("Testing push method. " +
                "ADDED THREE TEST OBJECT. NOW STACK IS:\n").append(stack);

        builder.append("Testing empty method: " + "(false) ")
                .append(stack.empty() + "\n");

        builder.append("Testing pop method").append("The value been pop:")
                .append(stack.pop()).append("\n")
                .append("Current stack:\n").append(stack);

        builder.append("Testing peek method").append("The value been peek:")
                .append(stack.peek() + "\n")
                .append("Current stack:\n").append(stack);
    }
}
