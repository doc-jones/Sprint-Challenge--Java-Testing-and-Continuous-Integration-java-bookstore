package com.lambdashool.bookstore.services;


import com.lambdashool.bookstore.models.Book;
import com.lambdashool.bookstore.view.CountAuthorsInBooks;

import java.util.ArrayList;

public interface BookService
{
    ArrayList<Book> findAll();

    ArrayList<CountAuthorsInBooks> getCountStudentsInCourse();

    void delete(long id);
}