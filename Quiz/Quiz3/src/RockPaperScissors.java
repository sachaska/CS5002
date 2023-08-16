public class RockPaperScissors {
    public static enum Gesture {ROCK, PAPER, SCISSORS};
    private static int count = 0;
    Gesture value;
    public RockPaperScissors() {
        switch (count%Gesture.values().length) {
            case 0:
                value = Gesture.ROCK;
                break;
            case 1:
                value = Gesture.PAPER;
                break;
            case 2:
                value = Gesture.SCISSORS;
                break;
        }
        count++;
    }

    public Gesture getValue() {
        return value;
    }

    public String toString() {
        return value.toString();
    }
}
