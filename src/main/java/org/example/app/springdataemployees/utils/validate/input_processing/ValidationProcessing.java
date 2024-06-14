package org.example.app.springdataemployees.utils.validate.input_processing;

import lombok.Getter;
import org.example.app.springdataemployees.entity.Employee;
import org.example.app.springdataemployees.repository.EmployeesRepository;
import org.example.app.springdataemployees.utils.validate.Validator;
import org.example.app.springdataemployees.utils.validate.enums.EValidateCustomer;
import org.example.app.springdataemployees.utils.validate.enums.IValidateUnit;
import org.example.app.springdataemployees.utils.validate.validate_dto.InputDataForValidateDTO;
import org.example.app.springdataemployees.utils.validate.validate_dto.ValidateResultDTO;

import java.util.HashMap;
import java.util.Optional;


@Getter
public class ValidationProcessing {
    private final HashMap<String, String> validationFormErrors = new HashMap<>();
    private boolean isValid= true;
    private final InputDataForValidateDTO data;
    private final EmployeesRepository repository;
    private Long id;


    public ValidationProcessing(EmployeesRepository repository, InputDataForValidateDTO data) {
        this.repository = repository;
        this.data = data;
        validateFirstName(data.getFirstName());
        validateLastName(data.getLastName());
        validateEmail(data.getEmail());
        validatePhone(data.getPhoneNumber());
        validatePosition(data.getPosition());
        validateIsWork(data.getIsWork());
    }



    public ValidationProcessing(EmployeesRepository repository, InputDataForValidateDTO data, Long id) {
        this.repository = repository;
        this.id = id;
        this.data = data;
        validateFirstName(data.getFirstName());
        validateLastName(data.getLastName());
        validateEmail(data.getEmail());
        validatePhone(data.getPhoneNumber());
        validatePosition(data.getPosition());
        validateIsWork(data.getIsWork());
    }


    private ValidateResultDTO validateField(String value, IValidateUnit validationType) {
        Validator<IValidateUnit> validator = new Validator<>();
        if (id != null) {
            if (value == null) {
                ValidateResultDTO answer = new ValidateResultDTO();
                answer.setValid(true);
                return answer;
            }
            return validator.validate(value, validationType);
        } 
        if (value==null) {
            ValidateResultDTO answer = new ValidateResultDTO();
            answer.addError(" Is empty (required)");
            return answer;
        }
        return validator.validate(value, validationType);
    }

    private void validateFirstName(String firstName) {
        ValidateResultDTO answer = validateField(firstName, EValidateCustomer.FIRST_NAME);
            if (answer.isValid()) {
                isValid = false;
                validationFormErrors.put("firstName", String.join(", ", answer.getErrorsList()));
            }

    }

    private void validateLastName(String  lastName) {
        ValidateResultDTO answer = validateField(lastName, EValidateCustomer.LAST_NAME);
        if (answer.isValid()) {
            isValid = false;
            validationFormErrors.put("lastName", String.join(", ", answer.getErrorsList()));
        }
    }

    private void validateEmail(String  email) {
        ValidateResultDTO answer = validateField(email, EValidateCustomer.EMAIL);
        if (answer.isValid()) {
            isValid = false;
            validationFormErrors.put("email", String.join(", ", answer.getErrorsList()));
        }
        if (id == null && isEmailExists(email)) {
            isValid = false;
            answer.addError("Email already exists");
            validationFormErrors.put("email", String.join(", ", answer.getErrorsList()));
        }
        if (id != null && isEmailExists(email) && !isCustomerEmail(email, id)) {
            isValid = false;
            answer.addError("Email already exists");
            validationFormErrors.put("email", String.join(", ", answer.getErrorsList()));
        }
    }

    private void validatePhone(String  phone) {
        ValidateResultDTO answer = validateField(phone, EValidateCustomer.PHONE);
        if (answer.isValid()) {
            isValid = false;
            validationFormErrors.put("phoneNumber", String.join(", ", answer.getErrorsList()));
        }
    }

    private void validatePosition(String  position) {
        ValidateResultDTO answer = validateField(position, EValidateCustomer.POSITION);
        if (answer.isValid()) {
            isValid = false;
            validationFormErrors.put("position", String.join(", ", answer.getErrorsList()));
        }
    }

    private void validateIsWork(Boolean  isWork) {
        ValidateResultDTO answer = validateField(isWork.toString(), EValidateCustomer.IS_WORKING);
        if (answer.isValid()) {
            isValid = false;
            validationFormErrors.put("isWork", String.join(", ", answer.getErrorsList()));
        }
    }

    private boolean isEmailExists(String email) {
        Optional<Employee> employees = repository.findByEmail(email);
        return employees.isPresent();
    }

    private boolean isCustomerEmail(String email, Long id) {
        Employee employee = repository.getReferenceById(id);
        return employee.getEmail().equals(email);
    }
}
