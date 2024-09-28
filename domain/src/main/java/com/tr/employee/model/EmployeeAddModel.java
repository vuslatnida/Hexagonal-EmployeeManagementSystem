package com.tr.employee.model;

public record EmployeeAddModel(Long id,
                               String fullName,
                               Integer age,
                               Long departmentId,
                               String departmentName) {
}
