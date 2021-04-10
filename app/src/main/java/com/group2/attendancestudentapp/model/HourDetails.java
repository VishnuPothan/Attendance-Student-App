package com.group2.attendancestudentapp.model;

public class HourDetails {
    String hour;
    String teacherName;
    String subjectName;
    String time;
    Boolean present;

    public HourDetails(String hour, String teacherName, String subjectName, String time, Boolean present) {
        this.hour = hour;
        this.teacherName = teacherName;
        this.subjectName = subjectName;
        this.time = time;
        this.present = present;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
