package com.windowsxp.bookhub.backend.dto.request;

import lombok.Getter;

@Getter
public class NewBookDTO {
    private String isbn;
    private String name;
    private String type;
    private String author;
    private Integer price;
    private String description;
    private Integer inventory;
    private String image;
}
