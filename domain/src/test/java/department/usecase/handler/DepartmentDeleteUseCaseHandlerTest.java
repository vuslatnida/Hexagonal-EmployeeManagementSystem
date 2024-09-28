package department.usecase.handler;

import com.tr.department.usecase.handler.DepartmentDeleteUseCaseHandler;
import department.port.FakeDepartmentPort;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class DepartmentDeleteUseCaseHandlerTest {

    @Test
    void delete_department() {
        FakeDepartmentPort port = spy(new FakeDepartmentPort());
        DepartmentDeleteUseCaseHandler handler = new DepartmentDeleteUseCaseHandler(port);
        Long id = 1L;

        handler.handle(id);

        verify(port).delete(id);
    }

}
