package org.example.app.springdataemployees.utils.validate.custom_query_param_validators.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.app.springdataemployees.utils.validate.custom_query_param_validators.SortedByValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SortedByValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSortedBy {
    String message() default "Invalid sorted by param. Allowed values: ['id', 'employeeId', 'firstName', 'lastName', 'email', 'phoneNumber', 'position', 'isWork'].";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
