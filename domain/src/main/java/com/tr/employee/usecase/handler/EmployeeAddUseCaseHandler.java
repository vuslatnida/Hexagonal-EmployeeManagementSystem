package com.tr.employee.usecase.handler;

import com.tr.common.annotation.UseCase;
import com.tr.common.handler.ParameterUseCaseHandler;
import com.tr.employee.model.EmployeeAddModel;
import com.tr.employee.port.EmployeePort;
import com.tr.employee.usecase.EmployeeAddUseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EmployeeAddUseCaseHandler implements ParameterUseCaseHandler<EmployeeAddModel, EmployeeAddUseCase> {

    private final EmployeePort employeePort;

    @Override
    public EmployeeAddModel handle(EmployeeAddUseCase employeeAddUseCase) {
        return employeePort.add(employeeAddUseCase);
    }

}
