package com.tr.employee.usecase;

public record EmployeeUpdateUseCase(Long id,
                                    String name,
                                    String surname,
                                    Integer age,
                                    Long departmentId) {
}
