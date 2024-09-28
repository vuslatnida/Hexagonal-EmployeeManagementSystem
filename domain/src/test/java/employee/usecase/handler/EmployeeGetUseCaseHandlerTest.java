package employee.usecase.handler;

import com.tr.employee.model.EmployeeGetModel;
import com.tr.employee.usecase.handler.EmployeeGetUseCaseHandler;
import employee.port.FakeEmployeePort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeGetUseCaseHandlerTest {

    @Test
    void get_employee() {
        FakeEmployeePort port = new FakeEmployeePort();
        EmployeeGetUseCaseHandler handler = new EmployeeGetUseCaseHandler(port);
        Long id = 1L;

        EmployeeGetModel model = handler.handle(id);

        assertNotNull(model);
        assertEquals(id, model.id());
        assertEquals("FULL-NAME", model.fullName());
        assertEquals(30, model.age());
        assertEquals(1L, model.departmentId());
        assertEquals("DEPARTMENT", model.departmentName());
    }

}
