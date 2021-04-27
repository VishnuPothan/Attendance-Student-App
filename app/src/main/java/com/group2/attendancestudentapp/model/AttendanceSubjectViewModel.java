package com.group2.attendancestudentapp.model;

public class AttendanceSubjectViewModel {
    String subjectName;
    int attendedHours;
    int totalHour;
    double percentage;

    public AttendanceSubjectViewModel() {
    }

    public AttendanceSubjectViewModel(String subjectName, int attendedHours, int totalHour, double percentage) {
        this.subjectName = subjectName;
        this.attendedHours = attendedHours;
        this.totalHour = totalHour;
        this.percentage = percentage;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getAttendedHours() {
        return attendedHours;
    }

    public void setAttendedHours(int attendedHours) {
        this.attendedHours = attendedHours;
    }

    public int getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(int totalHour) {
        this.totalHour = totalHour;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
