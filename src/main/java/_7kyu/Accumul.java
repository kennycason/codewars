package _7kyu;

public class Accumul {
    
    public static String accum(final String s) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            multiply(s.charAt(i), i + 1, stringBuilder);
            if (i < s.length() - 1) {
                stringBuilder.append("-");
            }
        }
        return stringBuilder.toString();
    }
    
    public static void multiply(final char c, final int times, final StringBuilder stringBuilder) {
        for (int i = 0; i < times; i++) {
            if (i == 0) {
                stringBuilder.append(Character.toUpperCase(c));
            } else {
                stringBuilder.append(Character.toLowerCase(c));
            }
        }
    }
}