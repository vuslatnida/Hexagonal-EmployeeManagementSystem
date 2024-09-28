package com.tr.employee.model;

public record EmployeeUpdateModel(Long id,
                                  String fullName,
                                  Integer age,
                                  Long departmentId,
                                  String departmentName) {
}
