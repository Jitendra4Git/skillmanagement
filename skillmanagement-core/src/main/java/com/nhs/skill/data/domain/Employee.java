package com.nhs.skill.data.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "employeeName")
    private String employeeName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "employee_skills",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private Set<Skill> skills = new HashSet<>();

    public Employee(String email, String employeeName) {
        this.email = email;
        this.employeeName = employeeName;
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
        skill.getEmployees().add(this);
    }

    public void removeSkill(Long skillId) {
        Skill skill = this.skills.stream().filter(t -> t.getId() == skillId).findFirst().orElse(null);
        if (skill != null) {
            this.skills.remove(skill);
            skill.getEmployees().remove(this);
        }
    }

}
