package _4kyu;

import java.util.*;

/**
 * Created by kenny on 7/26/17.
 */
public class NextBiggestNumber {

    public static long nextBiggerNumber(final long n) {
        final List<Long> permutations = new ArrayList<>();
        permute(toDigits(n), 0, permutations);
        Collections.sort(permutations);
        for (final long permuation : permutations) {
            if (permuation > n) {
                return permuation;
            }
        }
        return n - 1;
    }

    private static void permute(final int[] digits, int j, final List<Long> permutations) {
        for( int i = j; i < digits.length; i++){
            swap(digits, i, j); // swap
            permute(digits, j + 1, permutations);
            swap(digits, j, i); // undo after recur
        }
        if (j == digits.length - 1) {
            permutations.add(toLong(digits));
        }
    }

    private static int[] toDigits(long n) {
        final int[] digits = new int[(int) Math.log10(n) + 1];
        for (int i = 0; i < digits.length; i++) {
            digits[digits.length - i - 1] = (int) (n % 10);
            n /= 10;
        }
        return digits;
    }

    private static long toLong(final int[] digits) {
        long n = 0;
        for (int i = 0; i < digits.length; i++) {
            n += digits[i] * (long) Math.pow(10, i);
        }
        return n;
    }

    private static void swap(final int[] a, final int i, final int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
