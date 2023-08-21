package asun2_lab7;

public class RenderQueue {
    private Node head;
    private Node tail;
    private class Node {
        RenderCommand value;
        Node prev;
        Node next;

        public Node(RenderCommand value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public RenderQueue() {
        head = tail = null;
    }

    public boolean empty() {
        return head == null;
    }

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

    public RenderQueue copy() {
        Node p;         //reference to head Node
        RenderQueue copyObject = new RenderQueue();

        for (p = this.head; p != null; p = p.next) {
            copyObject.enqueue(p.value);
        }
        return copyObject;
    }

    public void append(RenderQueue queue) {
        for (Node curr = queue.head; curr != null; curr = curr.next)
            enqueue(curr.value);
    }

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
