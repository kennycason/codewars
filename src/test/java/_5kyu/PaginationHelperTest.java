package _5kyu;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by kenny on 7/15/17.
 */
public class PaginationHelperTest {

    @Test
    public void basicTest() {
        final List<Integer> items = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final PaginationHelper<Integer> paginationHelper = new PaginationHelper<>(items, 3);

        assertEquals(10, paginationHelper.itemCount());
        assertEquals(4, paginationHelper.pageCount());

        assertEquals(3, paginationHelper.pageItemCount(0));
        assertEquals(3, paginationHelper.pageItemCount(1));
        assertEquals(3, paginationHelper.pageItemCount(2));
        assertEquals(1, paginationHelper.pageItemCount(3));
        assertEquals(-1, paginationHelper.pageItemCount(4));

        assertEquals(0, paginationHelper.pageIndex(0));
        assertEquals(0, paginationHelper.pageIndex(1));
        assertEquals(0, paginationHelper.pageIndex(2));
        assertEquals(1, paginationHelper.pageIndex(3));
        assertEquals(1, paginationHelper.pageIndex(4));
        assertEquals(1, paginationHelper.pageIndex(5));
        assertEquals(2, paginationHelper.pageIndex(6));
        assertEquals(2, paginationHelper.pageIndex(7));
        assertEquals(2, paginationHelper.pageIndex(8));
        assertEquals(3, paginationHelper.pageIndex(9));
        assertEquals(-1, paginationHelper.pageIndex(10));
    }
}
