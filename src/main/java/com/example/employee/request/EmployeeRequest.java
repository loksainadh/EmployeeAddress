package com.example.employee.request;


import lombok.Data;
import java.util.List;

@Data
public class EmployeeRequest {


    private Long id;
    private String employeeName;
    private Integer employeeAge;

    private String gender;
    private Long mobileNumber;
    private Long alternateMobileNumber;
    private Double employeeSalary;
    private String emailId;
    private List<AddressRequest> address;
}
