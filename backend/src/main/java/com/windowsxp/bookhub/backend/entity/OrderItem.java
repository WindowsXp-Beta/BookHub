package com.windowsxp.bookhub.backend.entity;


import com.windowsxp.bookhub.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "MEDIUMINT")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(nullable = false, columnDefinition = "MEDIUMINT")
    private Integer bookNumber;
}