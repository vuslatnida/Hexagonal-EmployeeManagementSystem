package integration.department;

import com.tr.department.model.DepartmentAddModel;
import com.tr.department.model.DepartmentGetAllModel;
import com.tr.department.model.DepartmentGetModel;
import com.tr.department.model.DepartmentUpdateModel;
import com.tr.department.request.DepartmentAddRequest;
import com.tr.department.request.DepartmentUpdateRequest;
import integration.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentIntegrationTest extends BaseIntegrationTest {

    @Override
    protected String getEndpoint() {
        return "/department";
    }

    private static final String NAME = "DEPARTMENT";
    private static final String NAME_UPDATE = "DEPARTMENT_UPDATE";
    private static final String NON_EXISTENT_ID = "1000";

    @Test
    void should_add_and_update_and_get_and_getAll_and_delete_successfully() {

        // ADD

        DepartmentAddRequest departmentAddRequest = new DepartmentAddRequest(NAME);

        var addResponseEntity = restTemplate.exchange(getEndpoint(),
                HttpMethod.POST,
                new HttpEntity<>(departmentAddRequest, header),
                new ParameterizedTypeReference<DepartmentAddModel>() {
                });

        assertEquals(HttpStatus.OK, addResponseEntity.getStatusCode());
        assertNotNull(addResponseEntity.getBody());

        Long id = addResponseEntity.getBody().id();
        String endPoint = getEndpoint().concat("?id=").concat(id.toString());

        // UPDATE

        DepartmentUpdateRequest departmentUpdateRequest = new DepartmentUpdateRequest(NAME_UPDATE);

        var updateResponseEntity = restTemplate.exchange(endPoint,
                HttpMethod.PUT,
                new HttpEntity<>(departmentUpdateRequest, header),
                new ParameterizedTypeReference<DepartmentUpdateModel>() {
                });

        assertEquals(HttpStatus.OK, updateResponseEntity.getStatusCode());
        assertNotNull(updateResponseEntity.getBody());

        // GET

        var getResponseEntity = restTemplate.exchange(endPoint,
                HttpMethod.GET,
                new HttpEntity<>(header),
                new ParameterizedTypeReference<DepartmentGetModel>() {
                });

        assertEquals(HttpStatus.OK, getResponseEntity.getStatusCode());
        assertNotNull(getResponseEntity.getBody());

        // GET-ALL

        String getAllEndPoint = getEndpoint().concat("/all");

        var getAllResponseEntity = restTemplate.exchange(getAllEndPoint,
                HttpMethod.GET,
                new HttpEntity<>(header),
                new ParameterizedTypeReference<List<DepartmentGetAllModel>>() {
                });

        assertEquals(HttpStatus.OK, getAllResponseEntity.getStatusCode());
        assertNotNull(getAllResponseEntity.getBody());

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
    void should_throw_exception_when_add_department_with_null_parameter() {
        DepartmentAddRequest request = new DepartmentAddRequest(null);

        var responseEntity = restTemplate.exchange(getEndpoint(),
                HttpMethod.POST,
                new HttpEntity<>(request, header),
                new ParameterizedTypeReference<DepartmentAddModel>() {
                });

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void should_throw_exception_when_update_department_not_found() {
        String endPoint = getEndpoint().concat("?id=").concat(NON_EXISTENT_ID);
        DepartmentUpdateRequest request = new DepartmentUpdateRequest(NAME_UPDATE);

        var responseEntity = restTemplate.exchange(endPoint,
                HttpMethod.PUT,
                new HttpEntity<>(request, header),
                new ParameterizedTypeReference<DepartmentUpdateModel>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void should_throw_exception_when_update_department_with_null_parameter() {
        String endPoint = getEndpoint().concat("?id=1");
        DepartmentUpdateRequest request = new DepartmentUpdateRequest(null);

        var responseEntity = restTemplate.exchange(endPoint,
                HttpMethod.PUT,
                new HttpEntity<>(request, header),
                new ParameterizedTypeReference<DepartmentUpdateModel>() {
                });

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void should_throw_exception_when_get_department_not_found() {
        String endPoint = getEndpoint().concat("?id=").concat(NON_EXISTENT_ID);

        var responseEntity = restTemplate.exchange(endPoint,
                HttpMethod.GET,
                new HttpEntity<>(header),
                new ParameterizedTypeReference<DepartmentGetModel>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void should_delete_department_not_found() {
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