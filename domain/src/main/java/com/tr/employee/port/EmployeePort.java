package com.tr.employee.port;

import com.tr.employee.model.*;
import com.tr.employee.usecase.EmployeeAddUseCase;
import com.tr.employee.usecase.EmployeeUpdateUseCase;

import java.util.List;

public interface EmployeePort {

    EmployeeAddModel add(EmployeeAddUseCase employeeAddUseCase);

    EmployeeUpdateModel update(EmployeeUpdateUseCase employeeUpdateUseCase);

    EmployeeGetModel get(Long id);

    List<EmployeeGetAllModel> getAll();

    void delete(Long id);

    List<EmployeeGetByDepartment> getDepartmentId(Long departmentId);

}
