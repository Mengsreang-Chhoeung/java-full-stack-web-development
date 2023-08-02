package com.ms.app.model;

import com.ms.app.service.TeacherService;

public abstract class Teacher {
    // attributes
    private int id;
    private String name;
    private String subject;

    // Constructors
    public Teacher() {}

    public Teacher(String _name, String _subject) {
        this.id = (int) Math.floor(Math.random() * 10000);
        this.name = _name;
        this.subject = _subject;
    }

    // Methods
    public int getId() {
        return this.id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String _subject) {
        this.subject = _subject;
    }

    public abstract void output();
}
