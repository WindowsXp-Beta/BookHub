package com.windowsxp.bookstore.entity;

public class Book {
    private Integer id;

    private String isbn;
    private String name;
    private String type;
    private String author;
    private Integer price;
    private String description;
    private Integer inventory;
    private String image;

    public Book(Integer id, String isbn, String name, String type, String author, Integer price, String description, Integer inventory, String image) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.type = type;
        this.author = author;
        this.price = price;
        this.description = description;
        this.inventory = inventory;
        this.image = image;
    }
}
