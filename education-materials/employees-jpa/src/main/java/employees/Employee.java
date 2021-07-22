package employees;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

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

    @ElementCollection
    @CollectionTable(name = "nicknames", joinColumns = {@JoinColumn(name = "depName"), @JoinColumn(name = "id")})
    @Column(name = "nicknames")
    private Set<String> nickNames;

    @ElementCollection
    @CollectionTable(name = "vacations", joinColumns = {@JoinColumn(name = "depName"), @JoinColumn(name = "id")})
    @AttributeOverride(name = "startDate", column = @Column(name = "start_date"))
    @AttributeOverride(name = "dayTaken", column = @Column(name = "daya"))
    private Set<VacationEntry> vacationBookings;

    @ElementCollection
    @CollectionTable(name = "phone", joinColumns = {@JoinColumn(name = "depName"), @JoinColumn(name = "id")})
    @MapKeyColumn(name = "phone_type")
    @Column(name = "phone_number")
    private Map<String, String> phoneNumber;

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

    public Employee(String depName, Long id, String name) {
        this.depName = depName;
        this.id = id;
        this.name = name;
    }

    @PostPersist
    public void debugPersist() {
        //System.out.println(name + " " + id);
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

    public Set<String> getNickNames() {
        return nickNames;
    }

    public void setNickNames(Set<String> nickNames) {
        this.nickNames = nickNames;
    }

    public Set<VacationEntry> getVacationBookings() {
        return vacationBookings;
    }

    public void setVacationBookings(Set<VacationEntry> vacationBookings) {
        this.vacationBookings = vacationBookings;
    }

    public Map<String, String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Map<String, String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
