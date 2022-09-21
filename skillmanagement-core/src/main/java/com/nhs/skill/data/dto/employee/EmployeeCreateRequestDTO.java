package com.nhs.skill.data.dto.employee;

import com.nhs.skill.data.domain.Skill;
import lombok.*;

import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Getter
public class EmployeeCreateRequestDTO {
    private String employeeName;
    private String email;
    private Set<Skill> skills;
}
