package cn.zucc.edu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Attendancedetails {
    private int nid;
    private int attendanceid;
    private int studentid;
    private String attendancedetail;

    @Id
    @Column(name = "nid")
    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    @Basic
    @Column(name = "attendanceid")
    public int getAttendanceid() {
        return attendanceid;
    }

    public void setAttendanceid(int attendanceid) {
        this.attendanceid = attendanceid;
    }

    @Basic
    @Column(name = "studentid")
    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    @Basic
    @Column(name = "attendancedetail")
    public String getAttendancedetail() {
        return attendancedetail;
    }

    public void setAttendancedetail(String attendancedetail) {
        this.attendancedetail = attendancedetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendancedetails that = (Attendancedetails) o;

        if (nid != that.nid) return false;
        if (attendanceid != that.attendanceid) return false;
        if (studentid != that.studentid) return false;
        if (attendancedetail != null ? !attendancedetail.equals(that.attendancedetail) : that.attendancedetail != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nid;
        result = 31 * result + attendanceid;
        result = 31 * result + studentid;
        result = 31 * result + (attendancedetail != null ? attendancedetail.hashCode() : 0);
        return result;
    }
}
