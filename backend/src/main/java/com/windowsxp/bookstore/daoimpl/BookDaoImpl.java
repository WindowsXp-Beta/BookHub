package com.windowsxp.bookstore.daoimpl;

import com.windowsxp.bookstore.dao.BookDao;
import com.windowsxp.bookstore.dto.response.BookDTO;
import com.windowsxp.bookstore.dto.response.PageDTO;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.repository.BookRepository;
import com.windowsxp.bookstore.utils.LogUtil;
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
public class BookDaoImpl implements BookDao{

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;


    @Override
    @Cacheable(value = "bookDetail") //the key is bookDetail::$id
    public Optional<Book> findBookById(Integer id){
        LogUtil.debug(String.format("get book %d from db", id));
        return bookRepository.findById(id);
    }

    @Override
    @Cacheable(value = "bookBrief", key = "'page::' + #pageable.pageNumber")
    public PageDTO<BookDTO> getBooks(Pageable pageable) {
        LogUtil.debug("get all books from db");
        return new PageDTO<>(bookRepository.getAllByInventoryGreaterThan(0, pageable).stream().map((book) -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList()), bookRepository.count());
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