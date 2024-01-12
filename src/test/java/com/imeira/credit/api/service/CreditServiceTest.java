package com.imeira.credit.api.service;

import com.imeira.credit.api.domain.Person;
import com.imeira.credit.api.domain.Credit;
import com.imeira.credit.api.dto.PersonDTO;
import com.imeira.credit.api.dto.CreditDTO;
import com.imeira.credit.api.enums.CreditStatusEnum;
import com.imeira.credit.api.enums.IndicatorTypeEnum;
import com.imeira.credit.api.repository.CreditRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreditServiceTest {
    @Mock
    CreditRepository creditRepository;
    @InjectMocks
    CreditService creditService;

    @Mock
    PersonService PersonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDeleteAll() {
        creditService.deleteAll();
    }

    private CreditDTO getDto() {
        return new CreditDTO(BigInteger.valueOf(1), CreditStatusEnum.PENDING, BigInteger.valueOf(1));
    }

    private Credit getEntity() {
        return new Credit(BigInteger.valueOf(1), CreditStatusEnum.APPROVED.toString(), new Person(BigInteger.valueOf(1),
                "Name Person 1",
                "11111111111",
                BigDecimal.valueOf(1000.00),
                BigDecimal.valueOf(100.00)));
    }

    @Test
    void testFindById() {
        when(creditRepository.findById(any())).thenReturn(Optional.of(getEntity()));

        CreditDTO result = creditService.findById(BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindAll() {
        List<CreditDTO> result = creditService.findAll();
        Assertions.assertNotNull(result);
    }

    @Test
    void testCreateIfNotFound() {
        Credit result = creditService.createIfNotFound(getEntity());
        Assertions.assertNull(result);
    }

    @Test
    void testCreate() {
        when(PersonService.findById(any())).thenReturn(getPersonDTO());
        when(creditRepository.save(any())).thenReturn(getEntity());


        CreditDTO result = creditService.create(getDto());
        Assertions.assertNotNull(result);
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

    @Test
    void testFromEntity() {
        CreditDTO result = creditService.fromEntity(getEntity());
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromDTO() {
        Credit result = creditService.fromDTO(getDto());
        Assertions.assertNotNull(result);
    }

}
