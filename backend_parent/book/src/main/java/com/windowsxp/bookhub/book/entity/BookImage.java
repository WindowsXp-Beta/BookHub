package com.windowsxp.bookhub.book.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;


@Document(collection = "BookImage")
@Setter
@Getter
@AllArgsConstructor
public class BookImage {

    @Id
    private Integer id;

    private String imageBase64;
}
