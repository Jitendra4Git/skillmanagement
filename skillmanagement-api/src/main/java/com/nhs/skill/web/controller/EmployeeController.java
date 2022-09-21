package com.nhs.skill.web.controller;

import com.nhs.skill.data.domain.Employee;
import com.nhs.skill.data.dto.employee.*;
import com.nhs.skill.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(value = "Employee Management", description = "REST Apis related to Employee Entity")
@RestController
@RequestMapping("/nhs/employee")
//@Secured({"IS_AUTHENTICATED_FULLY", "IS_AUTHENTICATED_REMEMBERED"})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Get list of Employees"
            , response = Iterable.class, tags = "getAllEmployees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "employeeName",
                    value = "Name of the employee",
                    required = false,
                    dataType = "string",
                    paramType = "body"
            )
    })
    @GetMapping("/getEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String employeeName) {

        List<Employee> employees = employeeService.getAllEmployees(employeeName);

        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Employee Detail"
            , response = Employee.class, tags = "getEmployeeById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "EmployeeId of the employee",
                    required = true,
                    dataType = "Long",
                    paramType = "path"
            )
    })
    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @ApiOperation(value = "Create Employee Detail"
            , response = Employee.class, tags = "createEmployee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeCreateRequestDTO employee) {
        Employee _employee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(_employee, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Employee Detail by EmployeeID"
            , response = Employee.class, tags = "updateEmployee")
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody EmployeeUpdateRequestDTO employee) {
        Employee _employee = employeeService.updateEmployee(id, employee);
        return new ResponseEntity<>(_employee, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Employee Detail by EmployeeID"
            , tags = "deleteEmployee")
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Delete Employee Detail of all Employees"
            , tags = "deleteAllEmployees")
    @DeleteMapping("/deleteAllEmployees")
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        employeeService.deleteAllEmployees();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Find Employee Detail by Employee email"
            , response = Employee.class, tags = "findByEmail")
    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Employee> findByEmail(@PathVariable("email") String email) {
        Employee employee = employeeService.findByEmail(email);

        if (null == employee) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
