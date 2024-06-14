package org.example.app.springdataemployees.response_dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class FailedValidationResponseDTO {
    private String error;
    private Map<String, String> details;
}
