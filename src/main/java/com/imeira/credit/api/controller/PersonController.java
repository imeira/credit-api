package com.imeira.credit.api.controller;


import com.imeira.credit.api.dto.PersonDTO;
import com.imeira.credit.api.service.PersonService;
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
@RequestMapping("/Persons")
public class PersonController {

    @Autowired
    PersonService PersonService;


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO PersonDTO) {
        PersonDTO = PersonService.create(PersonDTO);
        return ResponseEntity.ok().body(PersonDTO);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<PersonDTO> all = PersonService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable String id) {
        PersonDTO dto = PersonService.findById(new BigInteger(id));
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable String id, @RequestBody PersonDTO PersonDTO) {
        PersonDTO dto = PersonService.update(id, PersonDTO);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        PersonService.delete(id);;
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
