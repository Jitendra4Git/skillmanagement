package com.nhs.skill.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhs.skill.data.type.SkillLevelType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill")
    private String skill;

    @Column(name = "skillLevel")
    @Enumerated(EnumType.STRING)
    private SkillLevelType skillLevel;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "skills")
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();

}
