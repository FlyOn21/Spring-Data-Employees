package org.example.app.springdataemployees.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "employees")
public class Employee extends BaseEntity{
    @Column(name = "employee_id", nullable = false, unique = true)
    private String employeeId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phoneNumber;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "is_work", nullable = false)
    private Boolean isWork;




    public Employee(String firstName, String lastName, String email, String phone, String position, boolean isWork) {
        this.employeeId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phone;
        this.position = position;
        this.isWork = isWork;
    }

    public Employee(Long id, UUID uuid, String firstName, String lastName, String email, String phone, String position, boolean isWork) {
        super(id);
        this.employeeId = uuid.toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phone;
        this.position = position;
        this.isWork = isWork;
    }
}
