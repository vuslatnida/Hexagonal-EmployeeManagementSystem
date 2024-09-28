package com.tr.department.usecase.handler;

import com.tr.common.annotation.UseCase;
import com.tr.common.handler.NoParameterUseCaseHandler;
import com.tr.department.model.DepartmentGetAllModel;
import com.tr.department.port.DepartmentPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class DepartmentGetAllUseCaseHandler implements NoParameterUseCaseHandler<List<DepartmentGetAllModel>> {

    private final DepartmentPort departmentPort;

    @Override
    public List<DepartmentGetAllModel> handle() {
        return departmentPort.getAll();
    }

}
