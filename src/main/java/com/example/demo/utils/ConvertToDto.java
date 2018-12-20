package com.example.demo.utils;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.LibraryDto;
import com.example.demo.entity.Address;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Library;

import java.util.ArrayList;
import java.util.List;

public class ConvertToDto {

    public static List<LibraryDto> convertToLibraryDtos(List<Library> libraries) {
        List<LibraryDto> libraryDtos = new ArrayList<>();
        for(Library library : libraries) {
            libraryDtos.add(convertToLibraryDto(library));
        }
        return libraryDtos;
    }

    public static LibraryDto convertToLibraryDto(Library library) {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setId(library.getId());
        libraryDto.setName(library.getName());
        libraryDto.setAddress(convertToAddressDto(library.getAddress()));
        return libraryDto;
    }

    public static AddressDto convertToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setLocation(address.getLocation());
        return addressDto;
    }

    public static List<BookDto> convertToBookDtos(List<Book> books) {
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book : books) {
            bookDtos.add(convertToBookDto(book));
        }
        return bookDtos;
    }

    public static BookDto convertToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthors(convertToAuthorDtos(book.getAuthors()));
        if(book.getLibrary() != null) {
            bookDto.setLibrary(convertToLibraryDto(book.getLibrary()));
        }
        return bookDto;
    }

    public static List<AuthorDto> convertToAuthorDtos(List<Author> authors) {
        List<AuthorDto> authorDtos = new ArrayList<>();
        for(Author author : authors ) {
            authorDtos.add(convertToAuthorDto(author));
        }
        return authorDtos;
    }

    public static AuthorDto convertToAuthorDto(Author author) {
       AuthorDto authorDto = new AuthorDto();
       authorDto.setId(author.getId());
       authorDto.setName(author.getName());
       authorDto.setBooks(convertToBookDtosWithoutAuthor(author.getBooks()));
       return authorDto;
    }

    private static List<BookDto> convertToBookDtosWithoutAuthor(List<Book> books) {
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book : books) {
            bookDtos.add(convertToBookDtoWithoutAuthor(book));
        }
        return bookDtos;
    }

    public static BookDto convertToBookDtoWithoutAuthor(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        if(book.getLibrary() != null) {
            bookDto.setLibrary(convertToLibraryDto(book.getLibrary()));
        }
        return bookDto;
    }


}
