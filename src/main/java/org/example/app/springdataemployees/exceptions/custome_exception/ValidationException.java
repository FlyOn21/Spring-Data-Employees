package org.example.app.springdataemployees.exceptions.custome_exception;

import lombok.Getter;
import org.example.app.springdataemployees.utils.validate.input_processing.ValidationProcessing;


@Getter
public class ValidationException extends RuntimeException{
        private final ValidationProcessing validationProcessing;

        public ValidationException(String message, ValidationProcessing validationProcessing) {
            super(message);
            this.validationProcessing = validationProcessing;
        }


}
