package com.windowsxp.bookstore.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_item", schema = "bookstore")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class OrderItem {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    @Column(name = "order_id")
    private Integer orderId;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "book_number")
    private Integer bookNum;
}