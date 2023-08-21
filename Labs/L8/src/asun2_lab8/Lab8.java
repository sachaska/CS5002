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

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        ArrayList<Double> doubles = new ArrayList<>(List.of(1.1, 2.2, 3.3));
        ArrayList<String> strings = new ArrayList<>(List.of("APP", "BALL",
                "CAT"));

        // Test genetic Stack
        Stack<String> strStack = new Stack<>();
        testStack(strStack, "STRING", builder, strings);

        Stack<Double> numStack = new Stack<>();
        testStack(numStack, "DOUBLE", builder, strings);


        Queue<String> strQueue = new Queue<>();
        testQueue(strQueue, "STRING", builder, strings);

        Queue<Double> numQueue = new Queue<>();
        testQueue(numQueue, "DOUBLE", builder , doubles);

        System.out.println(builder);
    }

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
                + queue.peek() + "\n");;
    }

    public static void testStack(Stack stack, String type,
                                 StringBuilder builder, ArrayList list) {
        builder.append("*****A STACK OF ").append(type).append("OBJECT.*****\n" +
                        "Testing empty method: " + "(true) ")
                .append(stack.empty()).append("\n");

        for (Object o: list)
            stack.push(o);

        builder.append("Testing push method. " +
                "ADDED THREE TEST OBJECT. NOW STACK IS:\n").append(stack);
        builder.append("Testing empty method: " + "(false) ")
                .append(stack.empty() +
                        "\n");
        builder.append("Testing pop method. NOW STACK IS:\n"
                + stack).append("The OBJECT been pop:")
                .append(stack.pop()).append("\n");
    }
}
