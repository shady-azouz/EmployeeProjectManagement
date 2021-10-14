package Controllers;

import DataTransferObjects.EmployeeDTO;
import Entities.Role;
import Services.QueryService;
import Services.QueryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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
    void canGetAllEmployees() {
        List<EmployeeDTO> returnList = Arrays.asList(
                new EmployeeDTO(1, "Shady", "Azouz", "shady.azouz@gmail.com", "01229339212", "1234", 25, new Role("ASE", "Associate Software Engineer"))
        );
        Response dummyResponse = Response.ok(returnList).build();
        Mockito.when(queryService.queryForAllEmployees()).thenAnswer(invocationOnMock -> returnList);
        System.out.println("get all employees: "+underTest.getAllEmployees().getEntity());
        System.out.println("Dummy response: "+dummyResponse.getEntity());
        Assertions.assertEquals(dummyResponse.getEntity(), underTest.getAllEmployees().getEntity());
    }

    @Test
    @Disabled
    void getTest() {
        System.out.println("Test Succeeds");
    }

    @Test
    @Disabled
    void getEmployeesInProject() {
    }

    @Test
    @Disabled
    void assignEmployeeToProject() {
    }

    @Test
    @Disabled
    void getEmployeesNotInProject() {
    }

    @Test
    @Disabled
    void getEmployeesPaginated() {
    }

    @Test
    @Disabled
    void getEmployeesPaginatedWithJson() {
    }
}