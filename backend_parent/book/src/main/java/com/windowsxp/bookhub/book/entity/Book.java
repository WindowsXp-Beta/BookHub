package com.windowsxp.bookhub.book.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(columnDefinition = "MEDIUMINT")
    private Integer id;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, columnDefinition = "MEDIUMINT")
    private Integer price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, columnDefinition = "MEDIUMINT")
    private Integer inventory;

    @Transient
    private String bookImage;
}