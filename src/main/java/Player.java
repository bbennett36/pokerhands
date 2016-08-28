public class Player {

    private String name;
    private int score;
    private int high;

    public Player(String name) {
        this.name = name;
    }

    // gets 5 cards from deck
    public Card[] draw(Deck deck, int index) {
        Card[] hand = deck.deal(index);
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

}
