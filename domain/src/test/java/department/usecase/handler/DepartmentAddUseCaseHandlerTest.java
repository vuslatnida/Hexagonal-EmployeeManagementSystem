package department.usecase.handler;

import com.tr.department.model.DepartmentAddModel;
import com.tr.department.usecase.DepartmentAddUseCase;
import com.tr.department.usecase.handler.DepartmentAddUseCaseHandler;
import department.port.FakeDepartmentPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DepartmentAddUseCaseHandlerTest {

    @Test
    void add_department() {
        FakeDepartmentPort port = new FakeDepartmentPort();
        DepartmentAddUseCaseHandler handler = new DepartmentAddUseCaseHandler(port);
        DepartmentAddUseCase useCase = new DepartmentAddUseCase("DEPARTMENT");

        DepartmentAddModel model = handler.handle(useCase);

        assertNotNull(model);
        assertEquals(1L, model.id());
        assertEquals(useCase.name(), model.name());
    }

}
