package com.tr.department.usecase.handler;

import com.tr.common.annotation.UseCase;
import com.tr.common.handler.VoidUseCaseHandler;
import com.tr.department.port.DepartmentPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DepartmentDeleteUseCaseHandler implements VoidUseCaseHandler<Long> {

    private final DepartmentPort departmentPort;

    @Override
    public void handle(Long id) {
        departmentPort.delete(id);
    }

}
