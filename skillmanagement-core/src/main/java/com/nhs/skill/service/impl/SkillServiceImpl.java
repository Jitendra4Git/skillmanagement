package com.nhs.skill.service.impl;

import com.nhs.skill.data.dao.EmployeeRepository;
import com.nhs.skill.data.dao.SkillRepository;
import com.nhs.skill.data.domain.Employee;
import com.nhs.skill.data.domain.Skill;
import com.nhs.skill.data.dto.skill.SkillUpdateRequestDTO;
import com.nhs.skill.exception.ResourceNotFoundException;
import com.nhs.skill.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SkillServiceImpl implements SkillService {

    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(EmployeeRepository employeeRepository
            ,SkillRepository skillRepository
                            ) {
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        List<Skill> skills = new ArrayList<Skill>();
        skillRepository.findAll().forEach(skills::add);
       return skills;
    }

   public List<Skill> getAllSkillsByEmployeeId(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Not found Employee with id = " + employeeId);
        }

        List<Skill> skills = skillRepository.findSkillsByEmployeesId(employeeId);
        return skills;
    }

    public Skill getSkillsById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Skill with id = " + id));

        return skill;
    }

    public List<Employee> getAllEmployeesBySkillId(Long skillId) {
        if (!skillRepository.existsById(skillId)) {
            throw new ResourceNotFoundException("Not found Skill  with id = " + skillId);
        }

        List<Employee> employees = employeeRepository.findEmployeesBySkillsId(skillId);
        return employees;
    }

    public Skill addSkill(Long employeeId,Skill skillRequest) {
        Skill skill = employeeRepository.findById(employeeId).map(employee -> {
            Long skillId = skillRequest.getId();

            // skill is existed
            if (skillId != 0L) {
                Skill _skill = skillRepository.findById(skillId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Skill with id = " + skillId));
                employee.addSkill(_skill);
                employeeRepository.save(employee);
                return _skill;
            }

            // add and create new Skill
            employee.addSkill(skillRequest);
            return skillRepository.save(skillRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Employee with id = " + employeeId));

        return skill;
    }

    public Skill updateSkill(long id, SkillUpdateRequestDTO skillRequest) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SkillId " + id + "not found"));

        skill.setSkill(skillRequest.getSkill());
        skill.setSkillLevel(skillRequest.getSkillLevel());
        return skillRepository.save(skill);
    }

    public void deleteSkillFromEmployee(Long employeeId, Long skillId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Employee with id = " + employeeId));

        employee.removeSkill(skillId);
        employeeRepository.save(employee);

    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);

    }
}
