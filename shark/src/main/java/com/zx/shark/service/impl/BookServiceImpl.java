package com.zx.shark.service.impl;

import com.zx.shark.mapper.BookMapper;
import com.zx.shark.model.Book;
import com.zx.shark.model.UserBook;
import com.zx.shark.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookMapper bookMapper;
    @Override
    public List<Book> listAllBooks() {
        return bookMapper.selectAllBooks();
    }

    @Override
    public void saveBook(Book book) {
        bookMapper.insertBook(book);
    }

    @Override
    public void removeBook(List<Integer> ids) {
        bookMapper.deleteBook(ids);
    }

    @Override
    public void saveUserBook(UserBook userBook) {
        bookMapper.insertUserBook(userBook);
    }

    @Override
    public void updateBookRemain(int book_id) {
        bookMapper.updateBookRemain(book_id);
    }

    @Override
    public void RebackBookRemain(int book_id) {
        bookMapper.rebackBookRemain(book_id);
    }

    @Override
    public void deleteUserBook(UserBook userBook) {
        bookMapper.deleteUserBook(userBook);
    }

    @Override
    public List<Book> selectNeedBook(Book book) {
        return bookMapper.selectNeedBook(book);
    }

    @Override
    public int countBooks() {
        return bookMapper.countBooks();
    }

    @Override
    public List<Integer> selectUserBooks(String username) {
        return bookMapper.selectUserBooks(username);
    }
}
