package com.imeira.credit.api.service;

import com.imeira.credit.api.domain.Person;
import com.imeira.credit.api.dto.PersonDTO;
import com.imeira.credit.api.repository.PersonRepository;
import com.imeira.credit.api.service.exception.ObjectAlreadyExistException;
import com.imeira.credit.api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    public static final String PERSON_NOT_FOUND = "Person not found!";

    @Autowired
    private PersonRepository PersonRepository;


    public void deleteAll() {
        PersonRepository.deleteAll();
    }

    public PersonDTO findById(BigInteger id) {
        Optional<Person> obj = PersonRepository.findById(id);
        return obj.map(this::fromEntity).orElseThrow(() -> new ObjectNotFoundException(PERSON_NOT_FOUND));
    }


    public  List<PersonDTO> findAll() {
        List<Person> all = PersonRepository.findAll();
        return fromEntity(all);
    }

    public PersonDTO findByName(String name) {
        Optional<Person> obj = PersonRepository.findByName(name);
        return obj.map(this::fromEntity).orElse(null);
    }

    public Person createIfNotFound(Person person) {
        Optional<Person> obj = PersonRepository.findByName(person.getName());
        return obj.orElseGet(() -> PersonRepository.save(person));
    }

    public PersonDTO create(PersonDTO personDTO) {
        Optional<Person> obj = PersonRepository.findByName(personDTO.getName());
        if (obj.isPresent()) {
            throw new ObjectAlreadyExistException(String.format("Já existe um Person com esse name!"));
        }
        return fromEntity(PersonRepository.save(fromDTO(personDTO)));
    }

    public PersonDTO update(String id, PersonDTO personDTO) {
        Optional<Person> obj = PersonRepository.findById(new BigInteger(id));
        validatePerson(obj);
        personDTO.setId(obj.get().getId());
        return fromEntity(PersonRepository.save(fromDTO(personDTO)));
    }

    public void delete(String id) {
        Optional<Person> obj = PersonRepository.findById(new BigInteger(id));
        validatePerson(obj);
        PersonRepository.delete(obj.get());
    }

    private static void validatePerson(Optional<Person> obj) {
        if (!obj.isPresent()) {
            throw new ObjectNotFoundException(String.format(PERSON_NOT_FOUND));
        }
    }

    public PersonDTO fromEntity(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .indicator(person.getIndicator())
                .indicatorType(person.getIndicatorType())
                .build();
    }

    public Person fromDTO (PersonDTO personDTO) {
        return new Person(personDTO.getId(),
                personDTO.getName(),
                personDTO.getIndicator(),
                personDTO.getValueMin(),
                personDTO.getValueMax()
        );
    }

    public List<PersonDTO> fromEntity(List<Person> persons) {
        return persons.stream()
                .map(a -> PersonDTO.builder()
                        .id(a.getId())
                        .name(a.getName())
                        .indicator(a.getIndicator())
                        .indicatorType(a.getIndicatorType())
                        .build()
        ).collect(Collectors.toList());
    }

    public List<Person> fromDTO (List<PersonDTO> dtos) {
        return dtos.stream().map(
                a -> new Person(a.getId(),
                        a.getName(),
                        a.getIndicator(),
                        a.getValueMin(),
                        a.getValueMax()
                )
        ).collect(Collectors.toList());
    }


}
