package org.example.app.springdataemployees.utils.validate.custom_query_param_validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.app.springdataemployees.utils.validate.custom_query_param_validators.annotation.ValidSortDirection;

public class SortDirectionValidator implements ConstraintValidator<ValidSortDirection, String> {
    @Override
    public void initialize(ValidSortDirection constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return "asc".equalsIgnoreCase(value) || "desc".equalsIgnoreCase(value);
    }
}
