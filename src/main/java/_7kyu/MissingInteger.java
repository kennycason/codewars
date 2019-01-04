package _7kyu;

import java.util.stream.IntStream;

/**
 * Created by kenny on 7/24/17.
 */
public class MissingInteger {
    public static int findDeletedNumber(int[] arr, int[] mixedArr) {
        return IntStream.of(arr).sum() - IntStream.of(mixedArr).sum();
    }
}
