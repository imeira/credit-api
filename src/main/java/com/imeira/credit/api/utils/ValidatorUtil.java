package com.imeira.credit.api.utils;

import org.apache.commons.lang3.StringUtils;

public class ValidatorUtil {

    public static Boolean isCpf(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");
        if (!StringUtils.isNumeric(cpf)) return false;
        return cpf.length() == 11;
    }

    public static Boolean isCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("[^\\d]", "");
        if (!StringUtils.isNumeric(cnpj)) return false;
        return cnpj.length() == 14;
    }

}
