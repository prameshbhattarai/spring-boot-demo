package com.example.demo.controller;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import com.example.demo.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTest {

    private static final String BASE_URL = "/books";

    private List<BookDto> bookDtos;

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private MockMvc mvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(bookController)
                .setControllerAdvice(new ExceptionHandlerController()).build();
        bookDtos = Arrays.asList(createBookDto());
    }

    @Test
    public void getBooks() throws Exception{

        Mockito.when(bookService.getAllBooks()).thenReturn(bookDtos);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn();

        List<BookDto> returnedBookDtos = JsonUtils.getObjectListFromJson(result.getResponse().getContentAsString(), BookDto.class);
        Assert.assertNotNull(returnedBookDtos);

    }

    @Test
    public void getBooksByAuthor() throws Exception {

        Mockito.when(bookService.getBooksByAuthor(1L)).thenReturn(bookDtos);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/author/{authorId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.getJsonFromObject(bookDtos)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<BookDto> returnedBookDtos = JsonUtils.getObjectListFromJson(result.getResponse().getContentAsString(), BookDto.class);
        Assert.assertNotNull(returnedBookDtos);

    }

    private AuthorDto createAuthorDto() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName("Mock Author");
        return authorDto;
    }

    private BookDto createBookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Mock Book");
        bookDto.setAuthors(Arrays.asList(createAuthorDto()));
        return bookDto;
    }

}
