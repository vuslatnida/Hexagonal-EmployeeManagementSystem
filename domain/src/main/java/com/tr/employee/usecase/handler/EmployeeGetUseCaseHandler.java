package com.tr.employee.usecase.handler;

import com.tr.common.annotation.UseCase;
import com.tr.common.handler.ParameterUseCaseHandler;
import com.tr.employee.model.EmployeeGetModel;
import com.tr.employee.port.EmployeePort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EmployeeGetUseCaseHandler implements ParameterUseCaseHandler<EmployeeGetModel, Long> {

    private final EmployeePort employeePort;

    @Override
    public EmployeeGetModel handle(Long id) {
        return employeePort.get(id);
    }

}
