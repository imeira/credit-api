package com.imeira.credit.api.validation;

import com.imeira.credit.api.utils.ValidatorUtil;
import com.imeira.credit.api.validation.annotations.CpfCnpj;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isBlank(value) || ValidatorUtil.isCpf(value) || ValidatorUtil.isCnpj(value);
    }

}
