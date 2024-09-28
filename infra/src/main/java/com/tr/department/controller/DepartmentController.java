package com.tr.department.controller;

import com.tr.common.handler.NoParameterUseCaseHandler;
import com.tr.common.handler.ParameterUseCaseHandler;
import com.tr.common.handler.VoidUseCaseHandler;
import com.tr.department.mapper.DepartmentMapper;
import com.tr.department.model.DepartmentAddModel;
import com.tr.department.model.DepartmentGetAllModel;
import com.tr.department.model.DepartmentGetModel;
import com.tr.department.model.DepartmentUpdateModel;
import com.tr.department.request.DepartmentAddRequest;
import com.tr.department.request.DepartmentUpdateRequest;
import com.tr.department.usecase.DepartmentAddUseCase;
import com.tr.department.usecase.DepartmentUpdateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentMapper departmentMapper;

    private final ParameterUseCaseHandler<DepartmentAddModel, DepartmentAddUseCase> departmentAddUseCaseHandler;
    private final ParameterUseCaseHandler<DepartmentUpdateModel, DepartmentUpdateUseCase> departmentUpdateUseCaseHandler;
    private final ParameterUseCaseHandler<DepartmentGetModel, Long> departmentGetUseCaseHandler;
    private final NoParameterUseCaseHandler<List<DepartmentGetAllModel>> departmentGetAllUseCaseHandler;
    private final VoidUseCaseHandler<Long> departmentDeleteUseCaseHandler;

    @PostMapping
    public DepartmentAddModel add(@Valid @RequestBody DepartmentAddRequest departmentAddRequest) {
        return departmentAddUseCaseHandler.handle(
                departmentMapper.toDepartmentAddUseCase(departmentAddRequest)
        );
    }

    @PutMapping
    public DepartmentUpdateModel update(@RequestParam Long id, @Valid @RequestBody DepartmentUpdateRequest departmentUpdateRequest) {
        return departmentUpdateUseCaseHandler.handle(
                departmentMapper.toDepartmentUpdateUseCase(id, departmentUpdateRequest)
        );
    }

    @GetMapping
    public DepartmentGetModel get(@RequestParam Long id) {
        return departmentGetUseCaseHandler.handle(id);
    }

    @GetMapping("/all")
    public List<DepartmentGetAllModel> getAll() {
        return departmentGetAllUseCaseHandler.handle();
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        departmentDeleteUseCaseHandler.handle(id);
    }

}
