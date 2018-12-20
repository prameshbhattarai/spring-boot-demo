package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.LibraryDto;
import com.example.demo.service.BookService;
import com.example.demo.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<LibraryDto> getLibraries() {
        return libraryService.getLibraries();
    }

    @RequestMapping(method = RequestMethod.POST)
    public LibraryDto createLibrary(@RequestBody() LibraryDto libraryDto) {
        return libraryService.createLibrary(libraryDto);
    }

    @RequestMapping(value = "/{libraryId}/book", method = RequestMethod.GET)
    public List<BookDto> getBooksFromLibrary(@PathVariable(value = "libraryId") String libraryId) {
        return bookService.getBooksFromLibrary(Long.parseLong(libraryId));
    }

    @RequestMapping(value = "/{libraryId}/book", method = RequestMethod.POST)
    public BookDto saveBookInLibrary(@PathVariable(value = "libraryId") String libraryId, @RequestBody BookDto bookDto) {
        return bookService.saveBookInLibrary(Long.parseLong(libraryId), bookDto);
    }
}
