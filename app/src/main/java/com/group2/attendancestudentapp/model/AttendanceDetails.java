package com.group2.attendancestudentapp.model;

import java.util.List;

public class AttendanceDetails {
    String dateStr;
    List<HourDetails> hourDetailsList;

    public AttendanceDetails(String dateStr, List<HourDetails> hourDetailsList) {
        this.dateStr = dateStr;
        this.hourDetailsList = hourDetailsList;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public List<HourDetails> getHourDetailsList() {
        return hourDetailsList;
    }

    public void setHourDetailsList(List<HourDetails> hourDetailsList) {
        this.hourDetailsList = hourDetailsList;
    }
}
