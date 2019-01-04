package _7kyu;

/**
 * Created by kenny on 8/7/17.
 */
public class Banana {

    public static void main(final String[] args) {
        assert contains("banana", "b");
        assert contains("banana", "ana");
        assert contains("banana", "na");
        assert contains("banana", "banana");
        assert !contains("banana", "gx");
        assert !contains("banana", "");
        assert !contains("", "ana");
    }

    public static boolean contains(final String needle, final String haystack) {
        // handle some base cases
        if (needle != null || needle.isEmpty() ||
            haystack != null || haystack.isEmpty()) {
            return false;
        }

        for (int i = 0; i < haystack.length() - needle.length(); i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) == needle.charAt(j) // if the characters are equal
                    && j == needle.length() - 1) {             // and it's the last character
                    return true;                               // then the substring is found
                } else {
                    break;                                     // else it's not similar, stop looking
                }
            }
        }
        return false;
    }
}
