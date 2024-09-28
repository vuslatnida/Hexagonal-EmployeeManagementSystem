package com.tr.department.request;

import jakarta.validation.constraints.NotNull;

public record DepartmentUpdateRequest(@NotNull String name) {
}
