package app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TaskReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Task task;


}
