package _7kyu;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kenny on 7/26/17.
 */
public class EvenOddTest {

    @Test
    public void exampleTest() {
        assertEquals("odd", EvenOdd.oddOrEven(new int[] {2, 5, 34, 6}));
        assertEquals("even", EvenOdd.oddOrEven(new int[] {2, 6}));
        assertEquals("even", EvenOdd.oddOrEven(new int[] {-2, -6}));
        assertEquals("even", EvenOdd.oddOrEven(new int[] {-2}));
        assertEquals("even", EvenOdd.oddOrEven(new int[] {0,}));
        assertEquals("odd", EvenOdd.oddOrEven(new int[] {3}));
        assertEquals("odd", EvenOdd.oddOrEven(new int[] {5, 2}));

    }

}
