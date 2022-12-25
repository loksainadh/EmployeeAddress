package com.example.employee.repo;

import com.example.employee.entities.Address;

import java.util.List;

public interface EmployeeCustomRepo {

    public List<Address>  findByEmployeeName(String employeeName,String employeeType);
}
