package asun2_p3;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {
    private Stack shuffledDeck;     // Stack of shuffled cards
    private Stack discard;          // Stack of discard pile
    private Queue[] playerQueue;    // Player card queue list
    private ArrayList<Integer> cardDeck; // Card deck
    private int playerCount;        // Player count
    private final int PAIR = 4;
    private final int CARD = 13;

    /**
     * Constructor, initializes playerCount and cardDeck.
     * @param playerCount Number of players.
     */
    public GameModel(int playerCount) {
        this.playerCount = playerCount;
        setCardDeck();
    }

    public String test() {
        initGame();
        return showPlayerQueue(0);
    }


    private boolean initGame() {
        final boolean SUCCESS = true;   // A flag of method.

        // Shuffle cards.
        shuffleDeck(cardDeck);
        initShuffledDeck();
        // Initialize playerQueue.
        initPlayerQueue();
        // Initialize discardDeck.
        discard = new Stack();

        return SUCCESS;
    }


    private void initPlayerQueue() {
        final int COUNTS = 7;
        createQueueList(playerCount);
        for (int i = 0; i < COUNTS; i++) {
            for (Queue player: playerQueue)
                player.enqueue(shuffledDeck.pop());
        }
    }

    private void initShuffledDeck() {
        shuffledDeck = new Stack();
        for (int element : cardDeck)
            shuffledDeck.push(element);
    }

    private String showPlayerQueue(int player) {
        StringBuilder builder = new StringBuilder();
        builder.append("|");
        for (Node current = playerQueue[player].front;
             current != null; current = current.next) {
            builder.append(String.format("%3d |", current.value));
        }
        return builder.toString();
    }

    /**
     * The createQueueList method initializes playerQueue.
     * @param playerCount Number of players.
     */
    private void createQueueList(int playerCount) {
        playerQueue = new Queue[playerCount];
        for (int i = 0; i < playerCount; i++)
            playerQueue[i] = new Queue();
    }

    /**
     * The setShuffledCards method initializes shuffledCards.
     */
    private void setCardDeck() {
        cardDeck = new ArrayList<>();
        for (int pair = 0; pair < PAIR; pair++) {
            for (int card = 1; card <= CARD; card++) {
                cardDeck.add(card);
            }
        }
    }

    /**
     * Shuffles the cards using the
     * <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">
     * Fisher-Yates algorithm</a>
     * @param cards deck to shuffle
     */
    private void shuffleDeck(ArrayList<Integer> cards) {
        Random rand = new Random();
        for (int i = cards.size(); i > 1; i--) {
            int j = rand.nextInt(i);
            int temp = cards.get(i - 1);
            cards.set(i - 1, cards.get(j));
            cards.set(j, temp);
        }
    }


    class Queue {
        private Node front; // Head Node of queue.
        private Node rear;  // Tail Node of queue.

        /**
         * Constructor.
         */
        public Queue() {
            front = rear = null;
        }

        /**
         * The isEmpty method check queue empty status.
         * @return Boolean value of empty status.
         */
        public boolean isEmpty() {
            return front == null;
        }

        /**
         * The enqueue method input integer at the tail of queue.
         * @param element The integer value to input.
         */
        public void enqueue(int element) {
            if (isEmpty())
                rear = front = new Node(element, null, null);

            else {
                Node newRear = new Node(element, rear, null);
                rear.next = newRear;
                rear = newRear;
            }
        }

        /**
         * The dequeue method move integer at the front of queue.
         * @return Integer been moved.
         */
        public int dequeue() {
            if (isEmpty())
                throw new EmptyQueueException("Dequeue error, queue empty.");

            int element = front.value;

            if (front.next == null)
                front = rear = null;
            else {
                front = front.next;
                front.prev = null;
            }

            return element;
        }
    }

    class Node {
        private int value;
        private Node prev;
        private Node next;

        /**
         * Constructor.
         * @param value Integer value of Node.
         * @param prev Previous Node reference.
         * @param next Next Node reference.
         */
        public Node(int value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    class Stack {
        private Node top;         // Top of stack.
        private int size;         // Size of stack.
        private int elementCount; // Count of stack element.

        public Stack() {
            top = null;
            size = CARD * PAIR;
            elementCount = 0;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public void push(int element) {
            if (isEmpty())
                top = new Node(element, null, null);

            else {
                if (elementCount == size)
                    throw new StackOverFlowException("Push error, stack full.");
                else {
                    Node newTop = new Node(element, null, null);
                    newTop.next = top;
                    top = newTop;
                }
            }

            elementCount++;
        }

        public int pop() {
            if (isEmpty())
                throw new EmptyStackException("Pop error, stack empty.");

            int element = top.value;
            top = top.next;
            top.prev = null;
            elementCount--;
            return element;
        }

        public int peek() {
            if (isEmpty())
                throw new EmptyStackException("Peek error, stack empty.");

            int element = top.value;
            return element;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (Node current = top; current != null;
            current = current.next) {
                stringBuilder.append(String.format("%3d" , current.value));
            }
            return stringBuilder.toString();
        }
    }

    class EmptyQueueException extends RuntimeException {
        public EmptyQueueException(String message) {
            super(message);
        }
    }

    class StackOverFlowException extends RuntimeException {
        public StackOverFlowException(String message) {
            super(message);
        }
    }

    class EmptyStackException extends RuntimeException {
        public EmptyStackException(String message) {
            super(message);
        }
    }

}
