package vn.hcmute.web24162095.model;

import java.util.List;

public class Page_24162095<T> {
    private final List<T> items;
    private final int page;
    private final int size;
    private final int totalItems;
    public Page_24162095(List<T> items, int page, int size, int totalItems) {
        this.items = items; this.page = page; this.size = size; this.totalItems = totalItems;
    }
    public List<T> getItems() { return items; }
    public int getPage() { return page; }
    public int getSize() { return size; }
    public int getTotalItems() { return totalItems; }
    public int getTotalPages() { return Math.max(1, (int) Math.ceil(totalItems / (double) size)); }
    public boolean isHasPrevious() { return page > 1; }
    public boolean isHasNext() { return page < getTotalPages(); }
}
