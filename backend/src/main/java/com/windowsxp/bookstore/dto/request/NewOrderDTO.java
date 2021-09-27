package com.windowsxp.bookstore.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class NewOrderDTO {

    @Getter
    public static class OrderItem{
        Integer bookId;
        Integer bookNumber;
    }

    private Integer userId;
    private List<OrderItem> orderItemList;

    public String toString() {
        StringBuilder ret = new StringBuilder(String.format("user id: %d\t", userId));
        for(OrderItem orderItem: orderItemList) {
            ret.append(String.format("book id: %d\tbook number: %d", orderItem.getBookId(), orderItem.getBookNumber()));
        }
        return ret.toString();
    }
}
