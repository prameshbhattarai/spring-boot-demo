package com.example.demo.service;

import com.example.demo.dao.LibraryRepository;
import com.example.demo.dto.AddressDto;
import com.example.demo.dto.LibraryDto;
import com.example.demo.entity.Address;
import com.example.demo.entity.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class LibraryServiceTest {

    @InjectMocks
    LibraryServiceImpl libraryService;

    @Mock
    LibraryRepository libraryRepository;

    private Library library;
    private LibraryDto libraryDto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        library = createLibraryEntity();
        libraryDto = createLibraryDto();
    }

    @Test
    public void getLibraries() {
        Mockito.when(libraryRepository.findAll()).thenReturn(Arrays.asList(library));
        List<LibraryDto> libraryDtos = libraryService.getLibraries();

        Assert.assertNotNull(libraryDtos);
    }

    @Test
    public void createLibrary() {
        Mockito.when(libraryRepository.save(library)).thenReturn(library);
        LibraryDto responseLibraryDto = libraryService.createLibrary(libraryDto);

        Assert.assertNotNull(responseLibraryDto);
    }

    private LibraryDto createLibraryDto() {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setName("Mock Library");
        libraryDto.setAddress(createAddressDto());
        return libraryDto;
    }

    private AddressDto createAddressDto() {
        AddressDto addressDto = new AddressDto();
        addressDto.setLocation("Mock Location");
        return addressDto;
    }

    private Library createLibraryEntity() {
        Library library = new Library();
        library.setName("Mock Library");
        library.setAddress(createAddressEntity());
        return library;
    }

    private Address createAddressEntity() {
        Library library = new Library();

        Address address = new Address();
        address.setLocation("Mock Location");
        address.setLibrary(library);
        return address;
    }
}
