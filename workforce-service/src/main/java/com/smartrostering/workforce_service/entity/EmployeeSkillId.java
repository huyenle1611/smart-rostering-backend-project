package com.smartrostering.workforce_service.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Embeddable 
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode 
public class EmployeeSkillId implements Serializable {
    @Column(name = "employee_id") private long employeeId;
    @Column(name = "skill_id") private long skillId; 
    
}
