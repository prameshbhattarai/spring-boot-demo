package com.example.demo.service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAuthors();

    AuthorDto createAuthor(AuthorDto authorDto);

    AuthorDto addBooks(long authorId, List<BookDto> bookDtos);

    AuthorDto getAuthor(long authorId);
}
