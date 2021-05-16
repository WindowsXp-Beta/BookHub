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

    public void setId(Integer id) {
        this.id = id;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return this.id;
    }
    public String getIsbn() {
        return this.isbn;
    }
    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    public String getAuthor() {
        return this.author;
    }
    public Integer getPrice() {
        return this.price;
    }
    public String getDescription() {
        return this.description;
    }
    public Integer setInventory() {
        return this.inventory;
    }
    public String getImage() {
        return this.image;
    }
}
