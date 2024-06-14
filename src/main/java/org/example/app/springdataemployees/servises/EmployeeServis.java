package org.example.app.springdataemployees.servises;

import org.example.app.springdataemployees.entity.Employee;
import org.example.app.springdataemployees.exceptions.custome_exception.NotFoundException;
import org.example.app.springdataemployees.exceptions.custome_exception.ValidationException;
import org.example.app.springdataemployees.repository.EmployeesRepository;
import org.example.app.springdataemployees.response_dto.SuccessResponsePageDTO;
import org.example.app.springdataemployees.utils.validate.input_processing.ValidationProcessing;
import org.example.app.springdataemployees.utils.validate.validate_dto.InputDataForValidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServis {

    @Autowired
    private EmployeesRepository employeesRepository;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    private Pageable getPageable(Integer page, Integer size, String direction, String sortedBy) {
        page = page - 1;
        List<Sort.Order> orderBy = new ArrayList<>();
        Sort.Direction sortDirection = getSortDirection(direction);
        orderBy.add(new Sort.Order(sortDirection, sortedBy));
        return PageRequest.of(page, size, Sort.by(orderBy));
    }

    public ResponseEntity<SuccessResponsePageDTO<List<Employee>>> getEmployeesSort(
            Integer page,
            Integer size,
            String direction,
            String sortedBy
    ) {
        Pageable pagingWithSort = getPageable(page, size, direction, sortedBy);
        Page<Employee> pageAll = employeesRepository.findAll(pagingWithSort);
        Integer currentPage = pageAll.getTotalElements() == 0 ? 0 : pageAll.getNumber() + 1;
        SuccessResponsePageDTO<List<Employee>> successResponsePageDTO =
                new SuccessResponsePageDTO <>(
                        pageAll.getContent(),
                        currentPage,
                        pageAll.getTotalElements(),
                        pageAll.getTotalPages()
                );
        return ResponseEntity.ok(successResponsePageDTO);
    }

    public ResponseEntity<SuccessResponsePageDTO<List<Employee>>> getEmployeesSearch(
            Integer page,
            Integer size,
            String direction,
            String searchBy,
            String valueData
    ) {
        Pageable pagingSearch = getPageable(page, size, direction, searchBy);
        Page<Employee> employeePage;
        switch (searchBy) {
            case "employeeId":
                try {
                    UUID uuid = UUID.fromString(valueData.trim());
                    employeePage = employeesRepository.findByEmployeeIdContaining(uuid.toString(), pagingSearch);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid UUID format for employeeId");
                }
                break;
            case "firstName":
                employeePage = employeesRepository.findByFirstNameContaining(valueData.trim(), pagingSearch);
                break;
            case "lastName":
                employeePage = employeesRepository.findByLastNameContaining(valueData.trim(), pagingSearch);
                break;
            case "email":
                employeePage = employeesRepository.findByEmailContaining(valueData.trim(), pagingSearch);
                break;
            case "phoneNumber":
                employeePage = employeesRepository.findByPhoneNumberContaining(valueData.trim(), pagingSearch);
                break;
            case "position":
                employeePage = employeesRepository.findByPositionContaining(valueData.trim(), pagingSearch);
                break;
            case "isWork":
                try {
                    Boolean isWork = Boolean.valueOf(valueData);
                    employeePage = employeesRepository.findByIsWorkContaining(isWork, pagingSearch);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid boolean format for isWork");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid searchBy parameter");
        }
        Integer currentPage = employeePage.getTotalElements() == 0 ? 0 : employeePage.getNumber() + 1;
        SuccessResponsePageDTO<List<Employee>> successResponsePageDTO = new SuccessResponsePageDTO<>(
                employeePage.getContent(),
                currentPage,
                employeePage.getTotalElements(),
                employeePage.getTotalPages()
        );

        return ResponseEntity.ok(successResponsePageDTO);
    }

    public ResponseEntity<Employee> createEmployee(InputDataForValidateDTO employeeData) {
        ValidationProcessing validationProcessing = new ValidationProcessing(
                employeesRepository,
                employeeData
        );
        if (!validationProcessing.isValid()) {
            throw new ValidationException(
                    "Validation error",
                    validationProcessing
            );
        }
        Employee employee = new Employee();
        employee.setEmployeeId(UUID.randomUUID().toString());
        employee.setFirstName(employeeData.getFirstName());
        employee.setLastName(employeeData.getLastName());
        employee.setEmail(employeeData.getEmail());
        employee.setPhoneNumber(employeeData.getPhoneNumber());
        employee.setPosition(employeeData.getPosition());
        employee.setIsWork(Boolean.valueOf(employeeData.getIsWork()));
        employeesRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    public ResponseEntity<Employee> updateEmployee(InputDataForValidateDTO employeeData, Long id) {
        ValidationProcessing validationProcessing = new ValidationProcessing(
                employeesRepository,
                employeeData,
                id
        );
        if (!validationProcessing.isValid()) {
            throw new ValidationException(
                    "Validation error",
                    validationProcessing
            );
        }
        Employee employee = employeesRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Employee with id %s not found", id))
        );
        if (employeeData.getFirstName() != null) {
            employee.setFirstName(employeeData.getFirstName());
        }
        if (employeeData.getLastName() != null) {
            employee.setLastName(employeeData.getLastName());
        }
        if (employeeData.getEmail() != null) {
            employee.setEmail(employeeData.getEmail());
        }
        if (employeeData.getPhoneNumber() != null) {
            employee.setPhoneNumber(employeeData.getPhoneNumber());
        }
        if (employeeData.getPosition() != null) {
            employee.setPosition(employeeData.getPosition());
        }
        if (employeeData.getIsWork() != null) {
            employee.setIsWork(Boolean.valueOf(employeeData.getIsWork()));
        }
        employeesRepository.save(employee);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employee);
    }

    public ResponseEntity<Employee> deleteEmployee(Long id) {
        Employee employee = employeesRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Employee with id %s not found", id))
        );
        employeesRepository.delete(employee);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(employee);
    }
}
