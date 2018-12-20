package com.example.demo.controller;


import com.example.demo.dto.BookDto;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookDto> getBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/author/{authorId}", method = RequestMethod.GET)
    public List<BookDto> getBooksByAuthor(@PathVariable(value = "authorId") long authorId) {
        return bookService.getBooksByAuthor(authorId);
    }

}
