package app.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startTask;
    private LocalDate endTask;
    @Column(unique = true)
    private String nameTask;
    private String descriptionTask;
    private boolean isDone;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    public LocalDate getStartTask() {
        return startTask;
    }

    public void setStartTask(LocalDate startTask) {
        this.startTask = startTask;
    }

    public LocalDate getEndTask() {
        return endTask;
    }

    public void setEndTask(LocalDate endTask) {
        this.endTask = endTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }




    public Task() {
    }

}

