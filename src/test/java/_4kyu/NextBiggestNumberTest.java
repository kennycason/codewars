package _4kyu;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NextBiggestNumberTest {
    @Test
    public void basicTests() {
         assertEquals(21, NextBiggestNumberFast.nextBiggerNumber(12));
         assertEquals(531, NextBiggestNumberFast.nextBiggerNumber(513));
         assertEquals(2071, NextBiggestNumberFast.nextBiggerNumber(2017));
         assertEquals(441, NextBiggestNumberFast.nextBiggerNumber(414));
         assertEquals(414, NextBiggestNumberFast.nextBiggerNumber(144));
    }

    @Test
    public void misc() {
//        System.out.println(
//                NextBiggestNumberFast.toLong(
//                        NextBiggestNumberFast.toDigits(10170L)));
        NextBiggestNumberFast.nextBiggerNumber(2017);
    }
}