public class Main {
  public static void main(String[] args) {
    ShuffleAnalyzer.analyze(deck -> MtgShuffle.shuffle(deck, 9), 40, 999999);
  }

  private static void computerShuffle(Deck d) {
    for (int i = 0; i < d.cards.length; i++) {
      int swapLocation = i + (int)(Math.random() * (d.cards.length - i));
      int buffer = d.cards[i];
      d.cards[i] = d.cards[swapLocation];
      d.cards[swapLocation] = buffer;
    }
  }

  private static void riffleShuffle(Deck d, int times) {
    int[] buffer = new int[d.cards.length];
    // Assume perfect splits
    for (int iteration = 0; iteration < times; iteration++) {
      int fill = 0;
      int headSplit = 0;
      int tailSplit = buffer.length / 2;

      while (fill < buffer.length) {
        int peel;
        peel = 1 + (int) (3 * Math.random());
        // Half the time start with second half (i.e. peel 0 off the first half)
        if (fill == 0 && Math.random() < 0.5) {
          peel = 0;
        }
        for (int i = 0; i < peel && headSplit < buffer.length / 2; i++) {
          buffer[fill++] = d.cards[headSplit++];
        }
        peel = 1 + (int) (3 * Math.random());
        for (int i = 0; i < peel && tailSplit < buffer.length; i++) {
          buffer[fill++] = d.cards[tailSplit++];
        }
      }

      // Copy over buffer
      for (int i = 0; i < d.cards.length; i++) {
        d.cards[i] = buffer[i];
      }
    }
  }
}
