package com.smartrostering.workforce_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.smartrostering.workforce_service.entity.enums.EmploymentType;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EmployeeRequest(
    @NotNull @Positive Long businessId,
    @NotBlank @Size(max = 100) String fullName,
    @NotBlank @Email @Size(max = 255) String email,
    @NotNull EmploymentType employmentType,
    @PastOrPresent LocalDate dateOfBirth,
    @NotNull LocalDate startDate,
    @NotNull @Positive @Digits(integer = 8, fraction = 2) BigDecimal baseHourlyRate,
    @NotNull @Min(1) @Max(60) Integer maxWeeklyHours

) {}
