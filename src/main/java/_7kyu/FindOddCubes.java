import java.util.stream.IntStream;

public class FindOddCubes {

    public static int cubeOdd(int[] ns) {
        return IntStream.of(ns)
                        .map(n -> (Math.abs(n) % 2 == 1) ? n * n * n : 0)
                        .sum();
    }

}