public class Main {
  public static void main(String[] args) {
    ShuffleAnalyzer.analyze(Main::computerShuffle, 8, 9999999);
  }

  private static void computerShuffle(Deck d) {
    for (int i = 0; i < d.cards.length; i++) {
      int swapLocation = i + (int)(Math.random() * (d.cards.length - i));
      int buffer = d.cards[i];
      d.cards[i] = d.cards[swapLocation];
      d.cards[swapLocation] = buffer;
    }
  }
}
