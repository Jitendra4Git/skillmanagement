package com.nhs.skill.service.impl;

import com.nhs.skill.data.dao.EmployeeRepository;
import com.nhs.skill.data.domain.Employee;
import com.nhs.skill.data.dto.employee.EmployeeCreateRequestDTO;
import com.nhs.skill.data.dto.employee.EmployeeUpdateRequestDTO;
import com.nhs.skill.exception.ResourceNotFoundException;
import com.nhs.skill.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(String employeeName) {
        List<Employee> employees = new ArrayList<>();

        if (employeeName == null)
            employeeRepository.findAll().forEach(employees::add);
        else
            employeeRepository.findByEmployeeNameContaining(employeeName).forEach(employees::add);

        return employees;
    }

    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Employee with id = " + id));

        return employee;
    }

    public Employee createEmployee(EmployeeCreateRequestDTO employee) {
        Employee _employee;
        if (null != employee.getSkills()) {
            _employee = employeeRepository.save(Employee.builder()
                    .employeeName(employee.getEmployeeName())
                    .email(employee.getEmail())
                    .skills(employee.getSkills())
                    .build());
        } else {
            _employee = employeeRepository.save(new Employee(employee.getEmail(), employee.getEmployeeName()));

        }
        return _employee;
    }

    public Employee updateEmployee(Long id, EmployeeUpdateRequestDTO employee) {
        Employee _employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Employee with id = " + id));

        _employee.setEmail(employee.getEmail());
        _employee.setEmployeeName(employee.getEmployeeName());

        return employeeRepository.save(_employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();

    }

    public Employee findByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);

        return employee;
    }
}
