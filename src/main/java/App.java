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
        List<?> employees = em.createQuery("SELECT e.id, e.first_name, e.last_name, e.email, e.phone_number, e.national_id, e.age, e.role_id\n" +
                        "FROM db_example.Project p\n" +
                        "JOIN db_example.employee_project_mapping epm\n" +
                        "ON p.id = epm.project_id\n" +
                        "JOIN db_example.Employee e\n" +
                        "ON e.id = epm.employee_id\n" +
                        "WHERE p.name = ?1;")
                .setParameter(1, projectName)
                .getResultList();
        return employees;
    }

    public static void addEmployeeToProject(Integer employeeId, Integer projectId) {
        EntityManager em = getEntityManager();
        em.createQuery("INSERT INTO db_example.employee_project_mapping VALUES (?1, ?2)")
                .setParameter(1, employeeId).setParameter(2, projectId);
    }

    public static List<?> queryForEmployeesWithRoleNotInProject(Integer role_id) {
        EntityManager em = getEntityManager();
        List<?> employees = em.createQuery("SELECT * \n" +
                        "FROM db_example.Employee e\n" +
                        "WHERE \n" +
                        "e.role_id = ?1\n" +
                        "AND NOT EXISTS (\n" +
                        "SELECT 1\n" +
                        "FROM db_example.employee_project_mapping epm\n" +
                        "WHERE epm.employee_id = e.id);")
                .setParameter(1, role_id)
                .getResultList();
        return employees;
    }

}
