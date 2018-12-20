package com.example.demo.controller;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.service.AuthorService;
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

public class AuthorControllerTest {

    private final String BASE_URL = "/authors";

    private AuthorDto authorDto;

    private List<AuthorDto> authorDtos;

    private MockMvc mvc;

    @InjectMocks
    AuthorController authorController;

    @Mock
    AuthorService authorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(authorController)
                .setControllerAdvice(new ExceptionHandlerController()).build();
        authorDto = createAuthorDto();
        authorDtos = Arrays.asList(authorDto);
    }

    @Test
    public void createAuthor() throws Exception {

        Mockito.when(authorService.createAuthor(authorDto)).thenReturn(authorDto);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.getJsonFromObject(authorDto)))
                .andDo(print())
                .andExpect(status().isOk())
                        .andReturn();

        AuthorDto returnedAuthor = JsonUtils.getObjectFromJson(result.getResponse().getContentAsString(), AuthorDto.class);
        Assert.assertNotNull(returnedAuthor);
    }

    @Test
    public void getAuthor() throws Exception {

        Mockito.when(authorService.getAuthor(1L)).thenReturn(authorDto);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andReturn();

        AuthorDto returnedAuthor = JsonUtils.getObjectFromJson(result.getResponse().getContentAsString(), AuthorDto.class);
        Assert.assertNotNull(returnedAuthor);
    }

    @Test
    public void getAuthors() throws Exception {

        Mockito.when(authorService.getAuthors()).thenReturn(authorDtos);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn();

        List<AuthorDto> returnedAuthor = JsonUtils.getObjectListFromJson(result.getResponse().getContentAsString(), AuthorDto.class);
        Assert.assertNotNull(returnedAuthor);

    }

    @Test
    public void addBooksToAuthor() throws Exception {

        BookDto mockBookDto = new BookDto();
        mockBookDto.setId(1L);
        mockBookDto.setTitle("Mock book added");

        Mockito.when(authorService.addBooks(1, Arrays.asList(mockBookDto))).thenReturn(authorDto);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/{authorId}/books", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.getJsonFromObject(Arrays.asList(mockBookDto))))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        AuthorDto returnedAuthor = JsonUtils.getObjectFromJson(result.getResponse().getContentAsString(), AuthorDto.class);
        Assert.assertNotNull(returnedAuthor);

    }

    private AuthorDto createAuthorDto() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Mock Book");

        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1L);
        authorDto.setName("Mock Author");
        authorDto.setBooks(Arrays.asList(bookDto));
        return authorDto;

    }


}
