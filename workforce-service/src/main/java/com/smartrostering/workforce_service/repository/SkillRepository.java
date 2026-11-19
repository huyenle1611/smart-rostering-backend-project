package com.smartrostering.workforce_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartrostering.workforce_service.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    boolean existsByCode(String code);
}
