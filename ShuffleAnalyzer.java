import java.util.Arrays;

public class ShuffleAnalyzer {
  public static void analyze(Shuffler shuffler, int deckSize, int runs) {
    // How likely a card is likely to end up in this bucket
    double[] chanceBuckets = new double[deckSize];
    Deck d = new Deck(deckSize);
    for (int i = 0; i < runs; i++) {
      d.order();
      shuffler.shuffle(d);
      updateChanceBuckets(chanceBuckets, d);
    }

    analyze(chanceBuckets, runs);
  }

  private static void updateChanceBuckets(double[] chanceBuckets, Deck d) {
    for (int i = 0; i < chanceBuckets.length; i++) {
      int offset = (i + chanceBuckets.length - d.cards[i]) % chanceBuckets.length;
      chanceBuckets[offset]++;
    }
  }

  private static void analyze(double[] arr, int n) {
    double mean = Arrays.stream(arr).reduce(0, (total, element) -> total + element) / arr.length;
    double squareOfDifferences = Arrays.stream(arr).reduce(0, (total, element) -> total + Math.pow(element - mean, 2));
    double coefficientOfVariation = Math.sqrt(squareOfDifferences / arr.length) / mean;
    System.out.printf("Coefficient of variance: %.5f%n", coefficientOfVariation);
    // Print normalized values
    System.out.print("[");
    System.out.printf("%.4f", arr[0] / (n * arr.length));
    for (int i = 1; i < arr.length; i++) {
      System.out.print(", ");
      System.out.printf("%.4f", arr[i] / (n * arr.length));
    }
    System.out.println("]");
  }
}
