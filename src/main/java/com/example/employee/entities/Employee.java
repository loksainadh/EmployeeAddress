package com.example.employee.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Employee_Details")
@Data
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String employeeName;
    @NotNull
    private Integer employeeAge;
    @NotNull
    private String gender;
    @NotNull
    private Long mobileNumber;
    @NotNull
    private Long alternateMobileNumber;
    @NotNull
    private Double employeeSalary;
    @NotNull
    @Email
    private String emailId;

    /*@OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private Address address;*/
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="emp_id",referencedColumnName = "id")
    private List<Address> address;

}
