package com.smartrostering.workforce_service.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartrostering.workforce_service.dto.EmployeeRequest;
import com.smartrostering.workforce_service.dto.EmployeeResponse;
import com.smartrostering.workforce_service.entity.Employee;
import com.smartrostering.workforce_service.exception.ConflictException;
import com.smartrostering.workforce_service.exception.ResourceNotFoundException;
import com.smartrostering.workforce_service.repository.EmployeeRepository;



@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository repo;
    public EmployeeService(EmployeeRepository repo) { this.repo = repo; }

    public EmployeeResponse create(EmployeeRequest req) {
        if (repo.existsByBusinessIdAndEmailIgnoreCase(req.businessId(), req.email()))
            throw new ConflictException("Email already used in this business");
        Employee e = new Employee();
        apply(e, req);
        return EmployeeResponse.from(repo.save(e));
    }

    @Transactional(readOnly = true)
    public EmployeeResponse get(Long id) { return EmployeeResponse.from(find(id)); }

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> list(Long businessId, Pageable p) {
        return repo.findByBusinessId(businessId, p).map(EmployeeResponse::from);
    }

    public EmployeeResponse update(Long id, EmployeeRequest req) {
        Employee e = find(id);
        if (!e.getEmail().equalsIgnoreCase(req.email())
                && repo.existsByBusinessIdAndEmailIgnoreCase(req.businessId(), req.email()))
            throw new ConflictException("Email already used in this business");
        apply(e, req);
        return EmployeeResponse.from(e);          // dirty checking saves on commit
    }

    public void deactivate(Long id) { find(id).setActive(false); }   // soft delete

    private Employee find(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", id));
    }
    private void apply(Employee e, EmployeeRequest r) {
        e.setBusinessId(r.businessId()); e.setFullName(r.fullName()); e.setEmail(r.email());
        e.setEmploymentType(r.employmentType()); e.setDateOfBirth(r.dateOfBirth());
        e.setStartDate(r.startDate()); e.setBaseHourlyRate(r.baseHourlyRate());
        e.setMaxWeeklyHours(r.maxWeeklyHours());
    }
}