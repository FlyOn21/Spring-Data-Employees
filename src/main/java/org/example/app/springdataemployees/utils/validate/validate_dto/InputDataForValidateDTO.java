package org.example.app.springdataemployees.utils.validate.validate_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputDataForValidateDTO {
    private Long id;
    private UUID employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String position;
    private String isWork;
}
