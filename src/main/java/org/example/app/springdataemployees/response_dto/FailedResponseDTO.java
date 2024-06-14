package org.example.app.springdataemployees.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FailedResponseDTO {
    private String error;
    private List<String> details;
}
