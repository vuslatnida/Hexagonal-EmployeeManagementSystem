package employee.usecase.handler;

import com.tr.employee.model.EmployeeUpdateModel;
import com.tr.employee.usecase.EmployeeUpdateUseCase;
import com.tr.employee.usecase.handler.EmployeeUpdateUseCaseHandler;
import employee.port.FakeEmployeePort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeUpdateUseCaseHandlerTest {

    @Test
    void update_employee() {
        FakeEmployeePort port = new FakeEmployeePort();
        EmployeeUpdateUseCaseHandler handler = new EmployeeUpdateUseCaseHandler(port);
        EmployeeUpdateUseCase useCase = new EmployeeUpdateUseCase(1L, "NAME", "SURNAME", 25, 1L);

        EmployeeUpdateModel model = handler.handle(useCase);

        assertNotNull(model);
        assertEquals(model.id(), useCase.id());
        assertEquals(model.fullName(), useCase.name() + " " + useCase.surname());
        assertEquals(model.age(), useCase.age());
        assertEquals(model.departmentId(), useCase.departmentId());
        assertEquals("DEPARTMENT", model.departmentName());
    }

}
