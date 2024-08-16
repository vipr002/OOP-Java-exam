package org.example;

public class Attendants {

    private int attendantID;
    private int studentID;
    private int staffID;
    private String staffName;
    private String studentName;
    private String guestName;

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getAttendantID() {
        return attendantID;
    }

    public void setAttendantID(int attendantID) {
        this.attendantID = attendantID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Attendants(int attendantID, int studentID, int staffID, String staffName,
                      String studentName, String guestName) {
        this.attendantID = attendantID;
        this.studentID = studentID;
        this.staffID = staffID;
        this.staffName = staffName;
        this.studentName = studentName;
        this.guestName = guestName;
    }

    @Override
    public String toString() {
        return "org.example.Attendants{" +
                "attendantID=" + attendantID +
                ", studentID=" + studentID +
                ", staffID=" + staffID +
                ", staffName='" + staffName + '\'' +
                ", studentName='" + studentName + '\'' +
                ", guestName='" + guestName + '\'' +
                '}';
    }
}
