package com.example.demo.service;

import com.example.demo.dao.AuthorRepository;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.utils.ConvertToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<AuthorDto> getAuthors() {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        return ConvertToDto.convertToAuthorDtos(authors);
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {

        Author author = new Author();
        author.setName(authorDto.getName());

        List<Book> books = authorDto.getBooks().stream().map((e) -> {
            Book book = new Book();
            book.setTitle(e.getTitle());
            return book;
        }).collect(Collectors.toList());

        author.setBooks(books);

        Author saved = authorRepository.save(author);
        return ConvertToDto.convertToAuthorDto(saved);
    }

    @Override
    public AuthorDto addBooks(long authorId, List<BookDto> bookDtos) {
        Author author = authorRepository.findById(authorId).orElse(null);
        if(author == null) {
            throw new IllegalArgumentException("Unable to find author");
        }
        author.setBooks(bookDtos.stream().map(this::mapToBook).collect(Collectors.toList()));
        Author updated = authorRepository.save(author);
        return ConvertToDto.convertToAuthorDto(updated);

    }

    private Book mapToBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        return book;
    }

    @Override
    public AuthorDto getAuthor(long authorId) {
        Author author = authorRepository.findById(authorId).orElse(null);
        if(author == null) {
            throw new IllegalArgumentException("Unable to find author");
        }
        return ConvertToDto.convertToAuthorDto(author);
    }

}
