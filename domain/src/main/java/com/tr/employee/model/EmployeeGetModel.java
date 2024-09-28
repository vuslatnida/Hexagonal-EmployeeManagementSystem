package com.tr.employee.model;

public record EmployeeGetModel(Long id,
                               String fullName,
                               Integer age,
                               Long departmentId,
                               String departmentName) {
}
