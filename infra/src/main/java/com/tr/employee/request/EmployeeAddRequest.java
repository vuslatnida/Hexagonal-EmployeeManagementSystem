package com.tr.employee.request;

import jakarta.validation.constraints.NotNull;

public record EmployeeAddRequest(@NotNull String name,
                                 @NotNull String surname,
                                 Integer age,
                                 Long departmentId) {
}
