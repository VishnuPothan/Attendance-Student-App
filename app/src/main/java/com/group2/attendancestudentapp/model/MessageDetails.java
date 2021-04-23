package com.group2.attendancestudentapp.model;

public class MessageDetails {
    String reqID;
    String reqDate;
    String regStatus;
    String reqMessage;
    String reqSubject;
    String reqHour;
    String reqTeacherID;
    String reqStudentID;

    public MessageDetails(String reqID, String reqDate, String regStatus, String reqMessage, String reqSubject, String reqHour, String reqTeacherID, String reqStudentID) {
        this.reqID = reqID;
        this.reqDate = reqDate;
        this.regStatus = regStatus;
        this.reqMessage = reqMessage;
        this.reqSubject = reqSubject;
        this.reqHour = reqHour;
        this.reqTeacherID = reqTeacherID;
        this.reqStudentID = reqStudentID;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getReqMessage() {
        return reqMessage;
    }

    public void setReqMessage(String reqMessage) {
        this.reqMessage = reqMessage;
    }

    public String getReqSubject() {
        return reqSubject;
    }

    public void setReqSubject(String reqSubject) {
        this.reqSubject = reqSubject;
    }

    public String getReqHour() {
        return reqHour;
    }

    public void setReqHour(String reqHour) {
        this.reqHour = reqHour;
    }

    public String getReqTeacherID() {
        return reqTeacherID;
    }

    public void setReqTeacherID(String reqTeacherID) {
        this.reqTeacherID = reqTeacherID;
    }

    public String getReqStudentID() {
        return reqStudentID;
    }

    public void setReqStudentID(String reqStudentID) {
        this.reqStudentID = reqStudentID;
    }
}
