package com.example.employee.controller;

import com.example.employee.entities.Employee;
import com.example.employee.entities.Response;
import com.example.employee.request.AddressRequest;
import com.example.employee.request.EmployeeRequest;
import com.example.employee.response.EmployeeResponse;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<EmployeeResponse> getEmployeeDetails(@RequestParam("employeeId") Long employeeId){
        return employeeService.getEmployee(employeeId);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<Response> updateEmployee(@RequestBody EmployeeRequest employeeRequest){
        return employeeService.update(employeeRequest);
    }

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<Response> deleteEmployee(@RequestParam("employeeId") Long employeeId){
        return employeeService.delete(employeeId);
    }
    @PutMapping("/editAddress")
    public ResponseEntity<Response> editAddress(@RequestParam("employeeId") Long employeeId,
                                                @RequestBody AddressRequest addressRequest){
        return employeeService.addAddress(employeeId,addressRequest);
    }

}
