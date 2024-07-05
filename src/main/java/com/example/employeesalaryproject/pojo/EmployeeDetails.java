package com.example.employeesalaryproject.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDetails {
    private Long id;
    private String name;
    private Float basic;
    private Float hra;
    private Float da;
    private Float deduction;
}
