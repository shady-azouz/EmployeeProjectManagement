package Services;

import DataTransferObjects.EmployeeDTO;
import Entities.Employee;
import Entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class QueryServiceImpl implements QueryService {
    @Override
    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        return emf.createEntityManager();
    }

    @Override
    public List<EmployeeDTO> queryForAllEmployees() {
        EntityManager em = getEntityManager();
        List<Employee> employees = em.createQuery("SELECT e FROM Employee e").getResultList();
        List<EmployeeDTO> employeesDTO = new ArrayList<EmployeeDTO>();
        for(final Employee employee : employees){
            employeesDTO.add(new EmployeeDTO(employee));
        }
        return employeesDTO;
    }

    @Override
    public List<EmployeeDTO> queryForEmployeesInProject(String projectName) {
        EntityManager em = getEntityManager();
        List<Employee> employees = em.createQuery("SELECT e " +
                        "FROM Project p " +
                        "JOIN p.employees e " +
                        "WHERE p.name = ?1")
                .setParameter(1, projectName)
                .getResultList();
        List<EmployeeDTO> employeesDTO = new ArrayList<EmployeeDTO>();
        for(final Employee employee : employees){
            employeesDTO.add(new EmployeeDTO(employee));
        }
        return employeesDTO;
    }

    @Override
    public String addEmployeeToProject(Integer employeeId, Integer projectId) {
        EntityManager em = getEntityManager();
        Employee employee = (Employee) em.createQuery("SELECT e FROM Employee e WHERE e.id = ?1")
                .setParameter(1, employeeId)
                .getSingleResult();
        Project project = (Project) em.createQuery("SELECT p FROM Project p WHERE p.id = ?1")
                .setParameter(1, projectId)
                .getSingleResult();
        if(employee != null && project != null){
            em.getTransaction().begin();

            project.getEmployees().add(employee);

            em.persist(project);
            em.getTransaction().commit();
            return "Employee Added To Project";
        } else {
            return "Invalid id for Employee AND/OR Project";
        }
    }

    @Override
    public List<EmployeeDTO> queryForEmployeesWithRoleNotInProject(String name) {
        EntityManager em = getEntityManager();
        List<Employee> employees = em.createQuery("SELECT e " +
                        "FROM Role r " +
                        "JOIN r.employees e " +
                        "WHERE r.name = ?1" +
                        "AND " +
                        "e.projects IS EMPTY")
                .setParameter(1, name)
                .getResultList();
        List<EmployeeDTO> employeesDTO = new ArrayList<EmployeeDTO>();
        for(final Employee employee : employees){
            employeesDTO.add(new EmployeeDTO(employee));
        }
        return employeesDTO;
    }

    @Override
    public List<EmployeeDTO> queryForPaginatedEmployees(int pageIndex, int pageSize) {
        EntityManager em = getEntityManager();
        long employeeCount = (long)em.createQuery("Select count(e.id) from Employee e")
                .getSingleResult();
        int lastPage = (int) ((employeeCount / pageSize) + 1);
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        if(pageIndex<=lastPage) { 
            List<Employee> employees = em.createQuery("From Employee")
                    .setFirstResult((pageIndex-1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
            for(var employee : employees){
                employeeDTOList.add(new EmployeeDTO(employee));
            }
        }
        return employeeDTOList;
    }

    private static EmployeeDTO convertToDto(Employee employee){
        return new EmployeeDTO(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhoneNumber(), employee.getNationalId(), employee.getAge(), employee.getRoleId());
    }
}
