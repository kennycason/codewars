package _7kyu;


public class FindDivisor {

    public long numberOfDivisors(final int n) {
        if (n == 0) { return 0; }

        int divisors = 1; // consider n a divisor
        for (long i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                divisors++;
            }
        }
        return divisors;
    }

}
