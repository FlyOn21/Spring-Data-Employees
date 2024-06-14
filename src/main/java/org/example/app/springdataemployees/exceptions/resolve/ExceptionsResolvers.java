package org.example.app.springdataemployees.exceptions.resolve;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app.springdataemployees.exceptions.custome_exception.NotFoundException;
import org.example.app.springdataemployees.exceptions.custome_exception.ValidationException;
import org.example.app.springdataemployees.response_dto.FailedResponseDTO;
import org.example.app.springdataemployees.response_dto.FailedValidationResponseDTO;
import org.example.app.springdataemployees.utils.validate.input_processing.ValidationProcessing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionsResolvers {

    private static final Logger SERVICE_LOGGER =
            LogManager.getLogger(ExceptionsResolvers.class);
    private static final Logger CONSOLE_LOGGER =
            LogManager.getLogger("console_logger");

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleExceptionMain(Exception ex) {
        if (ex instanceof HandlerExceptionResolver) {
            SERVICE_LOGGER.error("ERROR HandlerExceptionResolver: {}", ex.getMessage());
            CONSOLE_LOGGER.error("ERROR HandlerExceptionResolver: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(new FailedResponseDTO(ex.getClass().getSimpleName(), Collections.singletonList(ex.getMessage())));
        }
        if (ex instanceof MethodArgumentTypeMismatchException || ex instanceof IllegalArgumentException) {
            SERVICE_LOGGER.error("ERROR MethodArgumentTypeMismatchException: {}", ex.getMessage());
            CONSOLE_LOGGER.error("ERROR MethodArgumentTypeMismatchException: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(new FailedResponseDTO(ex.getClass().getSimpleName(), Collections.singletonList(ex.getMessage())));
        }
        if (ex instanceof HandlerMethodValidationException) {
            CONSOLE_LOGGER.error("ERROR HandlerMethodValidationException: {}", ((HandlerMethodValidationException) ex).getAllValidationResults());
            SERVICE_LOGGER.error("ERROR HandlerMethodValidationException: {}", ((HandlerMethodValidationException) ex).getAllValidationResults());
            Map<String, String> errors = new HashMap<>();
            ((HandlerMethodValidationException) ex).getAllValidationResults().forEach((error) -> errors.put(error.getMethodParameter().getParameter().getName(), error.getResolvableErrors().getFirst().getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new FailedValidationResponseDTO(ex.getClass().getSimpleName(), errors));
        }
        if (ex instanceof HttpMessageNotReadableException) {
            SERVICE_LOGGER.error("ERROR HttpMessageNotReadableException: {}", ex.getMessage());
            CONSOLE_LOGGER.error("ERROR HttpMessageNotReadableException: {}", ex.getMessage());
            Map<String, String> errors = new HashMap<>();
            errors.put("bodyError", ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new FailedValidationResponseDTO(ex.getClass().getSimpleName(), errors));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new FailedResponseDTO(ex.getClass().getSimpleName(), Collections.singletonList(ex.getMessage())));

    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<FailedValidationResponseDTO> validateExceptionResolver(ValidationException ex) {
        SERVICE_LOGGER.error("ERROR Validated: {}", ex.getMessage());
        CONSOLE_LOGGER.error("ERROR Validated: {}", ex.getMessage());
        ValidationProcessing validationProcessing = ex.getValidationProcessing();
        HashMap<String, String> errors = validationProcessing.getValidationFormErrors();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new FailedValidationResponseDTO(ex.getClass().getSimpleName(), errors));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<FailedResponseDTO> crudExceptionResolver(NotFoundException ex) {
        SERVICE_LOGGER.error("ERROR NotFoundException: {}", ex.getMessage());
        CONSOLE_LOGGER.error("ERROR NotFoundException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FailedResponseDTO(ex.getClass().getSimpleName(), Collections.singletonList(ex.getMessage())));
    }
}
