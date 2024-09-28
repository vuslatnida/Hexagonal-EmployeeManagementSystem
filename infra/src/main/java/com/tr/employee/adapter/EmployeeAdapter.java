package com.tr.employee.adapter;

import com.tr.employee.entity.Employee;
import com.tr.employee.mapper.EmployeeMapper;
import com.tr.employee.model.*;
import com.tr.employee.port.EmployeePort;
import com.tr.employee.repository.EmployeeRepository;
import com.tr.employee.usecase.EmployeeAddUseCase;
import com.tr.employee.usecase.EmployeeUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeAdapter implements EmployeePort {

    private final EmployeeMapper employeeMapper;

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeAddModel add(EmployeeAddUseCase employeeAddUseCase) {
        Employee employee = employeeRepository.save(
                employeeMapper.toEntity(employeeAddUseCase)
        );
        return employeeMapper.toEmployeeAddModel(employee);
    }

    @Override
    public EmployeeUpdateModel update(EmployeeUpdateUseCase employeeUpdateUseCase) {
        return employeeRepository.findById(employeeUpdateUseCase.id())
                .map(employee -> {
                    Employee save = employeeRepository.save(
                            employeeMapper.partialUpdate(employeeUpdateUseCase, employee)
                    );
                    return employeeMapper.toEmployeeUpdateModel(save);
                })
                .orElseThrow();
    }

    @Override
    public EmployeeGetModel get(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toEmployeeGetModel)
                .orElseThrow();
    }

    @Override
    public List<EmployeeGetAllModel> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toEmployeeGetAllModels(employees);
    }

    @Override
    public void delete(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeGetByDepartment> getDepartmentId(Long departmentId) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return employeeMapper.toEmployeeGetDepartmentModels(employees);
    }

}
