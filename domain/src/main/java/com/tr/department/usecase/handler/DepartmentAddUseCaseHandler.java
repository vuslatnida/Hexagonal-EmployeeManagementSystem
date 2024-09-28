package com.tr.department.usecase.handler;

import com.tr.common.annotation.UseCase;
import com.tr.common.handler.ParameterUseCaseHandler;
import com.tr.department.model.DepartmentAddModel;
import com.tr.department.port.DepartmentPort;
import com.tr.department.usecase.DepartmentAddUseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DepartmentAddUseCaseHandler implements ParameterUseCaseHandler<DepartmentAddModel, DepartmentAddUseCase> {

    private final DepartmentPort departmentPort;

    @Override
    public DepartmentAddModel handle(DepartmentAddUseCase departmentAddUseCase) {
        return departmentPort.add(departmentAddUseCase);
    }

}
