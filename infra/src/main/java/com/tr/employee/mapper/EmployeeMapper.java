package com.tr.employee.mapper;

import com.tr.department.adapter.DepartmentAdapter;
import com.tr.employee.entity.Employee;
import com.tr.employee.model.*;
import com.tr.employee.request.EmployeeAddRequest;
import com.tr.employee.request.EmployeeUpdateRequest;
import com.tr.employee.usecase.EmployeeAddUseCase;
import com.tr.employee.usecase.EmployeeUpdateUseCase;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                DepartmentAdapter.class
        }
)
public interface EmployeeMapper {

    // ADD

    EmployeeAddUseCase toEmployeeAddUseCase(EmployeeAddRequest employeeAddRequest);

    @Mapping(source = "departmentId", target = "department")
    Employee toEntity(EmployeeAddUseCase employeeAddUseCase);

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(expression = "java(employee.getName() + ' ' + employee.getSurname())", target = "fullName")
    EmployeeAddModel toEmployeeAddModel(Employee employee);

    // UPDATE

    EmployeeUpdateUseCase toEmployeeUpdateUseCase(Long id, EmployeeUpdateRequest employeeUpdateRequest);

    @Mapping(source = "departmentId", target = "department")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Employee partialUpdate(EmployeeUpdateUseCase employeeUpdateUseCase, @MappingTarget Employee employee);

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(expression = "java(employee.getName() + ' ' + employee.getSurname())", target = "fullName")
    EmployeeUpdateModel toEmployeeUpdateModel(Employee employee);

    // GET

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.name", target = "departmentName")
    EmployeeGetModel toEmployeeGetModel(Employee employee);

    // GET-ALL

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.name", target = "departmentName")
    EmployeeGetAllModel toEmployeeGetAllModel(Employee employee);

    List<EmployeeGetAllModel> toEmployeeGetAllModels(List<Employee> employee);

    // GET-BY-DEPARTMENT

    EmployeeGetByDepartment toEmployeeGetDepartmentModel(Employee employee);

    List<EmployeeGetByDepartment> toEmployeeGetDepartmentModels(List<Employee> employee);

}