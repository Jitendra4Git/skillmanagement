package com.nhs.skill.data.dto.employee;

import lombok.*;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Getter
public class EmployeeUpdateRequestDTO {
    private String employeeName;
    private String email;
}
