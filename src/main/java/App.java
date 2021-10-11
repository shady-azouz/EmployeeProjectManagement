import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {

    public static void main(String[] args) {
        queryForEmployeesByRole(1).toString();
//        addEmployeeToProject(1, 1);
    }

    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        return emf.createEntityManager();
    }

    public static List<?> queryForEmployeesByRole(Integer roleId) {
        EntityManager em = getEntityManager();
        List<?> employees = em.createQuery("SELECT * FROM db_example.Employee e WHERE e.role_id = ?1")
                .setParameter(1, roleId)
                .getResultList();
        return employees;
    }

    public static List<?> queryForEmployeesInProject(String projectName) {
        EntityManager em = getEntityManager();
        List<?> employees = em.createQuery("SELECT * FROM (SELECT * FROM db_example.Project p WHERE p.name = ?1) " +
                "INNER JOIN db_example.employee_project_mapping" +
                "INNER JOIN db_example.Employee")
                .setParameter(1, projectName)
                .getResultList();
        return employees;
    }

    public static void addEmployeeToProject(Integer employeeId, Integer projectId) {
        EntityManager em = getEntityManager();
        em.createQuery("INSERT INTO db_example.employee_project_mapping VALUES (?1, ?2)")
                .setParameter(1, employeeId).setParameter(2, projectId);
    }

    public static List<?> queryForEmployeesNotInProject(Integer projectId) {
        EntityManager em = getEntityManager();
        List<?> employees = em.createQuery("SELECT * FROM db_eample.Employee e" +
                "WHERE NOT EXISTS (" +
                "SELECT 1" +
                "FROM db_example.employee_project_mapping epm" +
                "WHERE db_example.epm.project_id = ?1" +
                ")")
                .setParameter(1, projectId)
                .getResultList();
        return employees;
    }

}
