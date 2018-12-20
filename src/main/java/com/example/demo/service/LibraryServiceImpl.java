package com.example.demo.service;

import com.example.demo.dao.LibraryRepository;
import com.example.demo.entity.Address;
import com.example.demo.entity.Library;
import com.example.demo.dto.LibraryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.utils.ConvertToDto;

@Service
public class LibraryServiceImpl implements LibraryService{

    @Autowired
    LibraryRepository libraryRepository;

    @Override
    public List<LibraryDto> getLibraries() {
        List<Library> libraries = (List<Library>) libraryRepository.findAll();
        return ConvertToDto.convertToLibraryDtos(libraries);
    }

    @Override
    public LibraryDto createLibrary(LibraryDto libraryDto) {

        Address address = new Address();
        address.setLocation(libraryDto.getAddress().getLocation());

        Library library = new Library();
        library.setName(libraryDto.getName());
        library.setAddress(address);

        Library saved = libraryRepository.save(library);
        return ConvertToDto.convertToLibraryDto(saved);

    }



}

