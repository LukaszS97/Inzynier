package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    @Column
    private String email;
    @Column
    private String password;
    private Date registrationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_role_id")
    @JsonIgnore
    private UserRole userRole;
    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Employee employee;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Task> tasks;

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    public User() {
    }

    public User(@NotEmpty @Email String email, @NotEmpty String password, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
