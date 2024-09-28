package com.tr.employee.usecase;

public record EmployeeAddUseCase(String name,
                                 String surname,
                                 Integer age,
                                 Long departmentId) {
}
