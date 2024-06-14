package org.example.app.springdataemployees.controllers;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.example.app.springdataemployees.entity.Employee;
import org.example.app.springdataemployees.response_dto.SuccessResponsePageDTO;
import org.example.app.springdataemployees.servises.EmployeeServis;
import org.example.app.springdataemployees.utils.validate.custom_query_param_validators.annotation.ValidSearchBy;
import org.example.app.springdataemployees.utils.validate.custom_query_param_validators.annotation.ValidSortDirection;
import org.example.app.springdataemployees.utils.validate.custom_query_param_validators.annotation.ValidSortedBy;
import org.example.app.springdataemployees.utils.validate.validate_dto.InputDataForValidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class EmployeesController {

    @Autowired
    private EmployeeServis employeeServis;

    @GetMapping(
            name = "employees",
            path = "employees/all",
            produces = "application/json"
    )
    public ResponseEntity<SuccessResponsePageDTO<List<Employee>>> fetchAllCustomers(
            @RequestParam(value = "sortedBy", defaultValue = "id", required = false) @ValidSortedBy String sortedBy,
            @RequestParam(value = "page", defaultValue = "1", required = false)
            @Valid
            @Min(value = 1, message = "must be greater than or equal to 1")
            Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false)
            @Valid
            @Min(value = 10, message = "must be greater than or equal to 10")
            @Max(value = 100, message = "must be less than or equal to 100")
            Integer size,
            @RequestParam(value = "direction", defaultValue = "desc", required = false) @ValidSortDirection String sort
    ) {

        return employeeServis.getEmployeesSort(page, size, sort, sortedBy);
    }

    @GetMapping(
            name = "employees",
            path = "employees/search"
    )
    public ResponseEntity<SuccessResponsePageDTO<List<Employee>>> searchEmployees(
            @RequestParam(value = "valueData")
            @Size(min = 1, max = 250, message = "Data must be between 1 and 250 characters long")
            String valueData,
            @RequestParam(value = "searchBy") @ValidSearchBy String searchBy,
            @RequestParam(value = "page", defaultValue = "1")
            @Valid
            @Min(value = 1, message = "must be greater than or equal to 1")
            Integer page,
            @RequestParam(value = "size", defaultValue = "10")
            @Valid
            @Min(value = 10, message = "must be greater than or equal to 10")
            @Max(value = 100, message = "must be less than or equal to 100")
            Integer size,
            @RequestParam(value = "direction", defaultValue = "desc") @ValidSortDirection String sort
    ) {
        return employeeServis.getEmployeesSearch(page, size, sort, searchBy, valueData);
    }

    @PostMapping(
            name = "employees",
            path = "employees/create"
    )
    public ResponseEntity<Employee> createEmployee(@RequestBody InputDataForValidateDTO employeeData) {
        return employeeServis.createEmployee(employeeData);
    }

    @PutMapping(
            name = "employees",
            path = "employees/updated"
    )
    public ResponseEntity<Employee> updateEmployee(@RequestBody InputDataForValidateDTO employeeData,
                                                   @RequestParam(value = "id") @Valid Long id) {
        return employeeServis.updateEmployee(employeeData, id);
    }

    @DeleteMapping(
            name = "employees",
            path = "employees/delete"
    )
    public ResponseEntity<Employee> deleteEmployee(@RequestParam(value = "id") @Valid Long id) {
        return employeeServis.deleteEmployee(id);
    }


}
