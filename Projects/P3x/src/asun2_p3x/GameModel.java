package asun2_p3x;

import java.util.ArrayList;
import java.util.Random;

/**
 * The GameModel Class implements card game model.
 *
 * @author Ai Sun
 * @version 1.0
 */
public class GameModel {
    private Stack shuffledDeck;                     // stack of shuffled cards
    private Stack discard;                          // stack of discard pile
    private Queue[] playerQueue;                    // player card queue list
    private ArrayList<Integer> cardDeck;            // card deck
    private int playerCount, currPlayer, discardCard;// player count
                                                     // current player index
                                                     // current discard card
    private StringBuilder builder;                  // StringBuilder for output
    private final int PAIR = 4, CARD = 13, FIRST = 1, TWO = 2;
                                                    // constant value of pair
                                                    // constant value of cards
                                                    // constant value of first
                                                    // constant value of pick
                                                    // two cards
    private final String NO_WINNER = "";            // constant value no winner
    private final String[] players = {"PLAYER_1", "PLAYER_2", "PLAYER_3",
            "PLAYER_4", "PLAYER_5", "PLAYER_6"};
                                                    // constant value of
                                                    // player name

    /**
     * Constructor, initializes playerCount and cardDeck.
     * @param playerCount Number of players.
     */
    public GameModel(int playerCount) {
        this.playerCount = playerCount;

        // Fill original deck with cards.
        setCardDeck();
    }

    /**
     * The gameProcess method implements the game process, and append game
     * process and result in the StringBuilder object.
     */
    public void gameProcess() {
        String winnerName;          // holds winner name

        // Initialize current game.
        initGame();

        // Repeat game process until have winner.
        do {
            try {
                winnerName = playerRound(playerQueue[currPlayer % playerCount]);
            } catch (EmptyStackException e) {
                winnerName = "TIE";
            }
        } while (winnerName.equals(NO_WINNER));

        if (winnerName == "TIE")
            builder.append("There is no card in the Deck. GAME TIE!\n");
        else builder.append("You have won the game!\n");

        // Append game finish message.
        builder.append("\n").append("The game has finished.\n");
    }

    /**
     * The toString method.
     * @return A String reference containing all message from current game.
     */
    public String toString() {
        return builder.toString();
    }

    /**
     * The playerRound method implements the process of single round.
     * @param playerQueue Card queue of current player.
     * @return A String reference containing winner name.
     */
    private String playerRound(Queue playerQueue) {
        int playerCard;         // player card number
        // Update player name.
        String playerName = players[currPlayer % playerCount];
        // Update game round.
        currPlayer++;
        builder.append(String.format("\n%s's turn, cards:\n", playerName));

        // Show player card queue.
        showPlayerQueue(playerQueue);

        // Put one card from Deck in the first round.
        if (currPlayer == FIRST) {
            discardCard = shuffledDeck.pop();
            discard.push(discardCard);
        }
        else
            discardCard = discard.peek();

        // Pick player card in the queue and discard.
        playerCard = playerQueue.dequeue();
        builder.append(String.format("Discard pile card: %d\n", discardCard));
        builder.append(String.format("Your current card: %d\n", playerCard));

        // Player run out of cards, win the game.
        if (playerQueue.isEmpty())
            return playerName;

        // Otherwise, game continue, compare the cards.
        else {
            comparison(playerCard, playerQueue);
            discard.push(playerCard);
            return NO_WINNER;
        }

    }

    /**
     * The comparison method compares current discard card with player card.
     * @param playerCard Integer value of current player card.
     * @param player Current player card queue.
     */
    private void comparison(int playerCard, Queue player) {
        // If player card smaller, player pick two cards.
        if (discardCard > playerCard) {

            for (int i = 0; i < TWO; i++)
                player.enqueue(safePop());

            builder.append("Your card is LOWER, pick up 2 cards.\n");
        }

        // If player card equals to discard card, pick 1 card.
        else if (discardCard == playerCard) {
            player.enqueue(safePop());
            builder.append("Your card is EQUAL, pick up 1 card.\n");
        }

        // If player card is bigger, the turn is over.
        else
            builder.append("Your card is HIGHER, turn is over.\n");
    }

