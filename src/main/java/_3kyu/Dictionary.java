package _3kyu;

public class Dictionary {

    private final String[] words;

    public Dictionary(final String[] words) {
        this.words = words;
    }

    public String findMostSimilar(final String search) {
        String mostSimilar = null;
        int mostSimilarCost = Integer.MAX_VALUE;
        for (final String word : words) {
            final int cost = distance(word, search);
            if (cost < mostSimilarCost) {
                mostSimilarCost = cost;
                mostSimilar = word;
            }
        }
        return mostSimilar;
    }

    // levenshtein, dynamic programming, from wikipedia, minus lowercasing
    public static int distance(final String a, final String b) {
        final int[] costs = new int[b.length() + 1];
        for (int j = 0; j < costs.length; j++) {
            costs[j] = j;
        }
        for (int i = 1; i <= a.length(); i++) {
            costs[0] = i;  // ?
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                final int cj = Math.min(
                        Math.min(costs[j], costs[j - 1]) + 1,
                        a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }

}