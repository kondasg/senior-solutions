package employees;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@IdClass(EmployeeId.class)
public class Employee {

    public enum EmployeeType { FULL_TIME, HALF_TIME }

    @Id
    private String depName;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_name", length = 200, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType = EmployeeType.FULL_TIME;

    private LocalDate dateOfBirth;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String depName, Long id, String name, EmployeeType employeeType, LocalDate dateOfBirth) {
        this.depName = depName;
        this.id = id;
        this.name = name;
        this.employeeType = employeeType;
        this.dateOfBirth = dateOfBirth;
    }

    @PostPersist
    public void debugPersist() {
        System.out.println(name + " " + id);
    }

    public Employee(String depName, Long id, String name) {
        this.depName = depName;
        this.id = id;
        this.name = name;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
