package _7kyu;

/**
 * Created by kenny on 7/26/17.
 */
public class EvenOdd {
    public static String oddOrEven(final int[] ns) {
        int flag = 0b0;
        for (final int n : ns) {
            flag ^= n;
        }
        return ((flag & 1) == 0) ? "even" : "odd";
    }
}
