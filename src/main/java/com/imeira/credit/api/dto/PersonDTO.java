package com.imeira.credit.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imeira.credit.api.validation.annotations.CpfCnpj;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDTO implements Serializable {

    private BigInteger id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("identificador")
    @NotBlank(message = "is required")
    @CpfCnpj(message = "must be a well-formed CPF or CPNJ")
    @Schema(name = "identificador", required = true, description = "user identifier number (CPF or CNPJ)", type = "String")
    private String indicator;

    private String indicatorType;

    @JsonProperty("valorMinimo")
    @Min(message = "O valor  ser maior ou igual a zero.", value = 0)
    private BigDecimal valueMin;

    @JsonProperty("valorMaximo")
    @Min(message = "O valor  ser maior ou igual a zero.", value = 0)
    private BigDecimal valueMax;

}
