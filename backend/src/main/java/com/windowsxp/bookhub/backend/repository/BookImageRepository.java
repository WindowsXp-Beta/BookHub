package com.windowsxp.bookhub.backend.repository;

import com.windowsxp.bookhub.book.entity.BookImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookImageRepository extends MongoRepository<BookImage, Integer> {
}
