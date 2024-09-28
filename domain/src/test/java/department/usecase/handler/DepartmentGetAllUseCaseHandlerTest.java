package department.usecase.handler;

import com.tr.department.model.DepartmentGetAllModel;
import com.tr.department.usecase.handler.DepartmentGetAllUseCaseHandler;
import department.port.FakeDepartmentPort;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DepartmentGetAllUseCaseHandlerTest {

    @Test
    void get_all_department() {
        FakeDepartmentPort port = new FakeDepartmentPort();
        DepartmentGetAllUseCaseHandler handler = new DepartmentGetAllUseCaseHandler(port);

        List<DepartmentGetAllModel> models = handler.handle();

        assertFalse(models.isEmpty());
        assertEquals(1L, models.getFirst().id());
        assertEquals("DEPARTMENT - 1", models.getFirst().name());

        assertEquals(2L, models.getLast().id());
        assertEquals("DEPARTMENT - 2", models.getLast().name());
    }

}
