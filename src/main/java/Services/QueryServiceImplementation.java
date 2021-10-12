package Services;

import Entities.Employee;
import Entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class QueryServiceImplementation implements QueryService {
    @Override
    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        return emf.createEntityManager();
    }

    @Override
    public List<Employee> queryForAllEmployees() {
        EntityManager em = getEntityManager();
        List<Employee> employees = em.createQuery("SELECT e FROM Employee e").getResultList();
        return employees;
    }

    @Override
    public List<?> queryForEmployeesInProject(String projectName) {
        EntityManager em = getEntityManager();
        List<?> employees = em.createQuery("SELECT e " +
                        "FROM Project p " +
                        "JOIN p.employees e " +
                        "WHERE p.name = ?1")
                .setParameter(1, projectName)
                .getResultList();
        return employees;
    }

    @Override
    public void addEmployeeToProject(Integer employeeId, Integer projectId) {
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
        } else {
            System.out.println("Invalid id for Employee AND/OR Project");
        }
    }

    @Override
    public List<?> queryForEmployeesWithRoleNotInProject(String name) {
        EntityManager em = getEntityManager();
        List<?> employees = em.createQuery("SELECT e " +
                        "FROM Role r " +
                        "JOIN r.employees e " +
                        "WHERE r.name = ?1" +
                        "AND " +
                        "e.projects IS EMPTY")
                .setParameter(1, name)
                .getResultList();
        return employees;
    }
}
