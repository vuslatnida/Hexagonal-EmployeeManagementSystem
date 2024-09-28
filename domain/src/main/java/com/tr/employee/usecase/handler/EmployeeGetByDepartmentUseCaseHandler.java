package com.tr.employee.usecase.handler;

import com.tr.common.annotation.UseCase;
import com.tr.common.handler.ParameterUseCaseHandler;
import com.tr.employee.model.EmployeeGetByDepartment;
import com.tr.employee.port.EmployeePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class EmployeeGetByDepartmentUseCaseHandler implements ParameterUseCaseHandler<List<EmployeeGetByDepartment>, Long> {

    private final EmployeePort employeePort;

    @Override
    public List<EmployeeGetByDepartment> handle(Long departmentId) {
        return employeePort.getDepartmentId(departmentId);
    }

}
