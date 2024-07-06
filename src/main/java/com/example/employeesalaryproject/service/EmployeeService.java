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

    public EmployeeDetails getEmployeeByName(String name) {
        Employee employeeByName = employeeRepository.findByName(name).orElse(null);
        if (employeeByName == null) {
            throw new RuntimeException("No Employee Record Found with " + name);
        }
        EmployeeDetails employeeDetailsWithName = new EmployeeDetails();
        BeanUtils.copyProperties(employeeByName, employeeDetailsWithName);
        return employeeDetailsWithName;
    }

    public EmployeeDetails createEmployee(EmployeeDetails employeeDetails) {
        Employee newEmployee = new Employee();
        newEmployee.setName(employeeDetails.getName());
        newEmployee.setBasic(employeeDetails.getBasic());
        newEmployee.setHra(employeeDetails.getHra());
        newEmployee.setDa(employeeDetails.getDa());
        newEmployee.setDeduction(employeeDetails.getDeduction());

        Employee savedEmployee = employeeRepository.save(newEmployee);
        BeanUtils.copyProperties(savedEmployee, employeeDetails);
        return employeeDetails;
    }

    public double calculateGrossSalary(EmployeeDetails employeeDetails) {
        return employeeDetails.getBasic() + employeeDetails.getHra() + employeeDetails.getDa();
    }

    public double calculateNetSalary(EmployeeDetails employeeDetails) {
        double grossSalary = calculateGrossSalary(employeeDetails);
        double totalDeductions = employeeDetails.getDeduction();
        double netSalaryBeforeTax = grossSalary - totalDeductions;
        double tax = netSalaryBeforeTax * 0.15;
        double netSalary = netSalaryBeforeTax - tax;
        return netSalary;
    }
}
