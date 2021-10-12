package Services;

import Entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public interface QueryService {
    public EntityManager getEntityManager();
    public List<Employee> queryForAllEmployees();
    public List<?> queryForEmployeesInProject(String projectName);
    public void addEmployeeToProject(Integer employeeId, Integer projectId);
    public List<?> queryForEmployeesWithRoleNotInProject(String name);
}
