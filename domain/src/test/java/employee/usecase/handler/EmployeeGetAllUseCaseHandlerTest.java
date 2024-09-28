package employee.usecase.handler;

import com.tr.employee.model.EmployeeGetAllModel;
import com.tr.employee.usecase.handler.EmployeeGetAllUseCaseHandler;
import employee.port.FakeEmployeePort;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EmployeeGetAllUseCaseHandlerTest {

    @Test
    void get_all_employee() {
        FakeEmployeePort port = new FakeEmployeePort();
        EmployeeGetAllUseCaseHandler handler = new EmployeeGetAllUseCaseHandler(port);

        List<EmployeeGetAllModel> models = handler.handle();

        assertFalse(models.isEmpty());

        assertEquals(1L, models.getFirst().id());
        assertEquals("FULL-NAME", models.getFirst().fullName());
        assertEquals(30, models.getFirst().age());
        assertEquals(1L, models.getFirst().departmentId());
        assertEquals("DEPARTMENT", models.getFirst().departmentName());

        assertEquals(2L, models.getLast().id());
        assertEquals("NAME - SURNAME", models.getLast().fullName());
        assertEquals(40, models.getLast().age());
        assertEquals(1L, models.getLast().departmentId());
        assertEquals("DEPARTMENT", models.getLast().departmentName());
    }

}
