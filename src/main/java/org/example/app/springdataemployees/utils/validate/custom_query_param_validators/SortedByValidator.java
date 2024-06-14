package org.example.app.springdataemployees.utils.validate.custom_query_param_validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.app.springdataemployees.utils.validate.custom_query_param_validators.annotation.ValidSortedBy;

public class SortedByValidator implements ConstraintValidator<ValidSortedBy, String> {
    @Override
    public void initialize(ValidSortedBy constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return "id".equalsIgnoreCase(value) || "employeeId".equalsIgnoreCase(value) ||
                "firstName".equalsIgnoreCase(value) || "lastName".equalsIgnoreCase(value) ||
                "email".equalsIgnoreCase(value) || "phoneNumber".equalsIgnoreCase(value) ||
                "position".equalsIgnoreCase(value) || "isWork".equalsIgnoreCase(value);
    }
}
