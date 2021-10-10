package com.windowsxp.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HomeDTO {
    List<BookDTO> content;
    Long size;
    Integer pageView;
}
