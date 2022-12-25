package com.example.employee.response;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponse {
    private Long id;
    private String employeeName;
    private Integer employeeAge;

    private String gender;
    private Long mobileNumber;
    private Long alternateMobileNumber;
    private Double employeeSalary;
    private String emailId;
    private List<AddressResponse> address;

}
