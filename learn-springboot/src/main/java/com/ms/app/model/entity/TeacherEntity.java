package com.ms.app.model.entity;

import com.ms.app.model.embeddable.GeneralInfo;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "teachers")
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "firstname", column = @Column(name = "teacher_firstname", length = 20, nullable = false)),
            @AttributeOverride(name = "lastname", column = @Column(name = "teacher_lastname", length = 10, nullable = false)),
            @AttributeOverride(name = "gender", column = @Column(name = "teacher_gender", length = 10, nullable = false)),
            @AttributeOverride(name = "phone", column = @Column(name = "teacher_phone", length = 15, nullable = false))
    })
    private GeneralInfo generalInfoTeacher;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "firstname", column = @Column(name = "stu_firstname", length = 30)),
            @AttributeOverride(name = "lastname", column = @Column(name = "stu_lastname", length = 20)),
            @AttributeOverride(name = "gender", column = @Column(name = "stu_gender")),
            @AttributeOverride(name = "phone", column = @Column(name = "stu_phone"))
    })
    private GeneralInfo generalInfoStudent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeacherEntity that = (TeacherEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public GeneralInfo getGeneralInfoTeacher() {
        return generalInfoTeacher;
    }

    public void setGeneralInfoTeacher(GeneralInfo generalInfoTeacher) {
        this.generalInfoTeacher = generalInfoTeacher;
    }

    public GeneralInfo getGeneralInfoStudent() {
        return generalInfoStudent;
    }

    public void setGeneralInfoStudent(GeneralInfo generalInfoStudent) {
        this.generalInfoStudent = generalInfoStudent;
    }
}
