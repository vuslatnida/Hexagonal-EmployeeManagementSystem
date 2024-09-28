package com.tr.employee.request;

public record EmployeeUpdateRequest(String name,
                                    String surname,
                                    Integer age,
                                    Long departmentId) {
}
