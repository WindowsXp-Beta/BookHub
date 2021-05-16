package com.windowsxp.bookstore.entity;

public class CartItem {
    private Integer itemId;
    private Integer userId;
    private Book book;
    private Integer bookNum;

    public CartItem(Integer itemId, Integer userId, Book book, Integer bookNum) {
        this.itemId = itemId;
        this.userId = userId;
        this.book = book;
        this.bookNum = bookNum;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }
    public Integer getItemId() {
        return this.itemId;
    }
    public Integer getUserId() {
        return this.userId;
    }
    public Book getBook() {
        return this.book;
    }
    public Integer getBookNum() {
        return this.bookNum;
    }
}
