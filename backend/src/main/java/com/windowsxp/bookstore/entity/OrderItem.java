package com.windowsxp.bookstore.entity;

public class OrderItem {
    private Integer itemId;
    private Integer orderId;
    private Book book;
    private Integer bookNum;

    public OrderItem(Integer itemId, Integer orderId, Book book, Integer bookNum) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.book = book;
        this.bookNum = bookNum;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Book getBook() {
        return book;
    }

    public Integer getBookNum() {
        return bookNum;
    }
}
