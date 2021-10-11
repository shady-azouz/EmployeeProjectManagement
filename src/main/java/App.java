import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {

    public static void main(String[] args) {
        queryForEmployeesByRole(1).toString();
    }

    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.baeldung.movie_catalog");
        return emf.createEntityManager();
    }

    public static List<?> queryForEmployeesByRole(Integer roleId) {
        EntityManager em = getEntityManager();
        List<?> employees = em.createQuery("SELECT e FROM Employee e WHERE e.role_id = ?1")
                .setParameter(1, roleId)
                .getResultList();
        return employees;
    }

    public static List<?> queryForEmployeesInProject(String projectName) {
        EntityManager em = getEntityManager();
        List<?> employees = em.createQuery("SELECT e FROM (SELECT * FROM Project p WHERE p.name = ?1) " +
                "INNER JOIN employee_project_mapping" +
                "INNER JOIN Employee e")
                .setParameter(1, projectName)
                .getResultList();
        return employees;
    }

    public static void addEmployeeToProject(String employeeId, String projectId) {
        EntityManager em = getEntityManager();
        em.createQuery("INSET INTO employee_project_mapping VALUES (?1, ?2)")
                .setParameter(1, employeeId).setParameter(2, projectId);
    }

    public static List<?> queryForEmployeesNotInProject(Integer projectId) {
        EntityManager em = getEntityManager();
        List<?> employees = em.createQuery("SELECT * FROM Employee e" +
                "WHERE NOT EXISTS (" +
                "SELECT 1" +
                "FROM employee-project-mapping epm" +
                "WHERE epm.project_id = ?1" +
                ")")
                .setParameter(1, projectId)
                .getResultList();
        return employees;
    }

}
