package com.imeira.credit.api.controller;


import com.imeira.credit.api.dto.CreditDTO;
import com.imeira.credit.api.dto.CreditDTO;
import com.imeira.credit.api.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/credits")
public class CreditController {

    @Autowired
    CreditService creditService;


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditDTO> create(@RequestBody CreditDTO creditDTO) {
        creditDTO = creditService.create(creditDTO);
        return ResponseEntity.ok().body(creditDTO);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<CreditDTO>> findAll() {
        List<CreditDTO> all = creditService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CreditDTO> findById(@PathVariable String id) {
        CreditDTO dto = creditService.findById(new BigInteger(id));
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditDTO> update(@PathVariable String id, @RequestBody CreditDTO PersonDTO) {
        CreditDTO dto = creditService.update(id, PersonDTO);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        creditService.delete(id);;
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
