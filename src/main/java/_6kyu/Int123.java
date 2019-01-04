package _6kyu;

/**
 * Created by kenny on 7/17/17.
 */
public class Int123 {
    public static long int123(final int a) {
        if (a <= 123) {
            return 123 - a;
        }
        return Long.MAX_VALUE - Integer.MAX_VALUE - a;
    }
}
