package com.windowsxp.bookhub.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "MEDIUMINT")
    private Integer id;

    @Column(nullable = false, columnDefinition = "MEDIUMINT")
    private Integer userId;

    @Column(nullable = false)
    private Timestamp time;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItem;
}