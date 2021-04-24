package com.group2.attendancestudentapp.model;


public class AttendanceSubjectModel {
    String subjectUniqueStr;
    String ID;
    String name;
    String subject;
    String time;
    String teacherID;
    String attendanceMark;

    public AttendanceSubjectModel(String subjectUniqueStr, String ID, String name, String subject, String time, String teacherID, String attendanceMark) {
        this.subjectUniqueStr = subjectUniqueStr;
        this.ID = ID;
        this.name = name;
        this.subject = subject;
        this.time = time;
        this.teacherID = teacherID;
        this.attendanceMark = attendanceMark;
    }

    public String getSubjectUniqueStr() {
        return subjectUniqueStr;
    }

    public void setSubjectUniqueStr(String subjectUniqueStr) {
        this.subjectUniqueStr = subjectUniqueStr;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getAttendanceMark() {
        return attendanceMark;
    }

    public void setAttendanceMark(String attendanceMark) {
        this.attendanceMark = attendanceMark;
    }
}

