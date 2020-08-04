package bridge.console;

/**
 *
 * @author Luis
 */
public class Card implements Comparable<Object>{
    int suit; // 0-Hearts, 1-Clubs, 2-Diamonds, 3-Spades
    int rank; // 2-10, 11-Jack, 12-Queen, 13-King, 14-Ace

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }
    
    public String getSuitString() {
        String str = null;
        switch(suit){
            case 0: 
                str = "Hearts";
                break;
            case 1: 
                str = "Clubs";
                break;
            case 2:
                str = "Diamonds";
                break;
            case 3:
                str = "Spades";
                break;
            default:
                System.out.print("Fatal error!!!");
                break;
        }
        return str;
    } 

    @Override
    public String toString() {
        return rank + " of " + getSuitString() + ", ";
    }
    @Override
    public int compareTo(Object o) {
        Card c2 = (Card) o;
        if( suit < c2.suit) {
            return -1;
        }
        if( suit > c2.suit) {
            return 1;
        }
        if( rank > c2.rank) {
            return 1;
        }
        if( rank < c2.rank) {
            return -1;
        }
        return 0;
    }

    public boolean compareTo(int firstSuit, Card c2) {
        return suit == firstSuit && rank > c2.rank;
    }
}