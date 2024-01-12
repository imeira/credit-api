package com.imeira.credit.api.repository;

import com.imeira.credit.api.domain.Person;
import com.imeira.credit.api.domain.Credit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface CreditRepository extends MongoRepository<Credit, BigInteger> {

    Optional<List<Credit>> findByPerson(Person Person);

}
