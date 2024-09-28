package com.tr.department.port;

import com.tr.department.model.DepartmentAddModel;
import com.tr.department.model.DepartmentGetAllModel;
import com.tr.department.model.DepartmentGetModel;
import com.tr.department.model.DepartmentUpdateModel;
import com.tr.department.usecase.DepartmentAddUseCase;
import com.tr.department.usecase.DepartmentUpdateUseCase;

import java.util.List;

public interface DepartmentPort {

    DepartmentAddModel add(DepartmentAddUseCase departmentAddUseCase);

    DepartmentUpdateModel update(DepartmentUpdateUseCase departmentUpdateUseCase);

    DepartmentGetModel get(Long id);

    List<DepartmentGetAllModel> getAll();

    void delete(Long id);

}
