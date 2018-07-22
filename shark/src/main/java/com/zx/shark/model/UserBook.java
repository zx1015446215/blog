package com.zx.shark.model;

public class UserBook {
    private int user_id;
    private int book_id;

    public UserBook(int user_id, int book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
