package com.tr.department.usecase.handler;

import com.tr.common.annotation.UseCase;
import com.tr.common.handler.ParameterUseCaseHandler;
import com.tr.department.model.DepartmentUpdateModel;
import com.tr.department.port.DepartmentPort;
import com.tr.department.usecase.DepartmentUpdateUseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DepartmentUpdateUseCaseHandler implements ParameterUseCaseHandler<DepartmentUpdateModel, DepartmentUpdateUseCase> {

    private final DepartmentPort departmentPort;

    @Override
    public DepartmentUpdateModel handle(DepartmentUpdateUseCase departmentUpdateUseCase) {
        return departmentPort.update(departmentUpdateUseCase);
    }

}
