package department.usecase.handler;

import com.tr.department.model.DepartmentUpdateModel;
import com.tr.department.usecase.DepartmentUpdateUseCase;
import com.tr.department.usecase.handler.DepartmentUpdateUseCaseHandler;
import department.port.FakeDepartmentPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DepartmentUpdateUseCaseHandlerTest {

    @Test
    void update_department() {
        FakeDepartmentPort port = new FakeDepartmentPort();
        DepartmentUpdateUseCaseHandler handler = new DepartmentUpdateUseCaseHandler(port);
        DepartmentUpdateUseCase useCase = new DepartmentUpdateUseCase(1L, "DEPARTMENT");

        DepartmentUpdateModel model = handler.handle(useCase);

        assertNotNull(model);
        assertEquals(model.id(), useCase.id());
        assertEquals(model.name(), useCase.name());
    }

}
