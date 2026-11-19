package com.smartrostering.workforce_service.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_skill")
@Getter @Setter @NoArgsConstructor 
public class EmployeeSkill {
    @EmbeddedId private EmployeeSkillId id;

    @MapsId("employeeId") @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "employee_id")
    private Employee employee;
    
    @MapsId("skillId") @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "skill_id")
    private Skill skill;

    @Column(nullable = false) 
    private Integer level;
    
    public EmployeeSkill(Employee employee, Skill skill, int level) {
        this.id = new EmployeeSkillId(employee.getId(), skill.getId());
        this.employee = employee;
        this.skill = skill;
        this.level = level;
    }
}
