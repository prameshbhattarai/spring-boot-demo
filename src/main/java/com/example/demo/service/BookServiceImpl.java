package com.example.demo.service;

import com.example.demo.dao.AuthorRepository;
import com.example.demo.dao.BookRepository;
import com.example.demo.dao.LibraryRepository;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.LibraryDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.utils.ConvertToDto;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Library getLibraryById(long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElse(null);
        if(library == null) {
            throw new IllegalArgumentException("Unable to find library");
        }
        return library;
    }

    @Override
    public BookDto saveBookInLibrary(long libraryId, BookDto bookDto) {
        Library library = getLibraryById(libraryId);

        Book book = bookRepository.findById(bookDto.getId()).orElse(null);
        if(book == null) {
            throw new IllegalArgumentException("Unable to find book");
        }

        book.setLibrary(library);

        Book saved = bookRepository.save(book);
        return ConvertToDto.convertToBookDto(saved);
    }

    @Override
    public List<BookDto> getBooksFromLibrary(long libraryId) {
        Library library = getLibraryById(libraryId);

        List<Book> books = bookRepository.findAllBooksByLibraryId(library.getId());

        return ConvertToDto.convertToBookDtos(books);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        return ConvertToDto.convertToBookDtos(books);
    }

    @Override
    public List<BookDto> getBooksByAuthor(long authorId) {
        Author author = authorRepository.findById(authorId).orElse(null);
        if(author == null) {
            throw new IllegalArgumentException("Unable to find books");
        }
        return ConvertToDto.convertToBookDtos(author.getBooks());
    }

}
