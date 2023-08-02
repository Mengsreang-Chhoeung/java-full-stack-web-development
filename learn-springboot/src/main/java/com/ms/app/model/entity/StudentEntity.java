package com.ms.app.model.entity;

import com.ms.app.constant.enums.GenderEnum;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "students", schema = "public")
public class StudentEntity {
    @Id
    @Column(name = "stu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stu_firstname", length = 20, nullable = false)
    private String firstname;

    @Column(name = "stu_lastname", length = 10, nullable = false)
    private String lastname;

    @Transient
    private String fullName;

//    @Column(name = "stu_born_date", columnDefinition = "timestamptz")
    @Column(name = "stu_born_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bornDate;

    @Column(name = "stu_gender", length = 10)
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(columnDefinition = "varchar(150) default 'Spring Boot'")
    private String subject;

    public void setId(Long _id) {
        this.id = _id;
    }

    public Long getId() {
        return this.id;
    }

    public void setFirstname(String _firstname) {
        this.firstname = _firstname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setLastname(String _lastname) {
        this.lastname = _lastname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getFullName() {
        this.fullName = this.firstname + " " + this.lastname;
        return this.fullName;
//        return this.firstname + " " + this.lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentEntity that = (StudentEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
