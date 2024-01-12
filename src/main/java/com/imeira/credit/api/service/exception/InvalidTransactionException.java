package com.imeira.credit.api.service.exception;

import java.io.Serializable;

public class InvalidTransactionException extends RuntimeException implements Serializable  {

    private static final long serialVersionUID = 1L;

    public InvalidTransactionException(String message) {
        super(message);
    }

}
