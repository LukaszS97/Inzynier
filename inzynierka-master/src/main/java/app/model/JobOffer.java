package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class JobOffer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;
    private String jobDescription;
    private String requirements;
    private String niceToHave;
    private String conditions;
    @OneToMany(mappedBy = "jobOffer",
            cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ApplicationForm> applicationForms;

    public JobOffer() {}

    public Long getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getNiceToHave() {
        return niceToHave;
    }

    public void setNiceToHave(String niceToHave) {
        this.niceToHave = niceToHave;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public List<ApplicationForm> getApplicationForms() {
        return applicationForms;
    }

    public void setApplicationForms(List<ApplicationForm> applicationForms) {
        this.applicationForms = applicationForms;
    }


    @Override
    public String toString() {
        return "JobOffer{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", requirements='" + requirements + '\'' +
                ", niceToHave='" + niceToHave + '\'' +
                ", conditions='" + conditions + '\'' +
                '}';
    }
}
