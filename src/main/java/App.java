import Entities.Employee;
import Entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {

    public static void main(String[] args) {
        for(var employee : queryForAllEmployees()) {
            System.out.println(employee.toString());
        }
        System.out.println("Query 1 Complete");

        for(var employee : queryForEmployeesInProject("BackEnd Training")) {
            System.out.println(employee.toString());
        }
        System.out.println("Query 2 Complete");

        addEmployeeToProject(2, 1);
        System.out.println("Query 3 Complete");

        for(var employee : queryForEmployeesWithRoleNotInProject("ASE")) {
            System.out.println(employee.toString());
        }
        System.out.println("Query 4 Complete");
//        addEmployeeToProject(1, 1);
    }

    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        return emf.createEntityManager();
    }

    public static List<Employee> queryForAllEmployees() {
        EntityManager em = getEntityManager();
        List<Employee> employees = em.createQuery("SELECT e FROM Employee e").getResultList();
        return employees;
    }

    public static List<?> queryForEmployeesInProject(String projectName) {
        EntityManager em = getEntityManager();
        List<?> employees = em.createQuery("SELECT e " +
                        "FROM Employee e " +
                        "JOIN e.projects p " +
                        "WHERE p.name = ?1")
                .setParameter(1, projectName)
                .getResultList();
        return employees;
    }

    public static void addEmployeeToProject(Integer employeeId, Integer projectId) {
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

    public static List<?> queryForEmployeesWithRoleNotInProject(String name) {
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
