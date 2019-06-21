package com.lambdashool.bookstore.controllers;


import com.lambdaschool.school.model.ErrorDetail;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.StudentService;
import com.lambdashool.bookstore.models.Author;
import com.lambdashool.bookstore.models.ErrorDetail;
import com.lambdashool.bookstore.services.AuthorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class StudentController
{
    @Autowired
    private AuthorService authorService;

    // Please note there is no way to add students to course yet!

    @ApiOperation(value = "return all Authors", response = Author.class, responseContainer = "List")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve 0..N"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is asscending. " +
                            "Multiple sort criteria are supported.")})


    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllAuthorss(@PageableDefault(page = 0, size = 5) Pageable pageable)
    {
        List<Author> myStudents = authorService.findAll(pageable);
        return new ResponseEntity<>(myStudents, HttpStatus.OK);
    }


    @ApiOperation(value = "Retrieves a student associated with the provided studid", response = Author.class)
    @ApiResponses(value =  {
            @ApiResponse(code = 201, message = "Student Found", response = Author.class),
            @ApiResponse(code = 404, message = "Student Not Found", response = ErrorDetail.class)})

    @GetMapping(value = "/author/{AuthorId}",
            produces = {"application/json"})
    public ResponseEntity<?> getStudentById(@ApiParam(value = "Author Id", required = true, example = "1")
                                            @PathVariable
                                                    Long StudentId)
    {
        Author r = authorService.findAuthorById(StudentId);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }


    @ApiOperation(value = "Creates a new Student", notes = "The newly created student id will be set in the location header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Student Created Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error creating student", response = ErrorDetail.class)})

    @PostMapping(value = "/data/books/author{id}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewStudent(@Valid
                                           @RequestBody
                                                   Author newStudent) throws URISyntaxException
    {
        newAuthor = authorService.save(newAtuhor);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Authorid}").buildAndExpand(newStudent.getAuthorid()).toUri();
        responseHeaders.setLocation(newStudentURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updates a student", notes = "The updated student id will be set in the location header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Student Updated Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error updating student", response = ErrorDetail.class)})

    @PutMapping(value = "/Student/{Studentid}")
    public ResponseEntity<?> updateStudent(
            @RequestBody
                    Student updateStudent,
            @PathVariable
                    long Studentid)
    {
        studentService.update(updateStudent, Studentid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes a student", notes = "The deleted student id will be set in the location header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Student Deleted Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error deleting student", response = ErrorDetail.class)})

    @DeleteMapping("/Student/{Studentid}")
    public ResponseEntity<?> deleteStudentById(
            @PathVariable
                    long Studentid)
    {
        studentService.delete(Studentid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}