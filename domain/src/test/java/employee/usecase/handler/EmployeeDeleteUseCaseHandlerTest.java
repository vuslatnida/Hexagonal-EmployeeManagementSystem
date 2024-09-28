package employee.usecase.handler;

import com.tr.employee.usecase.handler.EmployeeDeleteUseCaseHandler;
import employee.port.FakeEmployeePort;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class EmployeeDeleteUseCaseHandlerTest {

    @Test
    void delete_employee() {
        FakeEmployeePort port = spy(new FakeEmployeePort());
        EmployeeDeleteUseCaseHandler handler = new EmployeeDeleteUseCaseHandler(port);
        Long id = 1L;

        handler.handle(id);

        verify(port).delete(id);
    }

}
