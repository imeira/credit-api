package com.imeira.credit.api.controller;

import com.imeira.credit.api.dto.PersonDTO;
import com.imeira.credit.api.enums.IndicatorTypeEnum;
import com.imeira.credit.api.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class PersonControllerTest {
    @Mock
    PersonService PersonService;
    @InjectMocks
    PersonController PersonController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreate() {
        when(PersonService.create(any())).thenReturn(getPersonDTO());

        ResponseEntity<PersonDTO> result = PersonController.create(getPersonDTO());
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }

    @Test
    void testFindAll() {
        when(PersonService.findAll()).thenReturn(Arrays.<PersonDTO>asList(getPersonDTO()));

        ResponseEntity<List<PersonDTO>> result = PersonController.findAll();
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }

    private static PersonDTO getPersonDTO() {
        return PersonDTO.builder()
                .id(BigInteger.valueOf(2))
                .name("Name Person 2")
                .indicator("12345678901")
                .valueMin(BigDecimal.valueOf(1000.00))
                .valueMax(BigDecimal.valueOf(10000.00))
                .build();
    }
}

