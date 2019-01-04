package _4kyu;

/**
 * Created by kenny on 7/26/17.
 */
public class NextBiggestNumberFast {
    private static final int[] DNE = new int[0];

    public static long nextBiggerNumber(final long n) {
        int[] digits = toDigits(n);
        while (digits != DNE) {
            digits = nextPermutation(digits);
            final long next = toLong(digits);
            if (next > n) {
                return next;
            }
        }
        return -1;
    }

    private static int[] nextPermutation(final int[] digits) {
        int i = 0;
        // find a digit that is less than i so we can swap
        for (i = digits.length - 2; i >= 0; i--) {
            if (digits[i] < digits[i + 1]) {
                break; // preserve i
            }
            if (i == 0) { // we haven't found a digit to bubble up, we're done.
                return DNE;
            }
        }

        int j = digits.length - 1; // find first highest index where d[j] > d[i] where j > i
        while (digits[j] <= digits[i]) {
            j--;
        }

        swap(digits, i, j);

        // reverse digits after i and between j
        for (j = digits.length - 1; i + 1 < j; i++, j--) {
            swap(digits, i + 1, j);
        }

        return digits;
    }

    public static int[] toDigits(long n) {
        final int[] digits = new int[(int) Math.log10(n) + 1];
        for (int i = 0; i < digits.length; i++) {
            digits[digits.length - i - 1] = (int) (n % 10);
            n /= 10;
        }
        return digits;
    }

    public static long toLong(final int[] digits) {
        long n = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            n += digits[i] * (long) Math.pow(10, digits.length - i - 1);
        }
        return n;
    }

    private static void swap(final int[] a, final int i, final int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
