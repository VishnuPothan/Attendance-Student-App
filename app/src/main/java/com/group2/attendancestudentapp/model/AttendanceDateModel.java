package com.group2.attendancestudentapp.model;

import java.util.List;

public class AttendanceDateModel {
    String dateStr;
    List<AttendanceSubjectModel> attendanceSubjectModelList;

    public AttendanceDateModel() {
    }

    public AttendanceDateModel(String dateStr, List<AttendanceSubjectModel> attendanceSubjectModelList) {
        this.dateStr = dateStr;
        this.attendanceSubjectModelList = attendanceSubjectModelList;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public List<AttendanceSubjectModel> getAttendanceSubjectModelList() {
        return attendanceSubjectModelList;
    }

    public void setAttendanceSubjectModelList(List<AttendanceSubjectModel> attendanceSubjectModelList) {
        this.attendanceSubjectModelList = attendanceSubjectModelList;
    }
}
