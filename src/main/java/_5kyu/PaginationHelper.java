package _5kyu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenny on 7/15/17.
 */
public class PaginationHelper<I> {

    private final List<Page<I>> pages;

    private final int itemsPerPage;

    private final int itemCount;

    private static class Page<I> {
        private final List<I> items = new ArrayList<>();
    }

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(final List<I> items, final int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
        this.itemCount = items.size();
        this.pages = buildPages(items, itemsPerPage);
    }

    private List<Page<I>> buildPages(final List<I> items, final int itemsPerPage) {
        final List<Page<I>> pages = new ArrayList<>();
        for (int i = 0; i < items.size(); i += itemsPerPage) {
            final Page<I> page = new Page<>();
            page.items.addAll(
                    items.subList(i,
                                  Math.min(i + itemsPerPage, items.size())));
            pages.add(page);
        }

        return pages;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return itemCount;
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return pages.size();
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(final int pageIndex) {
        if (pageIndex < 0 || pageIndex >= pageCount()) {
            return -1;
        }
        return pages.get(pageIndex)
                    .items
                    .size();
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(final int itemIndex) {
        if (itemIndex < 0 || itemIndex >= itemCount) {
            return -1;
        }
        return itemIndex / itemsPerPage;
    }

}
