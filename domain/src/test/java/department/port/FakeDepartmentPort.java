package department.port;

import com.tr.department.model.DepartmentAddModel;
import com.tr.department.model.DepartmentGetAllModel;
import com.tr.department.model.DepartmentGetModel;
import com.tr.department.model.DepartmentUpdateModel;
import com.tr.department.port.DepartmentPort;
import com.tr.department.usecase.DepartmentAddUseCase;
import com.tr.department.usecase.DepartmentUpdateUseCase;

import java.util.List;

public class FakeDepartmentPort implements DepartmentPort {

    @Override
    public DepartmentAddModel add(DepartmentAddUseCase departmentAddUseCase) {
        return new DepartmentAddModel(1L, departmentAddUseCase.name());
    }

    @Override
    public DepartmentUpdateModel update(DepartmentUpdateUseCase departmentUpdateUseCase) {
        return new DepartmentUpdateModel(departmentUpdateUseCase.id(), departmentUpdateUseCase.name());
    }

    @Override
    public DepartmentGetModel get(Long id) {
        return new DepartmentGetModel(id, "DEPARTMENT");
    }

    @Override
    public List<DepartmentGetAllModel> getAll() {
        return List.of(
                new DepartmentGetAllModel(1L, "DEPARTMENT - 1"),
                new DepartmentGetAllModel(2L, "DEPARTMENT - 2")
        );
    }

    @Override
    public void delete(Long id) {
        //
    }
}
