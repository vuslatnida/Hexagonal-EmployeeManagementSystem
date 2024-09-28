package employee.port;

import com.tr.employee.model.*;
import com.tr.employee.port.EmployeePort;
import com.tr.employee.usecase.EmployeeAddUseCase;
import com.tr.employee.usecase.EmployeeUpdateUseCase;

import java.util.List;

public class FakeEmployeePort implements EmployeePort {

    @Override
    public EmployeeAddModel add(EmployeeAddUseCase employeeAddUseCase) {
        return new EmployeeAddModel(1L, employeeAddUseCase.name() + " " + employeeAddUseCase.surname(), employeeAddUseCase.age(), employeeAddUseCase.departmentId(), "DEPARTMENT");
    }

    @Override
    public EmployeeUpdateModel update(EmployeeUpdateUseCase employeeUpdateUseCase) {
        return new EmployeeUpdateModel(employeeUpdateUseCase.id(), employeeUpdateUseCase.name() + " " + employeeUpdateUseCase.surname(),
                employeeUpdateUseCase.age(), employeeUpdateUseCase.departmentId(), "DEPARTMENT");
    }

    @Override
    public EmployeeGetModel get(Long id) {
        return new EmployeeGetModel(id, "FULL-NAME", 30, 1L, "DEPARTMENT");
    }

    @Override
    public List<EmployeeGetAllModel> getAll() {
        return List.of(
                new EmployeeGetAllModel(1L, "FULL-NAME", 30, 1L, "DEPARTMENT"),
                new EmployeeGetAllModel(2L, "NAME - SURNAME", 40, 1L, "DEPARTMENT")
        );
    }

    @Override
    public void delete(Long id) {
        //
    }

    @Override
    public List<EmployeeGetByDepartment> getDepartmentId(Long departmentId) {
        return List.of(
                new EmployeeGetByDepartment(1L, "FULL-NAME", 30),
                new EmployeeGetByDepartment(2L, "NAME - SURNAME", 40)
        );
    }

}
