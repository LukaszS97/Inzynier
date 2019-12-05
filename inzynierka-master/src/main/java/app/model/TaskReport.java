package app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TaskReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @OneToOne
    private Task task;


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public TaskReport() {
    }
}
