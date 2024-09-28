package integration.employee;

import com.tr.department.entity.Department;
import com.tr.department.repository.DepartmentRepository;
import com.tr.employee.model.*;
import com.tr.employee.request.EmployeeAddRequest;
import com.tr.employee.request.EmployeeUpdateRequest;
import integration.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeIntegrationTest extends BaseIntegrationTest {

    @Override
    protected String getEndpoint() {
        return "/employee";
    }

    @Autowired
    private DepartmentRepository departmentRepository;

    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final Integer AGE = 25;
    private static final String NAME_UPDATE = "NAME UPDATE";
    private static final String NON_EXISTENT_ID = "1000";

    @BeforeEach
    void setUp() {
        Department department = new Department(2L, "Test Department");
        departmentRepository.save(department);
    }

    @Test
    void should_add_and_update_and_get_and_getAll_and_getByDepartment_and_delete_successfully() {

        // ADD

        EmployeeAddRequest employeeAddRequest = new EmployeeAddRequest(NAME, SURNAME, AGE, 2L);

        var addResponseEntity = restTemplate.exchange(getEndpoint(),
                HttpMethod.POST,
                new HttpEntity<>(employeeAddRequest, header),
                new ParameterizedTypeReference<EmployeeAddModel>() {
                });

        assertEquals(HttpStatus.OK, addResponseEntity.getStatusCode());
        assertNotNull(addResponseEntity.getBody());

        Long id = addResponseEntity.getBody().id();
        String endPoint = getEndpoint().concat("?id=").concat(id.toString());

        // UPDATE

        EmployeeUpdateRequest employeeUpdateRequest = new EmployeeUpdateRequest(NAME_UPDATE, SURNAME, AGE, 2L);

        var updateResponseEntity = restTemplate.exchange(endPoint,
                HttpMethod.PUT,
                new HttpEntity<>(employeeUpdateRequest, header),
                new ParameterizedTypeReference<EmployeeUpdateModel>() {
                });

        assertEquals(HttpStatus.OK, updateResponseEntity.getStatusCode());
        assertNotNull(updateResponseEntity.getBody());

        // GET

        var getResponseEntity = restTemplate.exchange(endPoint,
                HttpMethod.GET,
                new HttpEntity<>(header),
                new ParameterizedTypeReference<EmployeeGetModel>() {
                });

        assertEquals(HttpStatus.OK, getResponseEntity.getStatusCode());
        assertNotNull(getResponseEntity.getBody());

        // GET-ALL

        String getAllEndPoint = getEndpoint().concat("/all");

        var getAllResponseEntity = restTemplate.exchange(getAllEndPoint,
                HttpMethod.GET,
                new HttpEntity<>(header),
                new ParameterizedTypeReference<List<EmployeeGetAllModel>>() {
                });

        assertEquals(HttpStatus.OK, getAllResponseEntity.getStatusCode());
        assertNotNull(getAllResponseEntity.getBody());

        // GET-BY-DEPARTMENT

        String getByDepartmentEndPoint = getEndpoint().concat("/by-department?departmentId=2");

        var getByDepartmentResponseEntity = restTemplate.exchange(getByDepartmentEndPoint,
                HttpMethod.GET,
                new HttpEntity<>(header),
                new ParameterizedTypeReference<List<EmployeeGetByDepartment>>() {
                });

        assertEquals(HttpStatus.OK, getByDepartmentResponseEntity.getStatusCode());
        assertNotNull(getByDepartmentResponseEntity.getBody());

        // DELETE

        var deleteResponseEntity = restTemplate.exchange(endPoint,
                HttpMethod.DELETE,
                new HttpEntity<>(header),
                new ParameterizedTypeReference<Void>() {
                });

        assertEquals(HttpStatus.OK, deleteResponseEntity.getStatusCode());
        assertNull(deleteResponseEntity.getBody());
    }

    @Test
    void should_throw_exception_when_add_employee_with_null_parameter() {
        EmployeeAddRequest request = new EmployeeAddRequest(NAME, null, AGE, 2L);

        var responseEntity = restTemplate.exchange(getEndpoint(),
                HttpMethod.POST,
                new HttpEntity<>(request, header),
                new ParameterizedTypeReference<EmployeeAddModel>() {
                });

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void should_throw_exception_when_update_employee_not_found() {
        String endPoint = getEndpoint().concat("?id=").concat(NON_EXISTENT_ID);
        EmployeeUpdateRequest request = new EmployeeUpdateRequest(NAME_UPDATE, SURNAME, AGE, 2L);

        var responseEntity = restTemplate.exchange(endPoint,
                HttpMethod.PUT,
                new HttpEntity<>(request, header),
                new ParameterizedTypeReference<EmployeeUpdateModel>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void should_throw_exception_when_get_employee_not_found() {
        String endPoint = getEndpoint().concat("?id=").concat(NON_EXISTENT_ID);

        var responseEntity = restTemplate.exchange(endPoint,
                HttpMethod.GET,
                new HttpEntity<>(header),
                new ParameterizedTypeReference<EmployeeGetModel>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void should_delete_employee_not_found() {
        String endPoint = getEndpoint().concat("?id=").concat(NON_EXISTENT_ID);

        var responseEntity = restTemplate.exchange(endPoint,
                HttpMethod.DELETE,
                new HttpEntity<>(header),
                new ParameterizedTypeReference<Void>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

}