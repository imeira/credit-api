package com.imeira.credit.api.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigInteger;

@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Credit implements Serializable {

  @Id
  private BigInteger id;

  private String status;

  @DBRef
  private Person Person;

}
