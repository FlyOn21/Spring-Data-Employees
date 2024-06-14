package org.example.app.springdataemployees.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponsePageDTO<T> {
    T data;
    Integer currentPage;
    Long totalItems;
    Integer totalPages;
}
