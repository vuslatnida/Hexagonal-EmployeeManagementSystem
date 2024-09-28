package com.tr.department.request;

import jakarta.validation.constraints.NotNull;

public record DepartmentAddRequest(@NotNull String name) {
}
