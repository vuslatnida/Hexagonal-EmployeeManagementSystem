package employee.usecase.handler;

import com.tr.employee.model.EmployeeGetByDepartment;
import com.tr.employee.usecase.handler.EmployeeGetByDepartmentUseCaseHandler;
import employee.port.FakeEmployeePort;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EmployeeGetByDepartmentUseCaseHandlerTest {

    @Test
    void get_by_department_employee() {
        FakeEmployeePort port = new FakeEmployeePort();
        EmployeeGetByDepartmentUseCaseHandler handler = new EmployeeGetByDepartmentUseCaseHandler(port);
        Long epartmentId = 1L;

        List<EmployeeGetByDepartment> models = handler.handle(epartmentId);

        assertFalse(models.isEmpty());

        assertEquals(1L, models.getFirst().id());
        assertEquals("FULL-NAME", models.getFirst().fullName());
        assertEquals(30, models.getFirst().age());

        assertEquals(2L, models.getLast().id());
        assertEquals("NAME - SURNAME", models.getLast().fullName());
        assertEquals(40, models.getLast().age());
    }

}
