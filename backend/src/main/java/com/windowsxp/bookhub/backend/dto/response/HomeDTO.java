package com.windowsxp.bookhub.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HomeDTO {
    List<BookDTO> content;
    Long size;
    Integer pageView;
}
