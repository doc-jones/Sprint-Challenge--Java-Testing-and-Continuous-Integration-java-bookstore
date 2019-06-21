package com.lambdashool.bookstore.controllers;


import com.lambdashool.bookstore.models.Book;
import com.lambdashool.bookstore.models.ErrorDetail;
import com.lambdashool.bookstore.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/books")
public class BookController
{
    @Autowired
    private BookService bookService;

    @ApiOperation(value = "return all Books", response = Book.class, responseContainer = "List")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve 0..N"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is asscending. " +
                            "Multiple sort criteria are supported.")})

    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllCourses()
    {
        ArrayList<Book> myBooks = bookService.findAll();
        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }





    @ApiOperation(value = "Deletes a book", notes = "The deleted bok id will be set in the location header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book Deleted Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error deleting book", response = ErrorDetail.class)})
    @DeleteMapping("/books/{bookid}")
    public ResponseEntity<?> deleteCourseById(@PathVariable long bookid)
    {
        bookService.delete(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
