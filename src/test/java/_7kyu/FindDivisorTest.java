package _7kyu;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindDivisorTest {
    @Test
    public void testSomething() {
         assertEquals(0, new FindDivisor().numberOfDivisors(0));
         assertEquals(3, new FindDivisor().numberOfDivisors(4));
    }
}