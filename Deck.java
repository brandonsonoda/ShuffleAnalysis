import java.util.Arrays;

public class Deck {
  final int[] cards;

  public Deck(int deckSize) {
    cards = new int[deckSize];
    order();
  }

  public void order() {
    for (int i = 0; i < cards.length; i++) {
      cards[i] = i;
    }
  }

  @Override
  public String toString() {
    return Arrays.toString(cards);
  }
}
