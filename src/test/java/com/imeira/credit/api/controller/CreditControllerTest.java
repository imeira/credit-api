package com.imeira.credit.api.controller;

import com.imeira.credit.api.dto.CreditDTO;
import com.imeira.credit.api.enums.CreditStatusEnum;
import com.imeira.credit.api.service.CreditService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreditControllerTest  {
    @Mock
    CreditService creditService;
    @InjectMocks
    CreditController creditController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    private CreditDTO getDto() {
        return new CreditDTO(BigInteger.valueOf(1), CreditStatusEnum.PENDING, BigInteger.valueOf(1));
    }


    @Test
    void testCreate() {
        when(creditService.create(any())).thenReturn(getDto());

        ResponseEntity<CreditDTO> result = creditController.create(getDto());
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }

    @Test
    void testFindAll() {
        when(creditService.findAll()).thenReturn(Arrays.<CreditDTO>asList(getDto()));

        ResponseEntity<List<CreditDTO>> result = creditController.findAll();
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }
}

