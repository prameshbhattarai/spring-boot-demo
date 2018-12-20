package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto implements Serializable {

    private long id;
    private String title;
    private LibraryDto library;
    private List<AuthorDto> authors;

    public BookDto() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LibraryDto getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDto library) {
        this.library = library;
    }

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return id == bookDto.id &&
                Objects.equals(title, bookDto.title) &&
                Objects.equals(library, bookDto.library) &&
                Objects.equals(authors, bookDto.authors);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, library, authors);
    }
}
