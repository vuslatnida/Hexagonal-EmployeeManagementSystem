package unit.employee;

import com.tr.department.entity.Department;
import com.tr.employee.adapter.EmployeeAdapter;
import com.tr.employee.entity.Employee;
import com.tr.employee.mapper.EmployeeMapper;
import com.tr.employee.model.*;
import com.tr.employee.repository.EmployeeRepository;
import com.tr.employee.usecase.EmployeeAddUseCase;
import com.tr.employee.usecase.EmployeeUpdateUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
class EmployeeAdapterTest {

    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final String FULL_NAME = NAME + " " + SURNAME;
    private static final Integer AGE = 25;
    private static final Department department = new Department(1L, "DEPARTMENT");
    private static final Employee employee = new Employee(NAME, SURNAME, AGE, department);

    @InjectMocks
    private EmployeeAdapter employeeAdapter;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @Test
    void should_add_employee() {
        EmployeeAddUseCase useCase = new EmployeeAddUseCase(NAME, SURNAME, AGE, 1L);
        EmployeeAddModel model = new EmployeeAddModel(1L, FULL_NAME, AGE, department.getId(), department.getName());

        when(employeeMapper.toEntity(useCase)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.toEmployeeAddModel(employee)).thenReturn(model);

        EmployeeAddModel result = employeeAdapter.add(useCase);

        assertNotNull(result);
        assertEquals(model, result);
    }

    @Test
    void should_update_employee() {
        Integer ageUpdate = 26;
        EmployeeUpdateUseCase useCase = new EmployeeUpdateUseCase(1L, NAME, SURNAME, ageUpdate, 1L);
        Employee employeeUpdate = new Employee(NAME, SURNAME, ageUpdate, department);
        EmployeeUpdateModel model = new EmployeeUpdateModel(1L, FULL_NAME, ageUpdate, department.getId(), department.getName());

        when(employeeRepository.findById(useCase.id())).thenReturn(Optional.of(employee));
        when(employeeMapper.partialUpdate(useCase, employee)).thenReturn(employeeUpdate);
        when(employeeRepository.save(employeeUpdate)).thenReturn(employeeUpdate);
        when(employeeMapper.toEmployeeUpdateModel(employeeUpdate)).thenReturn(model);

        EmployeeUpdateModel result = employeeAdapter.update(useCase);

        assertNotNull(result);
        assertEquals(model, result);
    }

    @Test
    void should_get_employee() {
        Long id = 1L;
        EmployeeGetModel model = new EmployeeGetModel(id, FULL_NAME, AGE, department.getId(), department.getName());

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(employeeMapper.toEmployeeGetModel(employee)).thenReturn(model);

        EmployeeGetModel result = employeeAdapter.get(id);

        assertNotNull(result);
        assertEquals(model, result);
    }

    @Test
    void should_get_all_employees() {
        List<Employee> employees = List.of(employee);
        List<EmployeeGetAllModel> models = List.of(new EmployeeGetAllModel(1L, FULL_NAME, AGE, department.getId(), department.getName()));

        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeMapper.toEmployeeGetAllModels(employees)).thenReturn(models);

        List<EmployeeGetAllModel> result = employeeAdapter.getAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.getFirst(), models.getFirst());
    }

    @Test
    void should_delete_employee() {
        Long id = 1L;

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        employeeAdapter.delete(id);

        verify(employeeRepository).findById(id);
        verify(employeeRepository).delete(employee);
    }

    @Test
    void should_get_employees_by_department_id() {
        Long departmentId = 1L;
        List<Employee> employees = List.of(employee);
        List<EmployeeGetByDepartment> models = List.of(new EmployeeGetByDepartment(1L, FULL_NAME, AGE));

        when(employeeRepository.findByDepartmentId(departmentId)).thenReturn(employees);
        when(employeeMapper.toEmployeeGetDepartmentModels(employees)).thenReturn(models);

        List<EmployeeGetByDepartment> result = employeeAdapter.getDepartmentId(departmentId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.getFirst(), models.getFirst());
    }

    @Test
    void should_throw_exception_when_employee_not_found_by_id() {
        Long id = 2L;

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> employeeAdapter.get(id));

        verify(employeeRepository).findById(id);
    }

}
