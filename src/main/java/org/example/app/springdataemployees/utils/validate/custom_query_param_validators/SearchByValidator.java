package org.example.app.springdataemployees.utils.validate.custom_query_param_validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.app.springdataemployees.utils.validate.custom_query_param_validators.annotation.ValidSearchBy;

public class SearchByValidator implements ConstraintValidator<ValidSearchBy, String> {
    public void initialize(ValidSearchBy constraintAnnotation) {}

    public boolean isValid(String value,  ConstraintValidatorContext context)

    {
        return "employeeId".equalsIgnoreCase(value) || "firstName".equalsIgnoreCase(value)
                || "lastName".equalsIgnoreCase(value) || "email".equalsIgnoreCase(value)
                || "phoneNumber".equalsIgnoreCase(value) || "position".equalsIgnoreCase(value)
                || "isWork".equalsIgnoreCase(value);
    }
}
