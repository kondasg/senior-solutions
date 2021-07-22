package employees;


import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoTest {

    private EmployeeDao employeeDao;

    @BeforeEach
    void init() throws SQLException {
//        MariaDbDataSource dataSource = new MariaDbDataSource();
//        dataSource.setUrl("jdbc:mariadb://localhost:3307/csxxdg_t360?useUnicode=true");
//        dataSource.setUser("csxxdg_t360");
//        dataSource.setPassword("sWRAiZGCTAGY");

//        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
//
//        flyway.clean();
//        flyway.migrate();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        employeeDao = new EmployeeDao(entityManagerFactory);
    }

    @Test
    void saveTest() {
        Employee employee = new Employee("x", 1L, "John Doe");
        employeeDao.save(employee);

        long id = employee.getId();

        Employee another = employeeDao.findById("x", 1L);
        assertEquals("John Doe", another.getName());
    }

    @Test
    void listtAllTest() {
        employeeDao.save(new Employee("x", 1L, "John Doe"));
        employeeDao.save(new Employee("y", 2L, "Jack Doe"));
        employeeDao.save(new Employee("z", 3L, "Jane Doe"));

        List<Employee> employees = employeeDao.listAll();

        assertEquals(3, employees.size());
        assertEquals("Jack Doe", employees.get(0).getName());
    }

    @Test
    void changeNameTest() {
        Employee employee = new Employee("x", 1L, "John Doe");
        employeeDao.save(employee);

        long id = employee.getId();

        employeeDao.changeName("x", 1L, "Géza");

        Employee another = employeeDao.findById("x", 1L);
        assertEquals("Géza", another.getName());
    }

    @Test
    void deleteTest() {
        Employee employee = new Employee("x", 1L, "John Doe");
        employeeDao.save(employee);

        long id = employee.getId();

        employeeDao.deleteById("x", 1L);

        List<Employee> employees = employeeDao.listAll();
        assertTrue(employees.isEmpty());
    }

    @Test
    void employeeWithAttributesTest() {
        employeeDao.save(new Employee("x", 1L, "John Doe",
                Employee.EmployeeType.FULL_TIME, LocalDate.of(2000, 1, 1)));
        Employee employee = employeeDao.listAll().get(0);
        assertEquals(LocalDate.of(2000, 1, 1), employee.getDateOfBirth());
    }

    @Test
    void testSaveEmployeeChangeState() {
        Employee employee = new Employee("x", 1L, "John Doe");
        employeeDao.save(employee);

        employee.setName("Jack Doe");
        Employee modifiedEmployee = employeeDao.findById("x", 1L);

        assertEquals("John Doe", modifiedEmployee.getName());
        assertFalse(employee == modifiedEmployee);
    }

    @Test
    void testMerge() {
        Employee employee = new Employee("x", 1L, "John Doe");
        employeeDao.save(employee);

        employee.setName("Jack Doe");
        employeeDao.updateEmployee(employee);

        Employee modifiedEmployee = employeeDao.findById("x", 1L);
        assertEquals("Jack Doe", modifiedEmployee.getName());
    }

    @Test
    void testFlush() {
        for (long i = 1; i <= 10; i++) {
            employeeDao.save(new Employee("x", i, "John Doe" + i));
        }
        employeeDao.updateEmpleyeeNames();
    }

    @Test
    void testNickNanes() {
        Employee employee = new Employee("x", 1L, "Jon Doe");
        employee.setNickNames(Set.of("Johny", "J"));
        employeeDao.save(employee);

        Employee anotherEmployee = employeeDao.findByIdWithNickNams(employee.getDepName(), employee.getId());
        assertEquals(Set.of("Johny", "J"), anotherEmployee.getNickNames());
    }

    @Test
    void testVac() {
        Employee employee = new Employee("x", 1L, "John Doe");
        employee.setVacationBookings(Set.of(
                new VacationEntry(LocalDate.of(2021, 1, 1), 10),
                new VacationEntry(LocalDate.of(2021, 3, 11), 4)
        ));
        employeeDao.save(employee);

        Employee anotherEmployee = employeeDao.findByIdWithVacations(employee.getDepName(), employee.getId());
        assertEquals(2, anotherEmployee.getVacationBookings().size());
        System.out.println(anotherEmployee.getVacationBookings());
    }

    @Test
    void testPhone() {
        Employee employee = new Employee("x", 1L, "John Doe");
        employee.setPhoneNumber(Map.of("home", "1234", "work", "4567"));
        employeeDao.save(employee);

        Employee anotherEmployee = employeeDao.findByIdWithPhone(employee.getDepName(), employee.getId());
        assertEquals(2, anotherEmployee.getPhoneNumber().size());
        System.out.println(anotherEmployee.getPhoneNumber());
    }
}