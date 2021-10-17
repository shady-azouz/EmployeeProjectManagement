package Services;

import DataTransferObjects.EmployeeDTO;
import Entities.Employee;
import Entities.Role;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class QueryServiceImplTest {
    @Mock
    private EntityManager em;

    private QueryServiceImpl queryService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        queryService = Mockito.spy(new QueryServiceImpl());
        Mockito.when(queryService.getEntityManager()).thenReturn(em);
    }

    @Test
    @Disabled
    EntityManager getEntityManager() {
        return em;
    }

    @Test
    void queryForAllEmployees() {
        Role role = new Role("ASE", "Associate Software Engineer");
        Employee employee = new Employee("Shady", "Azouz", "shady.azouz@gmail.com", "01229339212", "1234", 25, role);
        List<Employee> returnList = Arrays.asList(
                employee
        );
        Query query = Mockito.mock(Query.class);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(returnList);

        List<EmployeeDTO> testList = Arrays.asList(
                new EmployeeDTO(employee)
        );
        Assertions.assertEquals(testList.toString(), queryService.queryForAllEmployees().toString());
    }

    @Test
    void queryForEmployeesInProject() {
        Role role = new Role("ASE", "Associate Software Engineer");
        Employee employee = new Employee("Shady", "Azouz", "shady.azouz@gmail.com", "01229339212", "1234", 25, role);
        List<Employee> returnList = Arrays.asList(
                employee
        );
        Query query = Mockito.mock(Query.class);
        Mockito.when(em.createQuery("SELECT e " +
                        "FROM Project p " +
                        "JOIN p.employees e " +
                        "WHERE p.name = ?1")).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyInt(), Mockito.anyString())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(returnList);

        List<EmployeeDTO> testList = Arrays.asList(
                new EmployeeDTO(employee)
        );
        Assertions.assertEquals(testList.toString(), queryService.queryForEmployeesInProject("Project Name").toString());
    }

    @Test
    void addEmployeeToProject() {
    }

    @Test
    @Disabled
    void queryForEmployeesWithRoleNotInProject() {
    }

    @Test
    @Disabled
    void queryForPaginatedEmployees() {
    }
}