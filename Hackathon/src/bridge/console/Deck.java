 package bridge.console;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Luis
 */
public class Deck {
        ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        for (int s = 0; s < 4; s++) {
            for (int r = 2; r < 15; r++) {
                deck.add(new Card(r, s));
            }
        }
        Collections.shuffle(deck);
//        System.out.print("Deck:");
//        deck.stream().forEach((element) -> {
//            System.out.println(element.toString());
//        });
    }

    public void addCard(Card card) {
        if (deck.size() < 52) {
            deck.add(card);
        }
    }

    public Card dealCard() {
        Card card = null;
        if (deck.size() > 0) {
            card = deck.remove(deck.size() - 1);
        }
        return card;
    }

}