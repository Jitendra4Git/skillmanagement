package com.nhs.skill.service;

import com.nhs.skill.data.domain.Employee;
import com.nhs.skill.data.dto.employee.EmployeeCreateRequestDTO;
import com.nhs.skill.data.dto.employee.EmployeeUpdateRequestDTO;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees(String employeeName);

    Employee getEmployeeById(Long id);

    Employee createEmployee(EmployeeCreateRequestDTO employee);

    Employee updateEmployee(Long id, EmployeeUpdateRequestDTO employee);

    void deleteEmployee(Long id);

    void deleteAllEmployees();

    Employee findByEmail(String email);
}
