package rest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String practice;
    private String domain;
    private String title;
    private String imageFileName;
    private boolean isPracticeLead;
    private boolean isDomainLead;

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isPracticeLead() {
        return isPracticeLead;
    }

    public void setPracticeLead(boolean practiceLead) {
        isPracticeLead = practiceLead;
    }

    public boolean isDomainLead() {
        return isDomainLead;
    }

    public void setDomainLead(boolean domainLead) {
        isDomainLead = domainLead;
    }

    public Employee() {

    }

    public Employee (String firstName, String lastName, String title, String practice, String domain/**, boolean isPracticeLead, boolean isDomainLead**/) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.practice = practice;
        this.domain = domain;
        //this.isPracticeLead = isPracticeLead;
        //this.isDomainLead = isDomainLead;
    }

    @Override
    public String toString() {
        return String.format(
                "Employee[id=%d, firstName='%s', lastName='%s', practice='%s', domain='%s']",
                id, firstName, lastName, practice, domain);
    }
}
