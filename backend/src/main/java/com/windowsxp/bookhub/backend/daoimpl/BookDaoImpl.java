package com.windowsxp.bookhub.backend.daoimpl;

import com.windowsxp.bookhub.backend.dao.BookDao;
import com.windowsxp.bookhub.backend.dto.response.BookDTO;
import com.windowsxp.bookhub.backend.dto.response.PageDTO;
import com.windowsxp.bookhub.backend.exception.BookException;
import com.windowsxp.bookhub.backend.repository.BookImageRepository;
import com.windowsxp.bookhub.backend.repository.BookRepository;
import com.windowsxp.bookhub.backend.utils.LogUtil;
import com.windowsxp.bookhub.book.entity.Book;
import com.windowsxp.bookhub.book.entity.BookImage;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    private final BookRepository bookRepository;
    private final BookImageRepository bookImageRepository;
    private final ModelMapper modelMapper;


    @Override
    @Cacheable(value = "bookDetail") //the key is bookDetail::$id
    public Book findBookById(Integer id){
        LogUtil.debug(String.format("get book %d from db", id));
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isEmpty()) throw new BookException(BookException.BookExceptionType.BOOK_NOT_EXIST);
        else {
            Book book = bookOptional.get();
            if(book.getInventory() < 0) throw new BookException(BookException.BookExceptionType.BOOK_DELETED);
            else {
                Optional<BookImage> bookImageOptional = bookImageRepository.findById(id);
                bookImageOptional.ifPresent(bookImage -> book.setBookImage(bookImage.getImageBase64()));
                return book;
            }
        }
    }

    @Override
    @Cacheable(value = "bookBrief", key = "'page::' + #pageable.pageNumber")
    public PageDTO<BookDTO> getBooks(Pageable pageable) {
        LogUtil.debug("get all books from db");
        Page<Book> books = bookRepository.getAllByInventoryGreaterThan(0, pageable);
        return new PageDTO<>(books.stream().map((book -> {
            Optional<BookImage> bookImageOptional = bookImageRepository.findById(book.getId());
            bookImageOptional.ifPresent(bookImage -> book.setBookImage(bookImage.getImageBase64()));
            return modelMapper.map(book, BookDTO.class);
        })).collect(Collectors.toList()), books.getTotalElements());
    }

    @Override
    @Caching(evict = {@CacheEvict(value="bookBrief", allEntries = true) },
            put = {@CachePut(value = "bookDetail", key = "#book.id")})
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "bookDetail", key = "#id"),
            @CacheEvict(value="bookBrief", allEntries = true) })//since we don't know which page the book belongs to and deleting one book could change the whole structure
    public void deleteBook(Integer id) {
        Book book = bookRepository.getById(id);
        book.setInventory(-1);
        bookRepository.save(book);
    }
}