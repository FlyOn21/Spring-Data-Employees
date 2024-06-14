package org.example.app.springdataemployees.exceptions.custome_exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
        public NotFoundException(String message) {
            super(message);
        }
}
