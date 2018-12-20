package com.example.demo.service;

import com.example.demo.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto saveBookInLibrary(long libraryId, BookDto bookDto);

    List<BookDto> getBooksFromLibrary(long libraryId);

    List<BookDto> getAllBooks();

    List<BookDto> getBooksByAuthor(long authorId);
}
