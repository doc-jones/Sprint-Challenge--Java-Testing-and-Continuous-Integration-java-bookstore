package com.lambdashool.bookstore.services;


import com.lambdashool.bookstore.models.Book;

import java.util.ArrayList;

public interface BookService
{
    ArrayList<Book> findAll();

    void delete(long id);
}