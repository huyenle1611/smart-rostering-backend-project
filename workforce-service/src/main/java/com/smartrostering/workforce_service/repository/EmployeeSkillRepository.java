package com.smartrostering.workforce_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartrostering.workforce_service.entity.EmployeeSkill;
import com.smartrostering.workforce_service.entity.EmployeeSkillId;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, EmployeeSkillId> {
    List<EmployeeSkill> findByIdEmployeeId(Long employeeId);   // traverses id.employeeId
    boolean existsByIdSkillId(Long skillId);
}
