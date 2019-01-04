package _5kyu;

class ToCamelCase {

    static String toCamelCase(final String s) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (c == '_' || c == '-') {
                stringBuilder.append(Character.toUpperCase(s.charAt(i + 1)));
                i++; // skip
            } else {
                stringBuilder.append(c);
            }
        }

        return stringBuilder.toString();
    }

}