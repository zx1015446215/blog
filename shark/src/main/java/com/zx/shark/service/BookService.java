package com.zx.shark.service;

import com.zx.shark.model.Book;
import java.util.List;

public interface BookService {
    List<Book> findAllBook();
    void removeBook(List<Integer> ids);
    void borrowBook(int book_id);
    void cancelBorrow(int book_id);
    List<Book> selectNeedBook(Book book);
    int countBooks();

}
