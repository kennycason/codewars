package _6kyu;

/**
 * Created by kenny on 7/13/17.
 */
public class ASum {

    // V = n^3 + (n-1)^3 + (n-2)^3 + ... + 1^3
    // 9   =                   2^3 + 1^3 =                 8 + 1
    // 36  =             3^3 + 2^3 + 1^3 =            27 + 8 + 1
    // 100 =       4^3 + 3^3 + 2^3 + 1^3 =       64 + 27 + 8 + 1
    // 225 = 5^3 + 4^3 + 3^3 + 2^3 + 1^3 = 125 + 64 + 27 + 8 + 1
    // ...
    public static long findNb(final long m) {
        long n = 1L;
        long sum = 0L;
        while (sum < m) {
            sum += n * n * n;
            if (sum == m) {
                return n;
            }

            n++;
        }
        return -1L;
    }
}