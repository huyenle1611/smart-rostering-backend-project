package com.smartrostering.workforce_service.controller;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smartrostering.workforce_service.dto.EmployeeRequest;
import com.smartrostering.workforce_service.dto.EmployeeResponse;
import com.smartrostering.workforce_service.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service = service; }

    @PostMapping
    ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest req) {
        EmployeeResponse body = service.create(req);
        return ResponseEntity.created(URI.create("/employees/" + body.id())).body(body);
    }

    @GetMapping("/{id}")
    EmployeeResponse get(@PathVariable Long id) { return service.get(id); }

    @GetMapping
    Page<EmployeeResponse> list(@RequestParam Long businessId, Pageable pageable) {
        return service.list(businessId, pageable);
    }

    @PutMapping("/{id}")
    EmployeeResponse update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) { service.deactivate(id); }
}