package com.nhs.skill.data.dao;

import com.nhs.skill.data.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAll();

    List<Employee> findByEmployeeNameContaining(String name);

    Employee findByEmail(String email);

    List<Employee> findEmployeesBySkillsId(Long skillId);

}
