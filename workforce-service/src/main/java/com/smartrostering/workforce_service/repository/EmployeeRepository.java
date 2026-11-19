package com.smartrostering.workforce_service.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.smartrostering.workforce_service.entity.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Long>{ 
    Page<Employee> findByBusinessId(Long businessId, Pageable pageable);
    boolean existsByBusinessIdAndEmailIgnoreCase(Long businessId, String email);
    
}
