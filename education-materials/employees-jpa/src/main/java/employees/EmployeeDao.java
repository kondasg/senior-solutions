package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EmployeeDao {

    private EntityManagerFactory entityManagerFactory;

    public EmployeeDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void save(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Employee findById(String depName, Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employee employee = entityManager.find(Employee.class, new EmployeeId(depName, id));
        entityManager.close();
        return employee;
    }

    public List<Employee> listAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e ORDER BY e.name", Employee.class)
                .getResultList();
        entityManager.close();
        return employees;
    }

    public void changeName(String depName, long id, String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, new EmployeeId(depName, id));
        employee.setName(name);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteById(String depName, long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.getReference(Employee.class, new EmployeeId(depName, id));
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
