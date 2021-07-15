package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Employee employee = new Employee("John Doe");

        entityManager.persist(employee);
        entityManager.getTransaction().commit();


        long id = employee.getId();

        employee = entityManager.find(Employee.class, id);
        System.out.println(employee);


        entityManager.getTransaction().begin();
        employee = entityManager.find(Employee.class, id);
        employee.setName("Jon Jack Doe");
        entityManager.getTransaction().commit();


        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e ORDER BY e.name", Employee.class)
                .getResultList();
        System.out.println(employees);


        entityManager.getTransaction().begin();
        employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
        employees = entityManager.createQuery("SELECT e FROM Employee e ORDER BY e.name", Employee.class)
                .getResultList();
        System.out.println(employees);


        entityManager.close();
        entityManagerFactory.close();
    }
}
