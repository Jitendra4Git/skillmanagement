package com.nhs.skill.service;

import com.nhs.skill.data.domain.Employee;
import com.nhs.skill.data.domain.Skill;
import com.nhs.skill.data.dto.skill.SkillUpdateRequestDTO;

import java.util.List;


public interface SkillService {

    List<Skill> getAllSkills();

    List<Skill> getAllSkillsByEmployeeId(Long employeeId);

    Skill getSkillsById(Long id);

    List<Employee> getAllEmployeesBySkillId(Long skillId);

    Skill addSkill(Long employeeId, Skill skillRequest);

    Skill updateSkill(long id, SkillUpdateRequestDTO skillRequest);

    void deleteSkillFromEmployee(Long employeeId, Long skillId);

    void deleteSkill(Long id);
}