    /**
     * The initGame method initializes the game.
     */
    private void initGame() {
        // Initialize current player.
        currPlayer = 0;
        // Initialize StringBuilder.
        builder = new StringBuilder();
        // Shuffle cards.
        shuffleDeck(cardDeck);
        initShuffledDeck();
        // Initialize playerQueue.
        initPlayerQueue();
        // Initialize discardDeck.
        discard = new Stack();
    }

    /**
     * The safePop method implements a Stack push method with empty handle
     * solution. If shuffledDeck is empty, turn over discard Stack excepts
     * the top.
     * @return A integer value been pop.
     */
    private int safePop() {

        if (shuffledDeck.isEmpty()) {
            // Turn over the deck.
            shuffledDeck = discard;
            discard = new Stack();
            discard.push(shuffledDeck.pop());
        }

        return shuffledDeck.pop();
    }

    /**
     * The initPlayerQueue method initializes player queues with default 7
     * cards.
     */
    private void initPlayerQueue() {
        final int COUNTS = 7;
        createQueueList(playerCount);

        for (int i = 0; i < COUNTS; i++) {
            for (Queue player: playerQueue)
                player.enqueue(shuffledDeck.pop());
        }
    }

    /**
     * The initShuffledDeck method push shuffled cards in shuffledDeck.
     */
    private void initShuffledDeck() {
        shuffledDeck = new Stack();

        for (int element : cardDeck)
            shuffledDeck.push(element);
    }

    /**
     * The showPlayerQueue method append current player Queue status to builder.
     * @param player Current player card Queue.
     */
    private void showPlayerQueue(Queue player) {
        builder.append("|");

        for (Node current = player.front;
             current != null; current = current.next) {
            builder.append(String.format("%3d |", current.value));
        }

        builder.append("\n");
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


    /**
     * The Queue class implements a doublely LinkList Queue.
     */
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

    /**
     * The Node class implements a Node.
     */
    class Node {
        private int value;      // Node integer value
        private Node prev;      // reference of previous Node
        private Node next;      // reference of next Node

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

    /**
     * The Stack class implements a doublely LinkList Stack.
     */
    class Stack {
        private Node top;         // Top of stack.
        private int size;         // Size of stack.
        private int elementCount; // Count of stack element.

        /**
         * Constructor.
         */
        public Stack() {
            top = null;
            size = CARD * PAIR;
            elementCount = 0;
        }

        /**
         The isEmpty method checks for an empty stack.
         @return true if stack is empty, false otherwise.
         */
        public boolean isEmpty() {
            return top == null;
        }

        /**
         The push method adds a new item to the stack.
         @param element The item to be pushed onto the stack.
         */
        public void push(int element) {
            if (isEmpty())
                top = new Node(element, null, null);

            else {
                if (elementCount == size)
                    throw new StackOverFlowException("Push error, stack full.");
                else {
                    Node newTop = new Node(element, null, top);
                    top.prev = newTop;
                    top = newTop;
                }
            }

            elementCount++;
        }

        /**
         The Pop method removes the value at the
         top of the stack.
         @return The value at the top of the stack.
         @exception EmptyStackException When the
         stack is empty.
         */
        public int pop() {
            if (isEmpty())
                throw new EmptyStackException("Pop error, stack empty.");

            int element = peek();

            if (top.next == null)
                top = null;

            else {
                top = top.next;
                top.prev = null;
            }

            elementCount--;
            return element;
        }

        /**
         The peek method returns the top value
         on the stack.
         @return The value at the top of the stack.
         @exception EmptyStackException When the
         stack is empty.
         */
        public int peek() {
            if (isEmpty())
                throw new EmptyStackException("Peek error, stack empty.");

            return top.value;
        }

        /**
         The toString method computes a string
         representation of the contents of the stack.
         @return The string representation of the
         stack contents.
         */
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            for (Node current = top; current != null;
            current = current.next) {
                stringBuilder.append(String.format("%3d" , current.value));
            }

            return stringBuilder.toString();
        }
    }

    /**
     * Empty Queue Exception.
     */
    class EmptyQueueException extends RuntimeException {
        public EmptyQueueException(String message) {
            super(message);
        }
    }

    /**
     * Full Stack Exception.
     */
    class StackOverFlowException extends RuntimeException {
        public StackOverFlowException(String message) {
            super(message);
        }
    }

    /**
     * Empty Stack Exception.
     */
    class EmptyStackException extends RuntimeException {
        public EmptyStackException(String message) {
            super(message);
        }
    }
}
