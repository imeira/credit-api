package com.imeira.credit.api.domain;

import com.imeira.credit.api.enums.IndicatorTypeEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Document
@Getter
@Setter
public class Person implements Serializable {

    @Id
    private BigInteger id;

    private String name;

    private String indicator;

    private String indicatorType;

    private BigDecimal valueMin;

    private BigDecimal valueMax;

    public Person() {
    }

    public Person(BigInteger id) {
        this.id = id;
    }

    public Person(BigInteger id, String name, String indicator, BigDecimal valueMin, BigDecimal valueMax) {
        this.id = id;
        this.name = name;
        this.indicator = indicator;
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.setIndicatorType(indicator);
    }

    public void setIndicatorType(String indicator) {
        if (indicator != null) {
            this.indicatorType = IndicatorTypeEnum.fromIndicatorCode(this.indicator.length()).name();
        }
    }

}
