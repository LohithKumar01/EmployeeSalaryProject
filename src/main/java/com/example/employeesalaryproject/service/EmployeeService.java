package com.example.employeesalaryproject.service;

import com.example.employeesalaryproject.entity.Employee;
import com.example.employeesalaryproject.pojo.EmployeeDetails;
import com.example.employeesalaryproject.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDetails getEmployeeById(Long id) {
        Employee employeeById = employeeRepository.findById(id).orElse(null);
        if (employeeById == null) {
            throw new RuntimeException("No Employee Record Found in DB.");
        }
        EmployeeDetails employeeDetails = new EmployeeDetails();
        BeanUtils.copyProperties(employeeById, employeeDetails);
        return employeeDetails;
    }
}
