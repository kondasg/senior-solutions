package employees;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeId implements Serializable {

    private String depName;
    private Long id;

    public EmployeeId() {
    }

    public EmployeeId(String depName, Long id) {
        this.depName = depName;
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeId that = (EmployeeId) o;
        return id == that.id && Objects.equals(depName, that.depName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depName, id);
    }
}
