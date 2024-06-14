package org.example.app.springdataemployees.utils.validate.custom_query_param_validators.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.app.springdataemployees.utils.validate.custom_query_param_validators.SearchByValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SearchByValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSearchBy {
    String message() default "Invalid search by param. Allowed values: ['employeeId', 'firstName', 'lastName', 'email', 'phoneNumber', 'position', 'isWork'].";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
