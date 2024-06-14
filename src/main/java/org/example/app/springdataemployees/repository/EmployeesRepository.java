package org.example.app.springdataemployees.repository;

import org.example.app.springdataemployees.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Long>{
    Optional<Employee> findByEmail(String email);
    Page<Employee> findByEmployeeIdContaining(String employeeId, Pageable pageable);
    Page<Employee> findByFirstNameContaining(String firstName, Pageable pageable);
    Page<Employee> findByLastNameContaining(String lastName, Pageable pageable);
    Page<Employee> findByEmailContaining(String email, Pageable pageable);
    Page<Employee> findByPhoneNumberContaining(String phoneNumber, Pageable pageable);
    Page<Employee> findByPositionContaining(String position, Pageable pageable);
    Page<Employee> findByIsWorkContaining(Boolean isWork, Pageable pageable);
}
