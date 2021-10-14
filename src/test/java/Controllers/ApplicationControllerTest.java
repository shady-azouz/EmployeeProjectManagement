package Controllers;

import DataTransferObjects.EmployeeDTO;
import DataTransferObjects.JsonPaginatorDTO;
import Entities.Role;
import Services.QueryService;
import Services.QueryServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ApplicationControllerTest {
    @Mock
    private QueryServiceImpl queryService;

    @InjectMocks
    private ApplicationController underTest;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("can get all employees")
    void canGetAllEmployees() {
        List<EmployeeDTO> returnList = Arrays.asList(
                new EmployeeDTO(1, "Shady", "Azouz", "shady.azouz@gmail.com", "01229339212", "1234", 25, new Role("ASE", "Associate Software Engineer"))
        );
        Response dummyResponse = Response.ok(returnList).build();
//        Mockito.when(queryService.queryForAllEmployees()).thenAnswer(invocationOnMock -> returnList);
        Mockito.when(queryService.queryForAllEmployees()).thenReturn(returnList);
        System.out.println("get all employees: "+underTest.getAllEmployees().getEntity());
        System.out.println("Dummy response: "+dummyResponse.getEntity());
        Assertions.assertEquals(dummyResponse.getEntity(), underTest.getAllEmployees().getEntity());
    }

    @Test
    @DisplayName("Can Get Test")
    void canGetTest() {
        String dummyOutput = "test";
        Assertions.assertEquals(dummyOutput, underTest.getTest(dummyOutput));
    }

    @Test
    @DisplayName("can get employees in project")
    void canGetEmployeesInProject() {
        List<EmployeeDTO> returnList = Arrays.asList(
                new EmployeeDTO(1, "Shady", "Azouz", "shady.azouz@gmail.com", "01229339212", "1234", 25, new Role("ASE", "Associate Software Engineer"))
        );
        Response dummyResponse = Response.ok(returnList).build();
        Mockito.when(queryService.queryForEmployeesInProject(Mockito.anyString())).thenReturn(returnList);
        Assertions.assertEquals(dummyResponse.getEntity(), underTest.getEmployeesInProject("testProject").getEntity());
    }

    @Test
    @DisplayName("can assign assign employee to project")
    void assignEmployeeToProject() {
        String dummyString = "success";
        Response dummyResponse = Response.ok(dummyString).build();
        Mockito.when(queryService.addEmployeeToProject(Mockito.anyInt(), Mockito.anyInt())).thenReturn(dummyString);
        Assertions.assertEquals(dummyResponse.getEntity(), underTest.assignEmployeeToProject(1,1).getEntity());
    }

    @Test
    @DisplayName("can get employees not in project")
    void getEmployeesNotInProject() {
        String roleName = "RoleName";
        List<EmployeeDTO> returnList = Arrays.asList(
                new EmployeeDTO(1, "Shady", "Azouz", "shady.azouz@gmail.com", "01229339212", "1234", 25, new Role("ASE", "Associate Software Engineer"))
        );
        Response dummyResponse = Response.ok(returnList).build();
        Mockito.when(queryService.queryForEmployeesWithRoleNotInProject(Mockito.anyString())).thenReturn(returnList);
        Assertions.assertEquals(dummyResponse.getEntity(), underTest.getEmployeesNotInProject(roleName).getEntity());
    }

    @Test
    @DisplayName("can get employees paginated")
    void getEmployeesPaginated() {
        List<EmployeeDTO> returnList = Arrays.asList(
                new EmployeeDTO(1, "Shady", "Azouz", "shady.azouz@gmail.com", "01229339212", "1234", 25, new Role("ASE", "Associate Software Engineer"))
        );
        Response dummyResponse = Response.ok(returnList).build();
        Mockito.when(queryService.queryForPaginatedEmployees(Mockito.anyInt(), Mockito.anyInt())).thenReturn(returnList);
        Assertions.assertEquals(dummyResponse.getEntity(), underTest.getEmployeesPaginated(Mockito.anyInt(), Mockito.anyInt()).getEntity());
    }

    @Test
    @DisplayName("can get employees paginated with json")
    void getEmployeesPaginatedWithJson() {
        List<EmployeeDTO> returnList = Arrays.asList(
                new EmployeeDTO(1, "Shady", "Azouz", "shady.azouz@gmail.com", "01229339212", "1234", 25, new Role("ASE", "Associate Software Engineer"))
        );
        Response dummyResponse = Response.ok(returnList).build();
        Mockito.when(queryService.queryForPaginatedEmployees(Mockito.anyInt(), Mockito.anyInt())).thenReturn(returnList);
        JsonPaginatorDTO jsonPaginatorDTO = new JsonPaginatorDTO(1,1);
        Assertions.assertEquals(dummyResponse.getEntity(), underTest.getEmployeesPaginatedWithJson(jsonPaginatorDTO).getEntity());
    }
}