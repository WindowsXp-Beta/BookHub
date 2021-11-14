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
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "MEDIUMINT")
    private Integer userId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(nullable = false, columnDefinition = "MEDIUMINT")
    private Integer bookNumber;
}