package com.tr.employee.model;

public record EmployeeGetAllModel(Long id,
                                  String fullName,
                                  Integer age,
                                  Long departmentId,
                                  String departmentName) {
}
