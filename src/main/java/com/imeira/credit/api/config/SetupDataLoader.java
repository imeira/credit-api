package com.imeira.credit.api.config;

import com.imeira.credit.api.domain.Credit;
import com.imeira.credit.api.domain.Person;
import com.imeira.credit.api.enums.CreditStatusEnum;
import com.imeira.credit.api.service.CreditService;
import com.imeira.credit.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.math.BigDecimal;
import java.math.BigInteger;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    PersonService PersonService;

    @Autowired
    CreditService creditService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        cleanTables();
        insertTables();

    }

    private void cleanTables() {
        PersonService.deleteAll();
        creditService.deleteAll();
    }

    private void insertTables() {

        //create Persons
        Person PersonA = new Person(BigInteger.valueOf(1),
                "Name Person 1",
                "11111111111",
                BigDecimal.valueOf(1000.00),
                BigDecimal.valueOf(100.00));
        PersonService.createIfNotFound(PersonA);
        Person PersonB = new Person(BigInteger.valueOf(1),
                "Name Person 2",
                "22222222222",
                BigDecimal.valueOf(2000.00),
                BigDecimal.valueOf(200.00));
        PersonService.createIfNotFound(PersonB);

        //create credits
        creditService.createIfNotFound(Credit.builder()
                .id(BigInteger.valueOf(1))
                .status(CreditStatusEnum.PENDING.toString())
                .Person(PersonA).build());
        creditService.createIfNotFound((Credit.builder()
                .id(BigInteger.valueOf(2))
                .Person(PersonB)
                .status(CreditStatusEnum.APPROVED.toString())
                .Person(PersonB).build()));
    }

}
