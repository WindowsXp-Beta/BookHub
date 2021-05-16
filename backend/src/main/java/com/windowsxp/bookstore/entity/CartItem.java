package com.windowsxp.bookstore.entity;

public class CartItem {
    private int userId;
    private Book book;
    private int bookNum;

    public CartItem(int userId, Book book, int bookNum) {
        this.userId = userId;
        this.book = book;
        this.bookNum = bookNum;
    }

}
