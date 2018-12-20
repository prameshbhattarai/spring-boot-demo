package com.example.demo.service;

import com.example.demo.dto.LibraryDto;

import java.util.List;

public interface LibraryService {

    List<LibraryDto> getLibraries();

    LibraryDto createLibrary(LibraryDto libraryDto);
}
