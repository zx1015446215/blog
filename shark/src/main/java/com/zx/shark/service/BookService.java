package com.zx.shark.service;

import com.zx.shark.model.Book;
import com.zx.shark.model.UserBook;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Book>  listAllBooks();
    void saveBook(Book book);
    void removeBook(List<Integer> ids);
    void saveUserBook(UserBook userBook);
    void updateBookRemain(int book_id);
    void RebackBookRemain(int book_id);
    void deleteUserBook(UserBook userBook);
    List<Book> selectNeedBook(Book book);
    int countBooks();
    List<Integer> selectUserBooks(String username);

}
