package com.example.employee.repo;

import com.example.employee.entities.Address;
import com.example.employee.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeCustomRepoImpl implements EmployeeCustomRepo{

    @Autowired
    EntityManager entityManager;
    @Override
    public List<Address> findByEmployeeName(String employeeName, String employeeType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root =query.from(Employee.class);
        ListJoin<Employee,Address> addressListJoin=root.joinList("Employee");
      //  query.select(addressListJoin.getParent()).where(criteriaBuilder.equal(root.get()))
         return null;
    }
}
