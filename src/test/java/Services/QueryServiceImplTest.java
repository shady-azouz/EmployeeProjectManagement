package Services;

import DataTransferObjects.EmployeeDTO;
import Entities.Employee;
import Entities.Project;
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
import java.util.Date;
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
        Role role = new Role("ASE", "Associate Software Engineer");
        Employee employee = new Employee("Shady", "Azouz", "shady.azouz@gmail.com", "01229339212", "1234", 25, role);
        List<Employee> employeeReturnList = Arrays.asList(
                employee
        );

        Query employeeQuery = Mockito.mock(Query.class);
        Mockito.when(em.createQuery("SELECT e FROM Employee e WHERE e.id = ?1")).thenReturn(employeeQuery);
        Mockito.when(employeeQuery.setParameter(Mockito.anyInt(), Mockito.anyString())).thenReturn(employeeQuery);
        Mockito.when(employeeQuery.getSingleResult()).thenReturn(employee);

        java.sql.Date dummyDate = new java.sql.Date(2021/10/10);
        Project project = new Project("DummyProject", dummyDate, employee);
        List<Project> projectReturnList = Arrays.asList(
                project
        );
        Query projectQuery = Mockito.mock(Query.class);
        Mockito.when(em.createQuery("SELECT p FROM Project p WHERE p.id = ?1")).thenReturn(projectQuery);
        Mockito.when(projectQuery.setParameter(Mockito.anyInt(), Mockito.anyString())).thenReturn(projectQuery);
        Mockito.when(projectQuery.getSingleResult()).thenReturn(project);

        Mockito.doNothing().when(em).getTransaction().begin();
        Mockito.doNothing().when(em).persist(Mockito.any());
        Mockito.doNothing().when(em).getTransaction().commit();

        List<EmployeeDTO> testList = Arrays.asList(
                new EmployeeDTO(employee)
        );
        Assertions.assertEquals("Employee Added To Project", queryService.addEmployeeToProject(1,1));
    }

    @Test
    void queryForEmployeesWithRoleNotInProject() {
        Role role = new Role("ASE", "Associate Software Engineer");
        Employee employee = new Employee("Shady", "Azouz", "shady.azouz@gmail.com", "01229339212", "1234", 25, role);
        List<Employee> returnList = Arrays.asList(
                employee
        );
        Query query = Mockito.mock(Query.class);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyInt(), Mockito.anyString())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(returnList);

        List<EmployeeDTO> testList = Arrays.asList(
                new EmployeeDTO(employee)
        );
        Assertions.assertEquals(testList.toString(), queryService.queryForEmployeesWithRoleNotInProject("Role Name").toString());
    }

    @Test
    void queryForPaginatedEmployees() {
        Query query = Mockito.mock(Query.class);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.getSingleResult()).thenReturn(Mockito.anyLong());

        Mockito.when(query.setFirstResult(Mockito.anyInt())).thenReturn(query);
        Mockito.when(query.setMaxResults(Mockito.anyInt())).thenReturn(query);

        Role role = new Role("ASE", "Associate Software Engineer");
        Employee employee = new Employee("Shady", "Azouz", "shady.azouz@gmail.com", "01229339212", "1234", 25, role);
        List<Employee> returnList = Arrays.asList(
                employee
        );
        Mockito.when(query.getResultList()).thenReturn(returnList);

        List<EmployeeDTO> testList = Arrays.asList(
                new EmployeeDTO(employee)
        );
        Assertions.assertEquals(testList.toString(), queryService.queryForPaginatedEmployees(Mockito.anyInt(), Mockito.anyInt()).toString());
    }
}