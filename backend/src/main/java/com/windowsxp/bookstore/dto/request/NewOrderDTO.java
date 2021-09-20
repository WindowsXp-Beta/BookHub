package com.windowsxp.bookstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NewOrderDTO {

    @Getter
    public class OrderItem{
        Integer bookId;
        Integer bookNumber;
    }

    private Integer userId;
    private List<OrderItem> orderItemList;
}
