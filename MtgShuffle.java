public class MtgShuffle {
  static void shuffle(Deck d, int times) {
    int[] buffer = new int[d.cards.length];

    for (int iteration = 0; iteration < times; iteration++) {
      singleShuffle(d, buffer);
    }
  }

  static void singleShuffle(Deck d, int[] buffer) {
    // Through experimentation:
    // 1. Split cards in half: A, B (roughly half +/- 2)
    // 2. Start with bottom x cards from A (roughly 6 +/- 2)
    // 3. Internleave 1 to 2 cards, starting with B (12x 1 cards : 1x 2 cards)
    // 4. Insert the rest of B
    int deckSize = d.cards.length;
    int fill = 0;
    int bIndex = 0;

    // Step 1
    int aSize = deckSize / 2 + plusOrMinus(deckSize / 20);
    int aStart = deckSize - aSize;
    int aIndex = aStart;

    // Step 2
    int aPeel = (int) (deckSize / 6.5) + plusOrMinus((int) (deckSize / 20.0));
    for (int i = 0; i < aPeel; i++) {
      buffer[fill++] = d.cards[aIndex++];
    }

    // Step 3
    while (fill < buffer.length && aIndex < buffer.length && bIndex < aStart) {
      int peel;
      // Insert cards from B
      peel = (13 * Math.random() > 1) ? 1 : 2;
      for (int i = 0; i < peel && bIndex < aStart; i++) {
        buffer[fill++] = d.cards[bIndex++];
      }

      // Insert cards from A
      peel = (13 * Math.random() > 1) ? 1 : 2;
      for (int i = 0; i < peel && aIndex < buffer.length; i++) {
        buffer[fill++] = d.cards[aIndex++];
      }
    }

    // STep 4
    int bPeel = buffer.length - fill;
    while (fill < buffer.length) {
      buffer[fill++] = d.cards[bIndex++];
    }

    // Copy over buffer
    for (int i = 0; i < d.cards.length; i++) {
      d.cards[i] = buffer[i];
    }

    // System.out.printf("Mtg Shuffle: aSize(%s), aPeel(%s), bPeel(%s)%n", aSize, aPeel, bPeel);
  }

  static int plusOrMinus(int x) {
    boolean negative = Math.random() < 0.5;
    int value = (int)(Math.random() * (x + 1));
    return negative ? -1 * value : value;
  }
}
