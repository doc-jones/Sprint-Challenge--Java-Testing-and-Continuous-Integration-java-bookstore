package com.lambdashool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdashool.bookstore.models.Author;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@ApiModel(value = "book", description = "Defines the book entity, it's relationships to other entities and book props")
@Entity
@Table(name = "book")
public class Book
{

    @ApiModelProperty(name = "bookid", value = "Primary key for book", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonView(View.booksOnly.class)
    private long bookid;

    @ApiModelProperty(name = "bookname", value = "Name of book", required = false, example = "The name or title of a book")
//    @JsonView(View.booksOnly.class)
    private String bookname;


    @ManyToMany(mappedBy = "books")
    @JsonIgnoreProperties("books")
    private List<Author> Authors = new ArrayList<>();

    public Book()
    {
    }

    public Book(String bookname)
    {
        this.bookname = bookname;
    }
    
    public long getbookid()
    {
        return bookid;
    }

    public void setbookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getbookname()
    {
        return bookname;
    }

    public void setbookname(String bookname)
    {
        this.bookname = bookname;
    }


    public List<Author> getAuthors()
    {
        return Authors;
    }

    public void setAuthors(List<Author> Authors)
    {
        this.Authors = Authors;
    }
}