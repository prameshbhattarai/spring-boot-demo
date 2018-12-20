package com.example.demo.dao;

import com.example.demo.entity.Library;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Library, Long> {

}
