import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private final int HAND_SIZE = 5;
    private int again = 1;

    // instantiate Deck and Player
    Scanner scan = new Scanner(System.in);
    Deck deck = new Deck();
    Player black = new Player("Black");
    Player white = new Player("White");
    Card[] hand;

    // plays the game
    public void play() {
        while (again == 1) {
            // fill deck
            deck.fillDeck();

            // shuffle
            deck.shuffle();

            // player draws
            hand = black.draw(deck, 0);

            // sort hand		
            Arrays.sort(hand);

            this.checkHand(hand, black.getName());

            // sort hand		
            Arrays.sort(hand);

            // evaluate the hand
            this.evaluate(black);

            hand = white.draw(deck, 5);
            Arrays.sort(hand);
            this.checkHand(hand, white.getName());
            Arrays.sort(hand);
            this.evaluate(white);

            String win = "";
            if (black.getScore() > white.getScore()) {
                win = winsWith(black.getScore());
                System.out.println("Black wins " + win);
            } else if (black.getScore() < white.getScore()) {
                win = winsWith(white.getScore());
                System.out.println("White wins " + win);
            } else if (black.getScore() == 0 && white.getScore() == 0) {
                if (black.getHigh() > white.getHigh()) {
                    win = winsWith(black.getScore());
                    System.out.println("Black wins with " + win + black.getHigh());
                } else if(black.getHigh() < white.getHigh()) {
                    win = winsWith(white.getScore());
                    System.out.println("White wins with " + win + white.getHigh());
                }
                else if(black.getHigh() == white.getHigh()) {
                    System.out.println("Tie!");
                }
            } else {
                System.out.println("Tie!");
            }

            // play again?
            this.again();
        }
        System.out.println("Thanks for playing!");
    }

    public String winsWith(int score) {
        String rank = "";
        switch (score) {
            case 0:
                rank = "with high card ";
                break;
            case 1:
                rank = "with a pair";
                break;
            case 2:
                rank = "with two pairs";
                break;
            case 3:
                rank = "with a three of a kind";
                break;
            case 4:
                rank = "with a straight";
                break;
            case 5:
                rank = "with a flush";
                break;
            case 6:
                rank = "with a full house";
                break;
            case 7:
                rank = "with a four of a kind";
                break;
            case 8:
                rank = "with a straight flush";
                break;
            case 9:
                rank = "with a royal flush";
                break;
            default:
                break;
        }
        return rank;
    }

    // tells player cards in hand
    public void checkHand(Card[] hand, String name) {
        System.out.print(name + ": ");
        for (int handCounter = 0; handCounter < HAND_SIZE; handCounter++) {
            this.display(hand[handCounter]);
        }
    }

    // evaluates the hand
    public void evaluate(Player player) {
        System.out.println("");
        if (this.royalFlush() == 1) {
//            System.out.println(player.getName() + " has a royal flush!");
            player.setScore(9);
        } else if (this.straightFlush() == 1) {
//            System.out.println(player.getName() + " has a straight flush!");
            player.setScore(8);
        } else if (this.fourOfaKind() == 1) {
//            System.out.println(player.getName() + " has four of a kind!");
            player.setScore(7);
        } else if (this.fullHouse() == 1) {
//            System.out.println(player.getName() + " has a full house!");
            player.setScore(6);
        } else if (this.flush() == 1) {
//            System.out.println(player.getName() + " has a flush!");
            player.setScore(5);
        } else if (this.straight() == 1) {
//            System.out.println(player.getName() + " has a straight!");
            player.setScore(4);
        } else if (this.triple() == 1) {
//            System.out.println(player.getName() + " has a triple!");
            player.setScore(3);
        } else if (this.twoPairs() == 1) {
//            System.out.println(player.getName() + " has two pairs!");
            player.setScore(2);
        } else if (this.pair() == 1) {
//            System.out.println(player.getName() + " has a pair!");
            player.setScore(1);
        } else {
            int highCard = this.highCard();
            player.setHigh(highCard);
//            System.out.println(player.getName() + "s highest card is " + highCard);
            player.setScore(0);
        }
    }

    // checks for a royal flush
    public int royalFlush() {
        if (hand[0].rank == 1 && hand[1].rank == 10 && hand[2].rank == 11
                && hand[3].rank == 12 && hand[4].rank == 13) {
            return 1;
        } else {
            return 0;
        }
    }

    // checks for a straight flush
    public int straightFlush() {
        for (int counter = 1; counter < 5; counter++) {
            if (hand[0].suit != hand[counter].suit) {
                return 0;
            }
        }
        for (int counter2 = 1; counter2 < 5; counter2++) {
            if (hand[counter2 - 1].rank != (hand[counter2].rank - 1)) {
                return 0;
            }

        }
        return 1;

    }

    // checks for four of a kind
    public int fourOfaKind() {
        if (hand[0].rank != hand[3].rank && hand[1].rank != hand[4].rank) {
            return 0;
        } else {
            return 1;
        }
    }

    // checks for full house
    public int fullHouse() {
        int comparison = 0;
        for (int counter = 1; counter < 5; counter++) {
            if (hand[counter - 1].rank == hand[counter].rank) {
                comparison++;
            }
        }
        if (comparison == 3) {
            return 1;
        } else {
            return 0;
        }
    }

    // checks for flush
    public int flush() {
        for (int counter = 1; counter < 5; counter++) {
            if (hand[0].suit != hand[counter].suit) {
                return 0;
            }
        }
        return 1;
    }

    // check for straight
    public int straight() {
        for (int counter2 = 1; counter2 < 5; counter2++) {
            if (hand[counter2 - 1].rank != (hand[counter2].rank - 1)) {
                return 0;
            }

        }
        return 1;
    }

    // checks for triple
    public int triple() {
        if (hand[0].rank == hand[2].rank || hand[2].rank == hand[4].rank) {
            return 1;
        }
        return 0;
    }

    // checks for two pairs
    public int twoPairs() {
        int check = 0;
        for (int counter = 1; counter < 5; counter++) {
            if (hand[counter - 1].rank == hand[counter].rank) {
                check++;
            }
        }
        if (check == 2) {
            return 1;
        } else {
            return 0;
        }
    }

    // check for pair
    public int pair() {
        int check = 0;
        for (int counter = 1; counter < 5; counter++) {
            if (hand[counter - 1].rank == hand[counter].rank) {
                check++;
            }
        }
        if (check == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    // find highest card
    public int highCard() {
        int highCard = 0;
        for (int counter = 0; counter < 5; counter++) {
            if (hand[counter].rank > highCard) {
                highCard = hand[counter].rank;
            }
        }
        return highCard;
    }

    // asks user if they want to play again
    public void again() {
        System.out.print("Play again? (1 for yes, 0 for no)");
        again = scan.nextInt();
    }

    // generates string for each card in hand
    public void display(Card card) {
        if (card.rank == 1) {
            System.out.print("A");
        }
        if (card.rank == 2) {
            System.out.print("2");
        }
        if (card.rank == 3) {
            System.out.print("3");
        }
        if (card.rank == 4) {
            System.out.print("4");
        }
        if (card.rank == 5) {
            System.out.print("5");
        }
        if (card.rank == 6) {
            System.out.print("6");
        }
        if (card.rank == 7) {
            System.out.print("7");
        }
        if (card.rank == 8) {
            System.out.print("8");
        }
        if (card.rank == 9) {
            System.out.print("9");
        }
        if (card.rank == 10) {
            System.out.print("10");
        }
        if (card.rank == 11) {
            System.out.print("J");
        }
        if (card.rank == 12) {
            System.out.print("Q");
        }
        if (card.rank == 13) {
            System.out.print("K");
        }
        if (card.suit == 1) {
            System.out.print("S ");
//            System.out.println();
        }
        if (card.suit == 2) {
            System.out.print("H ");
//            System.out.println();
        }
        if (card.suit == 3) {
            System.out.print("D ");
//            System.out.println();
        }
        if (card.suit == 4) {
            System.out.print("C ");
//            System.out.println();
        }

    }
}
