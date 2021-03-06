package app.model;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class VacationForm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isAccepted;
    private boolean isDone;
    private LocalDate timeSendingForm;
    @OneToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public LocalDate getTimeSendingForm() {
        return timeSendingForm;
    }

    public void setTimeSendingForm(LocalDate timeSendingForm) {
        this.timeSendingForm = timeSendingForm;
    }

    public VacationForm() {
    }
}
