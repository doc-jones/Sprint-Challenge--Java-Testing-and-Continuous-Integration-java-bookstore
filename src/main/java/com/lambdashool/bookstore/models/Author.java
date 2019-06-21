package com.lambdashool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "author", description = "Defines the author entity, it's relationships to other entities and author props")
@Entity
@Table(name = "author")
public class Author
{
    @ApiModelProperty(name = "authorid", value = "Primary key for Author", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    @ApiModelProperty(name = "authorname", value = "Author Name", required = true, example = "A person's name who is an author")
    private String authorname;

    @ManyToMany
    @JoinTable(name = "authorbooks",
               joinColumns = {@JoinColumn(name = "authorid")},
               inverseJoinColumns = {@JoinColumn(name = "bookid")})
    @JsonIgnoreProperties("authors")
    private List<Book> books = new ArrayList<>();

    public Author()
    {
    }

    public Author(String studname)
    {
        this.authorname = studname;
    }

    public long getStudid()
    {
        return authorid;
    }

    public void setAuthorid(long studid)
    {
        this.authorid = studid;
    }

    public String getAuthorname()
    {
        return authorname;
    }

    public void setAuthorname(String authorname)
    {
        this.authorname = authorname;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> courses)
    {
        this.books = books;
    }
}
