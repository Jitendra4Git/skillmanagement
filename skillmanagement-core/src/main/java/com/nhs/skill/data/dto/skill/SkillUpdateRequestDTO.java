package com.nhs.skill.data.dto.skill;

import com.nhs.skill.data.type.SkillLevelType;
import lombok.*;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Getter
public class SkillUpdateRequestDTO {
    private String skill;
    private SkillLevelType skillLevel;
}
