package com.tr.department.usecase.handler;

import com.tr.common.annotation.UseCase;
import com.tr.common.handler.ParameterUseCaseHandler;
import com.tr.department.model.DepartmentGetModel;
import com.tr.department.port.DepartmentPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DepartmentGetUseCaseHandler implements ParameterUseCaseHandler<DepartmentGetModel, Long> {

    private final DepartmentPort departmentPort;

    @Override
    public DepartmentGetModel handle(Long id) {
        return departmentPort.get(id);
    }

}
