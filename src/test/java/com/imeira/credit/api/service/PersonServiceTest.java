package com.imeira.credit.api.service;

import com.imeira.credit.api.domain.Person;
import com.imeira.credit.api.dto.PersonDTO;
import com.imeira.credit.api.enums.IndicatorTypeEnum;
import com.imeira.credit.api.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class PersonServiceTest {
    @Mock
    PersonRepository PersonRepository;
    @InjectMocks
    PersonService PersonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDeleteAll() {
        PersonService.deleteAll();
    }

    @Test
    void testFindById() {
        when(PersonRepository.findById(any())).thenReturn(Optional.of(getPerson()));

        PersonDTO result = PersonService.findById(BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindAll() {
        List<PersonDTO> result = PersonService.findAll();
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindByDescription() {
        when(PersonRepository.findByName(anyString())).thenReturn(Optional.of(getPerson()));

        PersonDTO result = PersonService.findByName("Test");
        Assertions.assertNotNull(result);
    }

    @Test
    void testCreateIfNotFound() {
        when(PersonRepository.findByName(anyString())).thenReturn(Optional.of(getPerson()));

        Person result = PersonService.createIfNotFound(getPerson());
        Assertions.assertNotNull(result);
    }

    private static Person getPerson() {
        return new Person(BigInteger.valueOf(1),
                "Name Person 1",
                "11111111111",
                BigDecimal.valueOf(1000.00),
                BigDecimal.valueOf(100.00));
    }

    private static PersonDTO getPersonDTO() {
        return PersonDTO.builder()
                .id(BigInteger.valueOf(2))
                .name("Name Person 2")
                .indicator("12345678901")
                .build();
    }

    @Test
    void testCreate() {
        when(PersonRepository.save(any())).thenReturn(getPerson());

        PersonDTO result = PersonService.create(getPersonDTO());
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromEntity() {
        PersonDTO result = PersonService.fromEntity(getPerson());
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromDTO() {
        Person result = PersonService.fromDTO(getPersonDTO());
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromEntity2() {
        List<PersonDTO> result = PersonService.fromEntity(Arrays.<Person>asList(getPerson()));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromDTO2() {
        List<Person> result = PersonService.fromDTO(Arrays.<PersonDTO>asList(getPersonDTO()));
        Assertions.assertNotNull(result);
    }
}

