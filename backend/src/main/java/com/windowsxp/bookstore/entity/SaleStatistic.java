package com.windowsxp.bookstore.entity;

import lombok.Data;

@Data
public class SaleStatistic {
    private Book book;
    private Integer bookNumber;
    private Integer Sum;
}
