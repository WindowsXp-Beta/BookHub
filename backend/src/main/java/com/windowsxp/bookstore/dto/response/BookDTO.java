package com.windowsxp.bookstore.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDTO {
    private Integer id;
    private String name;
    private Integer price;
    private String image;
}
