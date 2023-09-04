import java.util.Arrays;

public class ShuffleAnalyzer {
  public static void analyze(Shuffler shuffler, int deckSize, int runs) {
    // How likely a card is likely to end up in this bucket
    double[] chanceBuckets = new double[deckSize];

    for (int i = 0; i < runs; i++) {
      Deck d = new Deck(deckSize);
      shuffler.shuffle(d);
      updateChanceBuckets(chanceBuckets, d);
    }

    normalize(chanceBuckets, runs);
    System.out.println(Arrays.toString(chanceBuckets));
  }

  private static void updateChanceBuckets(double[] chanceBuckets, Deck d) {
    for (int i = 0; i < chanceBuckets.length; i++) {
      int offset = (i + chanceBuckets.length - d.cards[i]) % chanceBuckets.length;
      chanceBuckets[offset]++;
    }
  }

  private static void normalize(double[] arr, int n) {
    for (int i = 0; i < arr.length; i++) {
      arr[i] /= n * arr.length;
    }
  }
}
