package asun2_lab7;
/**
 * RenderQueue class implements doublely queue for Render Command.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class RenderQueue {
    private Node head;  // head of the queue
    private Node tail;  // tail of the queue

    /**
     * The Node class is used to implement the doubly linked list.
     */
    private class Node {
        /**
         * Hold element of queue.
         */
        RenderCommand value;

        /**
         * Points to the previous node in doubly linked list.
         */
        Node prev;

        /**
         * Points to next node in doubly linked list.
         */
        Node next;

        /**
         * The Node constructor initializes each instance of the Node class.
         *
         * @param value  The data that is stored in Node
         * @param prev  A pointer to the previous Node
         * @param next  A pointer to the succeeding Node
         */
        public Node(RenderCommand value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * The no-arg constructor initializes each new instance of the class.
     */
    public RenderQueue() {
        head = tail = null;
    }

    /**
     * The empty method check whether queue is empty or not.
     *
     * @return A boolean value represents empty status.
     */
    public boolean empty() {
        return head == null;
    }

    /**
     * Adds items to tail end of queue.
     *
     * @param element The data that is being added to the queue
     */
    public void enqueue(RenderCommand element) {
        Node newTail;   //holds new Node for item to be added to end of queue

        newTail = new Node(element, null, null);

        //if the queue is empty
        if (empty()) {
            head = newTail;
            tail = newTail;
        }

        //else the queue is not empty, add to tail
        else {
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
    }

    /**
     * Removes and returns the item at front(head) of queue.
     *
     * @return item at head of queue (the first thing in)
     * @throws IllegalArgumentException user tries to dequeue an empty queue
     */
    public RenderCommand dequeue() {
        if (empty())
            throw new IllegalArgumentException("Can't pop an empty queue.");

        RenderCommand element = head.value;

        //when item x is the last item in queue.
        if (head.next == null && tail.prev == null) {
            head = null;
            tail = null;
        }

        // If is not.
        else {
            head = head.next;
            head.prev = null;
        }

        return element;
    }

    /**
     * Copy current queue to new queue.
     *
     * @return New queue contains copy data.
     */
    public RenderQueue copy() {
        Node p;         //reference to head Node
        RenderQueue copyObject = new RenderQueue();

        for (p = this.head; p != null; p = p.next) {
            copyObject.enqueue(p.value);
        }
        return copyObject;
    }

    /**
     * Appends a copy of the given queue onto the current queue.
     */
    public void append(RenderQueue queue) {
        for (Node curr = queue.head; curr != null; curr = curr.next)
            enqueue(curr.value);
    }

    /**
     * The formString method creates a queue from input String.
     *
     * @param arguments input String for queue.
     * @return A queue object creates from arguments.
     */
    public static RenderQueue fromString(String arguments) {
        RenderQueue outputQueue = new RenderQueue();
        for (int index = 0; index < arguments.length(); index++) {
            switch (arguments.charAt(index)) {
                case 'F':
                    outputQueue.enqueue(RenderCommand.FORWARD);
                    break;
                case 'R':
                    outputQueue.enqueue(RenderCommand.FORWARD2);
                    break;
                case 'X':
                    outputQueue.enqueue(RenderCommand.IGNORE);
                    break;
                case '-':
                    outputQueue.enqueue(RenderCommand.RIGHT);
                    break;
                case '+':
                    outputQueue.enqueue(RenderCommand.LEFT);
                    break;
                case '[':
                    outputQueue.enqueue(RenderCommand.PUSH);
                    break;
                case ']':
                    outputQueue.enqueue(RenderCommand.POP);
                    break;
                default:
                    break;
            }
        }
        return outputQueue;
    }

    /**
     * Returns String representation of a RenderQueue object
     * @return String representation of a RenderQueue object
     * @throws IllegalArgumentException there is an invalid RenderCommand
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Node i = head; i != null; i = i.next) {
            switch (i.value) {
                case FORWARD:
                    sb.append('F');
                    break;
                case FORWARD2:
                    sb.append('R');
                    break;
                case IGNORE:
                    sb.append('X');
                    break;
                case RIGHT:
                    sb.append('-');
                    break;
                case LEFT:
                    sb.append('+');
                    break;
                case PUSH:
                    sb.append('[');
                    break;
                case POP:
                    sb.append(']');
                    break;
            }
        }
        return sb.toString();
    }

}
