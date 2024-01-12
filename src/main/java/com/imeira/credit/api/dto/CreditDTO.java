package com.imeira.credit.api.dto;

import com.imeira.credit.api.enums.CreditStatusEnum;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditDTO implements Serializable {

  private BigInteger id;

  private CreditStatusEnum status;

  private BigInteger PersonId;

}
