package department.usecase.handler;

import com.tr.department.model.DepartmentGetModel;
import com.tr.department.usecase.handler.DepartmentGetUseCaseHandler;
import department.port.FakeDepartmentPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DepartmentGetUseCaseHandlerTest {

    @Test
    void get_department() {
        FakeDepartmentPort port = new FakeDepartmentPort();
        DepartmentGetUseCaseHandler handler = new DepartmentGetUseCaseHandler(port);
        Long id = 1L;

        DepartmentGetModel model = handler.handle(id);

        assertNotNull(model);
        assertEquals(id, model.id());
        assertEquals("DEPARTMENT", model.name());
    }

}
