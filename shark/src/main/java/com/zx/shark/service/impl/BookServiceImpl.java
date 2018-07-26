package com.zx.shark.service.impl;

import com.zx.shark.mapper.BookMapper;
import com.zx.shark.model.Book;
import com.zx.shark.model.UserBook;
import com.zx.shark.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookMapper bookMapper;

    /**
     * 获取所以书籍和对应用户借阅信息
     * @param username
     * @return
     */
    @Transactional
    @Override
    public List<Book> findAllBook(String username) {
        List<Book> books = bookMapper.selectAllBooks();
        List<Integer> listid =bookMapper.selectUserBooks(username);
        for(Book book:books){
            if(listid.contains(book.getId())){
                book.setFlag(true);
            }
        }
        return books;
    }

    @Override
    public void removeBook(List<Integer> ids) {
        bookMapper.deleteBook(ids);
    }

    /**
     * 借书的操作
     * @param userBook
     * @param book_id
     */
    @Transactional
    @Override
    public void borrowBook(UserBook userBook, int book_id) {
        bookMapper.insertUserBook(userBook);
        bookMapper.updateBookRemain(book_id);
    }

    /**
     * 取消借阅书籍
     * @param userBook
     * @param book_id
     */
    @Transactional
    @Override
    public void cancelBorrow(UserBook userBook, int book_id) {
        bookMapper.rebackBookRemain(book_id);
        bookMapper.deleteUserBook(userBook);
    }

    /**
     * 条件查询书籍
     * @param book
     * @param username
     * @return
     */
    @Transactional
    @Override
    public List<Book> selectNeedBook(Book book, String username) {
        List<Book> books = bookMapper.selectNeedBook(book);
        List<Integer> listid = bookMapper.selectUserBooks(username);
        for(Book book1:books){
            if(listid.contains(book1.getId())){
                book1.setFlag(true);
            }
        }
        return books;
    }

    @Override
    public int countBooks() {
        return bookMapper.countBooks();
    }

}
