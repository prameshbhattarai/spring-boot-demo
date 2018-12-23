package com.example.demo.controller;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.LibraryDto;
import com.example.demo.entity.Address;
import com.example.demo.service.BookService;
import com.example.demo.service.LibraryService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

public class LibraryControllerTest {

    private static final String BASE_URL = "/libraries";

    @InjectMocks
    LibraryController libraryController;

    @Mock
    LibraryService libraryService;

    @Mock
    BookService bookService;

    private MockMvc mvc;

    private LibraryDto libraryDto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(libraryController)
                .setControllerAdvice(new ExceptionHandlerController()).build();
        libraryDto = createLibraryDto();
    }

    @Test
    public void getLibraries() throws Exception{
        Mockito.when(libraryService.getLibraries()).thenReturn(Arrays.asList(createLibraryDto()));

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(result);
    }

    @Test
    public void createLibrary() throws Exception{
        Mockito.when(libraryService.createLibrary(createLibraryDto())).thenReturn(createLibraryDto());

        MvcResult result =  mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.getJsonFromObject(libraryDto)))
//                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(result);
    }

    @Test
    public void getBooksFromLibrary() throws Exception{
        Mockito.when(bookService.getBooksFromLibrary(1L)).thenReturn(Arrays.asList(createBookDto()));

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/{libraryId}/book", 1))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(result);
    }

    @Test
    public void saveBookInLibrary() throws Exception{
        Mockito.when(bookService.saveBookInLibrary(1L, createBookDto())).thenReturn(createBookDto());

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/{libraryId}/book", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.getJsonFromObject(createBookDto())))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(result);
    }

    private LibraryDto createLibraryDto() {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setId(1L);
        libraryDto.setName("Mock Library");
        libraryDto.setAddress(createAddressDto());
        return libraryDto;
    }

    private AddressDto createAddressDto() {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(1L);
        addressDto.setLocation("Mock Location");
        return addressDto;
    }

    private BookDto createBookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Mock Book");
        bookDto.setLibrary(createLibraryDto());
        bookDto.setAuthors(Arrays.asList(createAuthorDto()));
        return bookDto;
    }

    private AuthorDto createAuthorDto() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1L);
        authorDto.setName("Mock Author");
        return authorDto;
    }
}
