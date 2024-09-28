package com.tr.employee.usecase.handler;

import com.tr.common.annotation.UseCase;
import com.tr.common.handler.NoParameterUseCaseHandler;
import com.tr.employee.model.EmployeeGetAllModel;
import com.tr.employee.port.EmployeePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class EmployeeGetAllUseCaseHandler implements NoParameterUseCaseHandler<List<EmployeeGetAllModel>> {

    private final EmployeePort employeePort;

    @Override
    public List<EmployeeGetAllModel> handle() {
        return employeePort.getAll();
    }

}
