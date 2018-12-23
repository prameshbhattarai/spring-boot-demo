package com.example.demo.service;

import com.example.demo.dao.AuthorRepository;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AuthorServiceTest {

    @InjectMocks
    AuthorServiceImpl authorService;

    @Mock
    AuthorRepository authorRepository;

    private Author author;

    private List<Author> authors;

    private AuthorDto authorDto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        author = createAuthorEntity();
        authorDto = createAuthorDto();
        authors = Arrays.asList(author);
    }

    @Test
    public void createAuthor() {
        Mockito.when(authorRepository.save(author)).thenReturn(author);
        AuthorDto responseAuthorDto = authorService.createAuthor(authorDto);
        Assert.assertNotNull(responseAuthorDto);
    }

    @Test
    public void getAuthor() {
        Optional<Author> authorOptional = Optional.of(author);
        Mockito.when(authorRepository.findById(1L)).thenReturn(authorOptional);
        AuthorDto authorDtos = authorService.getAuthor(1L);
        Assert.assertNotNull(authorDtos);
    }

    @Test
    public void getAuthors() {
        Mockito.when(authorRepository.findAll()).thenReturn(authors);
        List<AuthorDto> responseAuthorDtos = authorService.getAuthors();
        Assert.assertNotNull(responseAuthorDtos);
        Assert.assertEquals(responseAuthorDtos.size(), 1);
    }

    @Test
    public void addBooks() {
        Optional<Author> authorOptional = Optional.of(author);
        Mockito.when(authorRepository.findById(1L)).thenReturn(authorOptional);
        Mockito.when(authorRepository.save(author)).thenReturn(author);
        AuthorDto authorDto = authorService.addBooks(1L, Arrays.asList(createBookDto()));
        Assert.assertNotNull(authorDto);
    }

    private Book createBookEnity() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Mock Book ");
        return book;
    }

    private Author createAuthorEntity() {
        Author author = new Author();
        author.setName("Mock Author");
        author.setBooks(Arrays.asList(createBookEnity()));

        return author;
    }

    private BookDto createBookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Mock Book");
        return bookDto;
    }

    private AuthorDto createAuthorDto() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName("Mock Author");
        authorDto.setBooks(Arrays.asList(createBookDto()));
        return authorDto;
    }

}
