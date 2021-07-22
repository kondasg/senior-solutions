package employees;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class VacationEntry {

    private LocalDate startDate;
    private  int dayTaken;

    public VacationEntry() {
    }

    public VacationEntry(LocalDate startDate, int dayTaken) {
        this.startDate = startDate;
        this.dayTaken = dayTaken;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDayTaken() {
        return dayTaken;
    }

    public void setDayTaken(int dayTaken) {
        this.dayTaken = dayTaken;
    }

    @Override
    public String toString() {
        return "VacationEntry{" +
                "startDate=" + startDate +
                ", dayTaken=" + dayTaken +
                '}';
    }
}
