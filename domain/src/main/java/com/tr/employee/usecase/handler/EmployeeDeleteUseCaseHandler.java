package com.tr.employee.usecase.handler;

import com.tr.common.annotation.UseCase;
import com.tr.common.handler.VoidUseCaseHandler;
import com.tr.employee.port.EmployeePort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EmployeeDeleteUseCaseHandler implements VoidUseCaseHandler<Long> {

    private final EmployeePort employeePort;

    @Override
    public void handle(Long id) {
        employeePort.delete(id);
    }

}
