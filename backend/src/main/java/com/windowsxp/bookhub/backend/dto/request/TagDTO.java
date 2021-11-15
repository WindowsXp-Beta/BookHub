package com.windowsxp.bookhub.backend.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class TagDTO {
    String content;
    List<Integer> bookIdList;
    List<Long> relationTagList;
}
