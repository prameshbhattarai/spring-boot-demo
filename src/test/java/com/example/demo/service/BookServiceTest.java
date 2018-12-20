package com.example.demo.service;

import com.example.demo.dao.BookRepository;
import com.example.demo.dao.LibraryRepository;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.entity.Address;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private LibraryRepository libraryRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveBookInLibrary() {
        Optional<Library> optionalLibrary = Optional.of(createLibraryEntity());
        Optional<Book> optionalBook = Optional.of(createBookEntity());

        Mockito.when(libraryRepository.findById(1L)).thenReturn(optionalLibrary);
        Mockito.when(bookRepository.findById(1L)).thenReturn(optionalBook);
        Mockito.when(bookRepository.save(createBookEntity())).thenReturn(createBookEntity());

        BookDto savedBookInLibrary = bookService.saveBookInLibrary(1L, createBookDto());
        Assert.assertNotNull(savedBookInLibrary);
    }

    @Test
    public void getBooksFromLibrary() {
        Optional<Library> optionalLibrary = Optional.of(createLibraryEntity());

        Mockito.when(libraryRepository.findById(1L)).thenReturn(optionalLibrary);
        Mockito.when(bookRepository.findAllBooksByLibraryId(1L)).thenReturn(Arrays.asList(createBookEntity()));

        List<BookDto> booksFromLibrary = bookService.getBooksFromLibrary(1L);
        Assert.assertNotNull(booksFromLibrary);
    }

    @Test
    public void getAllBooks() {

        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(createBookEntity()));
        List<BookDto> booksFromLibrary = bookService.getAllBooks();
        Assert.assertNotNull(booksFromLibrary);

    }

    private Address createAddressEntity() {
        Address address = new Address();
        address.setId(1L);
        address.setLocation("Mock Address");
        return address;
    }

    private Library createLibraryEntity() {
        Library library = new Library();
        library.setId(1L);
        library.setName("Mock Library");
        library.setAddress(createAddressEntity());
        return library;
    }

    private Author createAuthorEntity() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Mock Book");

        Author author = new Author();
        author.setId(1L);
        author.setName("Mock Author");
        author.setBooks(Arrays.asList(book));
        return author;
    }

    private Book createBookEntity() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Mock Book");
        book.setAuthors(Arrays.asList(createAuthorEntity()));
        book.setLibrary(createLibraryEntity());
        return book;
    }

    private AuthorDto createAuthorDto() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1L);
        authorDto.setName("Mock Author");
        return authorDto;
    }

    private BookDto createBookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Mock Book");
        bookDto.setAuthors(Arrays.asList(createAuthorDto()));
        return bookDto;
    }
}
