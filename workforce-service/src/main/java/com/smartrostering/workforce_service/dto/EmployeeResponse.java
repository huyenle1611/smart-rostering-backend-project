package com.smartrostering.workforce_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.smartrostering.workforce_service.entity.Employee;
import com.smartrostering.workforce_service.entity.enums.EmploymentType;

public record EmployeeResponse(
    Long id, Long businessId, String fullName, String email,
    EmploymentType employmentType, LocalDate dateOfBirth, LocalDate startDate,
    BigDecimal baseHourlyRate, Integer maxWeeklyHours, Boolean active,
    OffsetDateTime createAt, OffsetDateTime updateAt) {

    public static EmployeeResponse from(Employee e) {
        return new EmployeeResponse(e.getId(), e.getBusinessId(), e.getFullName(), e.getEmail(),
                e.getEmploymentType(), e.getDateOfBirth(), e.getStartDate(), e.getBaseHourlyRate(),
                e.getMaxWeeklyHours(), e.getActive(), e.getCreatedAt(), e.getUpdatedAt());
    }

}
