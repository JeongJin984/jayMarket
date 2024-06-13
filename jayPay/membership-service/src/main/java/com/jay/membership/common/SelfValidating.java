package com.jay.membership.common;

import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public abstract class SelfValidating<T> {
    private Validator validator;

    public SelfValidating() {
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        } catch (ConstraintViolationException e) {
            log.error("ConstraintViolationException", e);
        }
    }

    protected  void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T)this);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
