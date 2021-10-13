package Services;

import DataTransferObjects.EmployeeDTO;
import Entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public interface QueryService {
    public EntityManager getEntityManager();
    public List<EmployeeDTO> queryForAllEmployees();
    public List<EmployeeDTO> queryForEmployeesInProject(String projectName);
    public String addEmployeeToProject(Integer employeeId, Integer projectId);
    public List<EmployeeDTO> queryForEmployeesWithRoleNotInProject(String name);
}
