package org.example.app.springdataemployees.utils.validate.custom_query_param_validators.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.app.springdataemployees.utils.validate.custom_query_param_validators.SortDirectionValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SortDirectionValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSortDirection {
    String message() default "Invalid sort direction. Allowed values are 'asc' or 'desc'.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}