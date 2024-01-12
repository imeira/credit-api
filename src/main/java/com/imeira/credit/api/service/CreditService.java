package com.imeira.credit.api.service;

import com.imeira.credit.api.domain.Person;
import com.imeira.credit.api.domain.Credit;
import com.imeira.credit.api.dto.PersonDTO;
import com.imeira.credit.api.dto.CreditDTO;
import com.imeira.credit.api.enums.CreditStatusEnum;
import com.imeira.credit.api.repository.CreditRepository;
import com.imeira.credit.api.service.exception.InvalidTransactionException;
import com.imeira.credit.api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    PersonService PersonService;


    public void deleteAll() {
        creditRepository.deleteAll();
    }

    public CreditDTO findById(BigInteger id) {
        Optional<Credit> obj = creditRepository.findById(id);
        return obj.map(this::fromEntity).orElseThrow(() -> new ObjectNotFoundException("Credit not found!"));
    }

    public List<CreditDTO> find(Person Person) {
        Optional<List<Credit>> all = creditRepository.findByPerson(Person);
        return fromEntity(all.orElseThrow(() -> new ObjectNotFoundException("Credit not found by Person!")));
    }


    public  List<CreditDTO> findAll() {
        List<Credit> all = creditRepository.findAll();
        return fromEntity(all);
    }

    public Credit createIfNotFound(Credit credit) {
        Optional<Credit> obj = creditRepository.findById(credit.getId());
        return obj.orElseGet(() -> creditRepository.save(credit));
    }


    public CreditDTO create(CreditDTO creditDTO) {

        try {
            PersonDTO PersonDTO = PersonService.findById(creditDTO.getPersonId());
            Objects.requireNonNull(PersonDTO, String.format("Person inválido!"));

            creditDTO = fromEntity(creditRepository.save(fromDTO(creditDTO)));

        } catch (Exception e) {
            throw new InvalidTransactionException(e.getMessage());
        }
        return creditDTO;
    }





    public CreditDTO update(String id, CreditDTO PersonDTO) {
        Optional<Credit> obj = creditRepository.findById(new BigInteger(id));
        if (!obj.isPresent()) {
            throw new ObjectNotFoundException(String.format("Credit not found!"));
        }
        PersonDTO.setId(obj.get().getId());
        return fromEntity(creditRepository.save(fromDTO(PersonDTO)));
    }

    public void delete(String id) {
        Optional<Credit> obj = creditRepository.findById(new BigInteger(id));
        if (!obj.isPresent()) {
            throw new ObjectNotFoundException(String.format("Credit not found!"));
        }
        creditRepository.delete(obj.get());
    }

    public CreditDTO fromEntity(Credit credit) {
        return CreditDTO.builder()
                .id(credit.getId())
                .status(CreditStatusEnum.valueOf(credit.getStatus()))
                .PersonId(credit.getPerson().getId())
                .build();
    }


    public Credit fromDTO (CreditDTO creditDTO) {
        return Credit.builder()
                .id(creditDTO.getId())
                .status(creditDTO.getStatus().toString())
                .Person(new Person(creditDTO.getPersonId()))
                .build();
    }

    public List<CreditDTO> fromEntity(List<Credit> credits) {
        return credits.stream()
                .map(a -> new CreditDTO(a.getId(), CreditStatusEnum. valueOf(a.getStatus()), a.getPerson().getId())
        ).collect(Collectors.toList());
    }

    public List<Credit> fromDTO (List<CreditDTO> dtos) {
        return dtos.stream().map(
                a -> new Credit(a.getId(), a.getStatus().toString(), new Person(a.getPersonId()))
        ).collect(Collectors.toList());
    }

}
