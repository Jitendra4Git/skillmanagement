package com.nhs.skill.data.type;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Jitendra Gupta
 */

@RequiredArgsConstructor
@Getter
public enum SkillLevelType {

    AWARENESS(
            "AWARENESS"
    ),

    WORKING(
            "WORKING"
    ),

    PRACTITIONER(
            "PRACTITIONER"
    ),

    EXPERT(
            "EXPERT"
    );

    public final String code;
}