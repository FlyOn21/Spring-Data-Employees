package org.example.app.springdataemployees.utils.validate;


import org.example.app.springdataemployees.utils.validate.validate_dto.ValidateResultDTO;
import org.example.app.springdataemployees.utils.validate.enums.IValidateUnit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator<T extends IValidateUnit> {
    public ValidateResultDTO validate(String value, T field) {
        ValidateResultDTO answer = new ValidateResultDTO();
        Pattern pattern = Pattern.compile(field.getValidationRegex());
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            answer.addError(field.getErrorMsg());
            return answer;
        }
        answer.setValid(answer.getErrorsList().isEmpty());
        return answer;
    }
}
