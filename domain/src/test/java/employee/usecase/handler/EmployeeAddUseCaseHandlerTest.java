package employee.usecase.handler;

import com.tr.employee.model.EmployeeAddModel;
import com.tr.employee.usecase.EmployeeAddUseCase;
import com.tr.employee.usecase.handler.EmployeeAddUseCaseHandler;
import employee.port.FakeEmployeePort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeAddUseCaseHandlerTest {

    @Test
    void add_employee() {
        FakeEmployeePort port = new FakeEmployeePort();
        EmployeeAddUseCaseHandler handler = new EmployeeAddUseCaseHandler(port);
        EmployeeAddUseCase useCase = new EmployeeAddUseCase("NAME", "SURNAME", 20, 1L);

        EmployeeAddModel model = handler.handle(useCase);

        assertNotNull(model);
        assertEquals(1L, model.id());
        assertEquals(useCase.name() + " " + useCase.surname(), model.fullName());
        assertEquals(useCase.age(), model.age());
        assertEquals(useCase.departmentId(), model.departmentId());
    }

}
