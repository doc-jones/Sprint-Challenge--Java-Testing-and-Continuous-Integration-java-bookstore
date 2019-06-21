package com.lambdashool.bookstore.repos;

import com.lambdashool.bookstore.models.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long>
{
    ArrayList<Book> findBooksByBooktitleEquals(String name);

    @Modifying
    @Query(value = "DELETE FROM authorsbooks WHERE bookid = :bookid", nativeQuery = true)
    void deleteCourseFromAtuthorbooks(long courseid);

}