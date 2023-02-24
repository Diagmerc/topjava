package ru.javawebinar.topjava.repository.jdbc;

import javax.validation.*;
import java.util.Set;

public abstract class AbstractJdbcRepository {
    protected void validate(Object entity){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> validate = validator.validate(entity);
        if(validate.size() > 0){
            throw new ConstraintViolationException("wrong data", validate);
        }
    }
}
