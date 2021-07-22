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
    public Employee findByIdWithNickNams(String depName, Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employee employee = entityManager
                .createQuery("SELECT e FROM Employee e JOIN FETCH e.nickNames WHERE e.id = :id AND e.depName = :depName",
                        Employee.class)
                .setParameter("id", id )
                .setParameter("depName", depName )
                .getSingleResult();
        entityManager.close();
        return employee;
    }

    public Employee findByIdWithVacations(String depName, Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employee employee = entityManager
                .createQuery("SELECT e FROM Employee e JOIN FETCH e.vacationBookings WHERE e.id = :id AND e.depName = :depName",
                        Employee.class)
                .setParameter("id", id )
                .setParameter("depName", depName )
                .getSingleResult();
        entityManager.close();
        return employee;
    }

    public Employee findByIdWithPhone(String depName, Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employee employee = entityManager
                .createQuery("SELECT e FROM Employee e JOIN FETCH e.phoneNumber WHERE e.id = :id AND e.depName = :depName",
                        Employee.class)
                .setParameter("id", id )
                .setParameter("depName", depName )
                .getSingleResult();
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

    public void updateEmployee(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee merged = entityManager.merge(employee);

        merged.setName("***" + employee.getName());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateEmpleyeeNames() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e ORDER BY e.name", Employee.class)
                .getResultList();

        entityManager.getTransaction().begin();
        for (Employee employee: employees) {
            employee.setName(employee.getName() + "***");
            System.out.println("Modositva");
            entityManager.flush();
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }



}
