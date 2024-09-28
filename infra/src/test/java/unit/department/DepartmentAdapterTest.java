package unit.department;

import com.tr.department.adapter.DepartmentAdapter;
import com.tr.department.entity.Department;
import com.tr.department.mapper.DepartmentMapper;
import com.tr.department.model.DepartmentAddModel;
import com.tr.department.model.DepartmentGetAllModel;
import com.tr.department.model.DepartmentGetModel;
import com.tr.department.model.DepartmentUpdateModel;
import com.tr.department.repository.DepartmentRepository;
import com.tr.department.usecase.DepartmentAddUseCase;
import com.tr.department.usecase.DepartmentUpdateUseCase;
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
class DepartmentAdapterTest {

    private static final String NAME = "DEPARTMENT";
    private static final Department department = new Department(NAME);

    @InjectMocks
    private DepartmentAdapter departmentAdapter;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapper departmentMapper;

    @Test
    void should_add_department() {
        DepartmentAddUseCase useCase = new DepartmentAddUseCase(NAME);
        DepartmentAddModel model = new DepartmentAddModel(1L, NAME);

        when(departmentMapper.toEntity(useCase)).thenReturn(department);
        when(departmentRepository.save(department)).thenReturn(department);
        when(departmentMapper.toDepartmentAddModel(department)).thenReturn(model);

        DepartmentAddModel result = departmentAdapter.add(useCase);

        assertNotNull(result);
        assertEquals(model, result);
    }

    @Test
    void should_update_department() {
        String nameUpdate = "DEPARTMENT_UPDATE";
        DepartmentUpdateUseCase useCase = new DepartmentUpdateUseCase(1L, nameUpdate);
        Department departmentUpdate = new Department(nameUpdate);
        DepartmentUpdateModel model = new DepartmentUpdateModel(1L, nameUpdate);

        when(departmentRepository.findById(useCase.id())).thenReturn(Optional.of(department));
        when(departmentMapper.partialUpdate(useCase, department)).thenReturn(departmentUpdate);
        when(departmentRepository.save(departmentUpdate)).thenReturn(departmentUpdate);
        when(departmentMapper.toDepartmentUpdateModel(departmentUpdate)).thenReturn(model);

        DepartmentUpdateModel result = departmentAdapter.update(useCase);

        assertNotNull(result);
        assertEquals(model, result);
    }

    @Test
    void should_get_department() {
        Long id = 1L;
        DepartmentGetModel model = new DepartmentGetModel(id, NAME);

        when(departmentRepository.findById(id)).thenReturn(Optional.of(department));
        when(departmentMapper.toDepartmentGetModel(department)).thenReturn(model);

        DepartmentGetModel result = departmentAdapter.get(id);

        assertNotNull(result);
        assertEquals(model, result);
    }

    @Test
    void should_get_all_departments() {
        List<Department> departments = List.of(department);
        List<DepartmentGetAllModel> models = List.of(new DepartmentGetAllModel(1L, NAME));

        when(departmentRepository.findAll()).thenReturn(departments);
        when(departmentMapper.toDepartmentGetAllModels(departments)).thenReturn(models);

        List<DepartmentGetAllModel> result = departmentAdapter.getAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.getFirst(), models.getFirst());
    }

    @Test
    void should_delete_department() {
        Long id = 1L;

        when(departmentRepository.findById(id)).thenReturn(Optional.of(department));

        departmentAdapter.delete(id);

        verify(departmentRepository).findById(id);
        verify(departmentRepository).delete(department);
    }

    @Test
    void should_throw_exception_when_department_not_found_by_id() {
        Long id = 2L;

        when(departmentRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> departmentAdapter.get(id));

        verify(departmentRepository).findById(id);
    }

}
