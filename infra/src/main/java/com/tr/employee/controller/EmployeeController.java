package com.tr.employee.controller;

import com.tr.common.handler.NoParameterUseCaseHandler;
import com.tr.common.handler.ParameterUseCaseHandler;
import com.tr.common.handler.VoidUseCaseHandler;
import com.tr.employee.mapper.EmployeeMapper;
import com.tr.employee.model.*;
import com.tr.employee.request.EmployeeAddRequest;
import com.tr.employee.request.EmployeeUpdateRequest;
import com.tr.employee.usecase.EmployeeAddUseCase;
import com.tr.employee.usecase.EmployeeUpdateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeMapper employeeMapper;

    private final ParameterUseCaseHandler<EmployeeAddModel, EmployeeAddUseCase> employeeAddUseCaseHandler;
    private final ParameterUseCaseHandler<EmployeeUpdateModel, EmployeeUpdateUseCase> employeeUpdateUseCaseHandler;
    private final ParameterUseCaseHandler<EmployeeGetModel, Long> employeeGetUseCaseHandler;
    private final NoParameterUseCaseHandler<List<EmployeeGetAllModel>> employeeGetAllUseCaseHandler;
    private final VoidUseCaseHandler<Long> employeeDeleteUseCaseHandler;
    private final ParameterUseCaseHandler<List<EmployeeGetByDepartment>, Long> employeeGetDepartmentUseCaseHandler;

    @PostMapping
    public EmployeeAddModel add(@Valid @RequestBody EmployeeAddRequest employeeAddRequest) {
        return employeeAddUseCaseHandler.handle(
                employeeMapper.toEmployeeAddUseCase(employeeAddRequest)
        );
    }

    @PutMapping
    public EmployeeUpdateModel update(@RequestParam Long id, @Valid @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        return employeeUpdateUseCaseHandler.handle(
                employeeMapper.toEmployeeUpdateUseCase(id, employeeUpdateRequest)
        );
    }

    @GetMapping
    public EmployeeGetModel get(@RequestParam Long id) {
        return employeeGetUseCaseHandler.handle(id);
    }

    @GetMapping("/all")
    public List<EmployeeGetAllModel> getAll() {
        return employeeGetAllUseCaseHandler.handle();
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        employeeDeleteUseCaseHandler.handle(id);
    }

    @GetMapping("/by-department")
    public List<EmployeeGetByDepartment> getByDepartment(@RequestParam Long departmentId) {
        return employeeGetDepartmentUseCaseHandler.handle(departmentId);
    }

}
