package cn.zucc.edu.BackEntity;

import java.sql.Date;

public class Backattendancedetails {
    private int studentid;
    private String studentname;
    private String attendancedetail;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getAttendancedetail() {
        return attendancedetail;
    }

    public void setAttendancedetail(String attendancedetail) {
        this.attendancedetail = attendancedetail;
    }
}
