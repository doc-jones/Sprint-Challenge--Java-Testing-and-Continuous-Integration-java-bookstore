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

    @ApiModelProperty(name = "booktitle", value = "Title of book", required = false, example = "The name or title of a book")
//    @JsonView(View.booksOnly.class)
    private String booktitle;

    @ApiModelProperty(name = "bookISBN", value = "ISBN of book", required = false, example = "The ISBN of a book")
//    @JsonView(View.booksOnly.class)
    private String bookISBN;

    @ApiModelProperty(name = "bookvopy", value = "Copyright year of book", required = false, example = "2019")
//    @JsonView(View.booksOnly.class)
    private int bookcopy;


    @ManyToMany(mappedBy = "books")
    @JsonIgnoreProperties("books")
    private List<Author> Authors = new ArrayList<>();

    public Book()
    {
    }

    public Book(String bookname)
    {
        this.booktitle = bookname;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public int getBookcopy() {
        return bookcopy;
    }

    public void setBookcopy(int bookcopy) {
        this.bookcopy = bookcopy;
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
        return booktitle;
    }

    public void setbookname(String bookname)
    {
        this.booktitle = bookname;
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