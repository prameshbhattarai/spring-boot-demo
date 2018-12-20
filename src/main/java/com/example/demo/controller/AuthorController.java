package com.example.demo.controller;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "/{authorId}", method = RequestMethod.GET)
    public AuthorDto getAuthor(@PathVariable(value = "authorId") long authorId) {
        return authorService.getAuthor(authorId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AuthorDto> getAuthors() {
        return authorService.getAuthors();
    }

    @RequestMapping(method = RequestMethod.POST)
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.createAuthor(authorDto);
    }

    @RequestMapping(value = "/{authorId}/books", method = RequestMethod.POST)
    public AuthorDto addBooks(@PathVariable(value = "authorId") long authorId, @RequestBody List<BookDto> bookDtos) {
        return authorService.addBooks(authorId, bookDtos);
    }
}
