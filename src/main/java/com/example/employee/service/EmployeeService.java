package com.example.employee.service;

import com.example.employee.constatants.MessageConstants;
import com.example.employee.entities.Address;
import com.example.employee.entities.Employee;
import com.example.employee.entities.Response;
import com.example.employee.exceptions.EmployeeNotFoundException;
import com.example.employee.repo.AddressRepo;
import com.example.employee.repo.EmployeeRepo;
import com.example.employee.request.AddressRequest;
import com.example.employee.request.EmployeeRequest;
import com.example.employee.response.AddressResponse;
import com.example.employee.response.EmployeeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private AddressRepo addressRepo;

    public ResponseEntity<Response> addEmployee(EmployeeRequest employeeRequest) {
        Response response = new Response();
        if (employeeRequest != null) {
            Employee employee = constrctEmployee(employeeRequest);
            log.info("employee " + employee);
            employeeRepo.save(employee);
            response.setStatus(true);
            response.setErrorMessage(MessageConstants.EMPLOYEE_ADDED);
        } else {
            response.setStatus(false);
            response.setErrorMessage(MessageConstants.EMPLOYEE_ADDED_FAILED);
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<EmployeeResponse> getEmployee(Long employeeId) {
            Employee employee = employeeRepo.findById(employeeId).
                    orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found "));
        EmployeeResponse employeeResponse = constructEmployeeResponse(employee);
        return ResponseEntity.ok().body(employeeResponse);
    }



    public ResponseEntity<Response> update(EmployeeRequest employeeRequest) {
        Response response=new Response();
        if (employeeRequest != null) {
            Optional<Employee> existEmployee = employeeRepo.findById(employeeRequest.getId());
            if (existEmployee.isPresent()) {
                log.info("existing employee is present " + existEmployee.get().getId());
                Employee updateEmployee = existEmployee.get();
                updateEmployee.setEmployeeName(employeeRequest.getEmployeeName());
                updateEmployee.setEmployeeSalary(employeeRequest.getEmployeeSalary());
                updateEmployee.setAlternateMobileNumber(employeeRequest.getAlternateMobileNumber());
                employeeRepo.save(updateEmployee);
                log.info("employee details updated..");
                response.setStatus(true);
                response.setErrorMessage(MessageConstants.EMPLOYEE_UPDATED);
            } else {
                response.setStatus(false);
                response.setErrorMessage(MessageConstants.EMPLOYEE_NOT_FOUND);

            }
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Response> delete(Long employeeId) {

        Response response=new Response();
        if (employeeId != null) {
            boolean existEmployee = employeeRepo.existsById(employeeId);
            if (existEmployee) {
                log.info("employee is present ");
                employeeRepo.deleteById(employeeId);
                response.setStatus(true);
                response.setErrorMessage(employeeId+MessageConstants.EMPLOYEE_DELETED);
            } else {
                response.setStatus(false);
                response.setErrorMessage(MessageConstants.EMPLOYEE_NOT_FOUND);
            }
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Response> addAddress(Long employeeId, AddressRequest addressRequest) {
        Response response = new Response();
        if (employeeId != null && addressRequest != null) {
            Optional<Employee> existEmployee = employeeRepo.findById(employeeId);
            if (existEmployee.isPresent()) {
                Address address = new Address();
                address.setCity(addressRequest.getCity());
                address.setAddressType(addressRequest.getAddressType());
                address.setCountry(addressRequest.getCountry());
                address.setState(addressRequest.getState());
                address.setPincode(addressRequest.getPincode());
               // address.setEmployee(constrctEmployee(existEmployee.get()));
                addressRepo.save(address);
                response.setStatus(true);
                response.setErrorMessage(MessageConstants.ADDRESS_ADDED);
            } else {
                response.setStatus(false);
                response.setErrorMessage(MessageConstants.EMPLOYEE_NOT_FOUND);
            }
        }
        return ResponseEntity.ok(response);
    }


    public static Employee constrctEmployee(EmployeeRequest employeeRequest) {
        Employee emp = new Employee();
        emp.setId(employeeRequest.getId());
        emp.setEmployeeName(employeeRequest.getEmployeeName());
        emp.setEmployeeAge(employeeRequest.getEmployeeAge());
        emp.setEmployeeSalary(employeeRequest.getEmployeeSalary());
        emp.setGender(employeeRequest.getGender());
        emp.setEmailId(employeeRequest.getEmailId());
        emp.setMobileNumber(employeeRequest.getMobileNumber());
        emp.setAlternateMobileNumber(employeeRequest.getAlternateMobileNumber());
        emp.setAddress(constructAddress(employeeRequest.getAddress()));
        return emp;
    }
    private EmployeeResponse constructEmployeeResponse(Employee employee) {
        EmployeeResponse employeeResponse=new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setEmployeeName(employeeResponse.getEmployeeName());
        employeeResponse.setEmployeeSalary(employeeResponse.getEmployeeSalary());
        employeeResponse.setEmployeeAge(employeeResponse.getEmployeeAge());
        employeeResponse.setGender(employeeResponse.getGender());
        employeeResponse.setEmailId(employeeResponse.getEmailId());
        employeeResponse.setMobileNumber(employee.getMobileNumber());
        employeeResponse.setAlternateMobileNumber(employee.getAlternateMobileNumber());
        employeeResponse.setAddress(constructAddressResponse(employee.getAddress()));
        return employeeResponse;
    }

    private List<AddressResponse> constructAddressResponse(List<Address> address) {
        List<AddressResponse> addressResponseList = new ArrayList<>();
        for (Address address1 : address) {
            AddressResponse addressResponse = new AddressResponse();
            addressResponse.setAddress(address1.getAddress());
            addressResponse.setAddressType(address1.getAddressType());
            addressResponse.setPincode(address1.getPincode());
            addressResponse.setId(address1.getId());
           addressResponse.setCity(address1.getCity());
           addressResponse.setCountry(address1.getCountry());
           addressResponseList.add(addressResponse);
        }
        return addressResponseList;
    }

    private static List<Address> constructAddress(List<AddressRequest> addressRequest) {
        List<Address> addressList= new ArrayList<>();
        for(AddressRequest address1:addressRequest){
            Address address=new Address();
            address.setAddressType(address1.getAddressType());
            address.setPincode(address1.getPincode());
            address.setCountry(address1.getCountry());
            address.setState(address1.getState());
            address.setCity(address1.getCity());
            addressList.add(address);
        }
        return addressList;
    }
}
