package bridge.console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Luis
 */
public class BridgeConsole {

    static Scanner stdin = new Scanner(System.in);
    static Deck deck = new Deck();
    static int playerCard;
    static ArrayList<Card> player = new ArrayList<>();
    static ArrayList<Card> ai1 = new ArrayList<>();
    static ArrayList<Card> ai2 = new ArrayList<>();
    static ArrayList<Card> ai3 = new ArrayList<>();
    static ArrayList<Card> center = new ArrayList<>();
    static int team1 = 0;
    static int team2 = 0;

    public static int getPlyerCard() {
        do {
            System.out.print("\n player hands contains " + player.size() + " cards."
                    + "\n Choose card [index]");
            while (!stdin.hasNextInt()) {
                System.out.print("Please enter a valid card index. [0-13]");
                playerCard = stdin.nextInt();
            }
            return playerCard = stdin.nextInt();
        } while (playerCard < player.size());
    }

    public static void trackScore(ArrayList<Card> p_1, ArrayList<Card> p_2, ArrayList<Card> p_3, ArrayList<Card> p_4, ArrayList<Card> center, int winner) {

        switch (winner) {
            case 0:
                team1++;
                break;
            case 1:
                team2++;
                break;
            case 2:
                team1++;
                break;
            case 3:
                team2++;
                break;
        }
    }

    public static void playRound() {
        System.out.println("PLAYER Hand:");
        int index = 0;
        for (Card c : player) {
            System.out.print("[" + index + "] " + c.toString());
            index++;
        }

        System.out.println("\n AI 1 hand");
        displayCards(ai1);
        System.out.println("\n AI 2 hand");
        displayCards(ai2);
        System.out.println("\n AI 3 hand");
        displayCards(ai3);

        playerCard = getPlyerCard();

        center.add(player.get(playerCard));
        player.remove(playerCard);

        center.add(aiSelect(center.get(0), ai1));
        center.add(aiSelect(center.get(0), ai2));
        center.add(aiSelect(center.get(0), ai3));

        ai1.remove(aiSelect(center.get(0), ai1));
        ai2.remove(aiSelect(center.get(0), ai2));
        ai3.remove(aiSelect(center.get(0), ai3));

        System.out.println("Center row of cards ");
        displayCards(center);
        System.out.print("\n Winning Card \n");
        System.out.println(selectMax(center.get(0), center.get(1), center.get(2), center.get(3)).toString());
        System.out.print("\n Player who won round \n");
        int winner = getWinningCard(center);
        System.out.println("At position [" + getPlayerPosition(center.get(winner), center) + "]");
        trackScore(player, ai2, ai3, ai1, center, winner);
        center.clear();

    }

    public static int getPlayerPosition(Card playerCard, ArrayList<Card> center) {
        int index = 0;
        for (int i = 0; i < center.size(); i++) {
            if (playerCard == center.get(i)) {
                index = i;
            }
        }
        return index;
    }

    public static int getWinningCard(ArrayList<Card> center) {
        Card maxCard = selectMax(center.get(0), center.get(1), center.get(2), center.get(3));
        return center.indexOf(maxCard);
    }

    public static Card aiSelect(Card current, ArrayList<Card> aiHand) {
        Collections.sort(aiHand);
        // var max will track highest card in AIs hand
        Card max;
        // contains all posible cards of the same sui as the current card
        ArrayList<Card> followSuit = new ArrayList<>();
        if (!followSuit.isEmpty()) {
            for (Card c : aiHand) {
                if (c.suit == current.suit) {
                    followSuit.add(c);
                }
            }
            Collections.sort(aiHand);
            max = followSuit.get(followSuit.size() - 1);
            return max;
        } else {
            return aiHand.get(aiHand.size() - 1);
        }
    }

    public static void displayCards(ArrayList<Card> hand) {
        for (Card c : hand) {
            System.out.print(c.toString());
        }
    }

    public static void dealCards(Deck deck, ArrayList<Card> p1, ArrayList<Card> p2,
            ArrayList<Card> p3, ArrayList<Card> p4) {
        for (int i = 0; i < 13; i++) {
            p1.add(deck.dealCard());
            p2.add(deck.dealCard());
            p3.add(deck.dealCard());
            p4.add(deck.dealCard());
        }
    }

    public static Card selectMax(Card c1, Card c2, Card c3, Card c4) {
        // suit of card 1
        int firsSuit = c1.suit;
        if (c1.compareTo(firsSuit, c2) && c1.compareTo(firsSuit, c3)
                && c1.compareTo(firsSuit, c4)) {
            return c1;
        } else if (c2.compareTo(firsSuit, c1) && c2.compareTo(firsSuit, c3)
                && c2.compareTo(firsSuit, c4)) {
            return c2;
        } else if (c3.compareTo(firsSuit, c1) && c3.compareTo(firsSuit, c2)
                && c3.compareTo(firsSuit, c4)) {
            return c3;
        } else {
            return c4;
        }

    }

    public static void main(String[] args) {

        System.out.println("Contract Bridge - Console");
        System.out.println("Creating deck...");

        System.out.println("Dealing Cards...");

        dealCards(deck, player, ai1, ai2, ai3);
        while (!player.isEmpty() || !ai1.isEmpty()
                || !ai2.isEmpty() || !ai3.isEmpty()) {
            playRound();
        }
        stdin.close();
        System.exit(0);

    }
}