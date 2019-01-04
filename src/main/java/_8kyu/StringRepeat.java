package _8kyu;

/**
 * Created by kenny on 7/16/17.
 */
public class StringRepeat {

    public static String repeatStr(final int n, final String string) {
        final StringBuilder repeated = new StringBuilder();
        for (int i = 0; i < n; i++) {
            repeated.append(string);
        }
        return repeated.toString();
    }

}
