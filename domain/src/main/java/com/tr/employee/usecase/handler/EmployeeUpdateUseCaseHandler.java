package com.tr.employee.usecase.handler;

import com.tr.common.annotation.UseCase;
import com.tr.common.handler.ParameterUseCaseHandler;
import com.tr.employee.model.EmployeeUpdateModel;
import com.tr.employee.port.EmployeePort;
import com.tr.employee.usecase.EmployeeUpdateUseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EmployeeUpdateUseCaseHandler implements ParameterUseCaseHandler<EmployeeUpdateModel, EmployeeUpdateUseCase> {

    private final EmployeePort employeePort;

    @Override
    public EmployeeUpdateModel handle(EmployeeUpdateUseCase employeeUpdateUseCase) {
        return employeePort.update(employeeUpdateUseCase);
    }

}
