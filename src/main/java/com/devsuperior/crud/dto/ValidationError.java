package com.devsuperior.crud.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {
    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    private List<FieldError> errors = new ArrayList<>();

    public void addError(String fieldName, String message) {
        errors.add(new FieldError(fieldName, message));
    }

    public List<FieldError> getErrors() {
        return errors;
    }
}
