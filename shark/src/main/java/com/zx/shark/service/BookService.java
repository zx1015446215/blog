package com.zx.shark.service;

import com.zx.shark.model.Book;
import com.zx.shark.model.UserBook;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Book> findAllBook(String username);
    void removeBook(List<Integer> ids);
    void borrowBook(UserBook userBook,int book_id);
    void cancelBorrow(UserBook userBook,int book_id);
    List<Book> selectNeedBook(Book book,String username);
    int countBooks();

}
