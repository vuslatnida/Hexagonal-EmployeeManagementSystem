package com.tr.department.adapter;

import com.tr.department.entity.Department;
import com.tr.department.mapper.DepartmentMapper;
import com.tr.department.model.DepartmentAddModel;
import com.tr.department.model.DepartmentGetAllModel;
import com.tr.department.model.DepartmentGetModel;
import com.tr.department.model.DepartmentUpdateModel;
import com.tr.department.port.DepartmentPort;
import com.tr.department.repository.DepartmentRepository;
import com.tr.department.usecase.DepartmentAddUseCase;
import com.tr.department.usecase.DepartmentUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentAdapter implements DepartmentPort {

    private final DepartmentMapper departmentMapper;

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentAddModel add(DepartmentAddUseCase departmentAddUseCase) {
        Department department = departmentRepository.save(
                departmentMapper.toEntity(departmentAddUseCase)
        );
        return departmentMapper.toDepartmentAddModel(department);
    }

    @Override
    public DepartmentUpdateModel update(DepartmentUpdateUseCase departmentUpdateUseCase) {
        return departmentRepository.findById(departmentUpdateUseCase.id())
                .map(department -> {
                    Department save = departmentRepository.save(
                            departmentMapper.partialUpdate(departmentUpdateUseCase, department)
                    );
                    return departmentMapper.toDepartmentUpdateModel(save);
                })
                .orElseThrow();
    }

    @Override
    public DepartmentGetModel get(Long id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDepartmentGetModel)
                .orElseThrow();
    }

    @Override
    public List<DepartmentGetAllModel> getAll() {
        List<Department> departments = departmentRepository.findAll();
        return departmentMapper.toDepartmentGetAllModels(departments);
    }

    @Override
    public void delete(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow();
        departmentRepository.delete(department);
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

}
