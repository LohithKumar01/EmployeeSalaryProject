package com.example.employeesalaryproject.controller;

import com.example.employeesalaryproject.pojo.EmployeeDetails;
import com.example.employeesalaryproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public EmployeeDetails getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employee")
    public double getEmployeeByName(@RequestParam String name) {
        EmployeeDetails employeeByName = employeeService.getEmployeeByName(name);
        if (employeeByName == null) {
            System.out.println("Employee Null.");
        }
        double netSalary = employeeService.calculateNetSalary(employeeByName);
        return netSalary;
    }

    @DeleteMapping("/employee")
    public String deleteEmployeeById(@RequestParam Long id) {
        System.out.println("Employee Record to be deleted: " + id);
        return "Record Deleted";
    }

    @PostMapping("/employee")
    public EmployeeDetails createEmployee(@RequestBody EmployeeDetails employeeDetails) {
        return employeeService.createEmployee(employeeDetails);
    }
}
