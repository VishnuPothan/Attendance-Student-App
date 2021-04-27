package com.group2.attendancestudentapp.model;

public class TeacherDetailsModel {
    String phone;
    String subject;
    String name;
    String ID;

    public TeacherDetailsModel() {
    }

    public TeacherDetailsModel(String phone, String subject, String name, String ID) {
        this.phone = phone;
        this.subject = subject;
        this.name = name;
        this.ID = ID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
