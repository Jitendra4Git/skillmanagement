package com.nhs.skill.data.dao;

import com.nhs.skill.data.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findSkillsByEmployeesId(Long employeeId);
}
