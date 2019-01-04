package _6kyu;

/**
 * Created by kenny on 7/17/17.
 */
public class MultiplesOf3and5 {
    public int solution(final int number) {
        int sum = 0;
        for (int i = 3; i < number; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
