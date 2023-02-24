package ru.javawebinar.topjava.repository.jdbc;

import javax.validation.*;
import java.util.Set;

public interface ValidateAble<T> {
    default void validate(T entity) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validate = validator.validate(entity);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException("wrong data", validate);
        }
    }
}
