package com.tr.department.mapper;

import com.tr.department.entity.Department;
import com.tr.department.model.DepartmentAddModel;
import com.tr.department.model.DepartmentGetAllModel;
import com.tr.department.model.DepartmentGetModel;
import com.tr.department.model.DepartmentUpdateModel;
import com.tr.department.request.DepartmentAddRequest;
import com.tr.department.request.DepartmentUpdateRequest;
import com.tr.department.usecase.DepartmentAddUseCase;
import com.tr.department.usecase.DepartmentUpdateUseCase;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface DepartmentMapper {

    // ADD

    DepartmentAddUseCase toDepartmentAddUseCase(DepartmentAddRequest departmentAddRequest);

    Department toEntity(DepartmentAddUseCase departmentAddUseCase);

    DepartmentAddModel toDepartmentAddModel(Department department);

    // UPDATE

    DepartmentUpdateUseCase toDepartmentUpdateUseCase(Long id, DepartmentUpdateRequest departmentUpdateRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Department partialUpdate(DepartmentUpdateUseCase departmentUpdateUseCase, @MappingTarget Department department);

    DepartmentUpdateModel toDepartmentUpdateModel(Department department);

    // GET

    DepartmentGetModel toDepartmentGetModel(Department department);

    // GET-ALL

    List<DepartmentGetAllModel> toDepartmentGetAllModels(List<Department> departments);

}