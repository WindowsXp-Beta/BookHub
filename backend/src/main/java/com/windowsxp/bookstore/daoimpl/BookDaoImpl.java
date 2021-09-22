package com.windowsxp.bookstore.daoimpl;

import com.windowsxp.bookstore.dao.BookDao;
import com.windowsxp.bookstore.dto.response.PageDTO;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookDaoImpl implements BookDao{

    private final BookRepository bookRepository;

    @Override
    public Optional<Book> findBookById(Integer id){
        return bookRepository.findById(id);
    }

    @Override
    public PageDTO<Book> getBooks(Pageable pageable) {
        return new PageDTO<>(bookRepository.getAllByInventoryGreaterThan(0, pageable).getContent(), bookRepository.count());
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        Book book = bookRepository.getById(id);
        book.setInventory(-1);
        bookRepository.save(book);
    }
}