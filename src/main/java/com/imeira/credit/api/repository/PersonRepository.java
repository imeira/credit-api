package com.imeira.credit.api.repository;

import com.imeira.credit.api.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<Person, BigInteger> {

    Optional<Person> findByName(String name);

}
