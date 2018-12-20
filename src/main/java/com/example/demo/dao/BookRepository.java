package com.example.demo.dao;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.library.id = :libraryId")
    List<Book> findAllBooksByLibraryId(@Param("libraryId") long libraryId);

}
