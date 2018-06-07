package cn.zucc.edu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Attendance {
    private int attendanceid;
    private Date attendancedate;
    private int courseid;

    @Id
    @Column(name = "attendanceid")
    public int getAttendanceid() {
        return attendanceid;
    }

    public void setAttendanceid(int attendanceid) {
        this.attendanceid = attendanceid;
    }

    @Basic
    @Column(name = "attendancedate")
    public Date getAttendancedate() {
        return attendancedate;
    }

    public void setAttendancedate(Date attendancedate) {
        this.attendancedate = attendancedate;
    }

    @Basic
    @Column(name = "courseid")
    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendance that = (Attendance) o;

        if (attendanceid != that.attendanceid) return false;
        if (courseid != that.courseid) return false;
        if (attendancedate != null ? !attendancedate.equals(that.attendancedate) : that.attendancedate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attendanceid;
        result = 31 * result + (attendancedate != null ? attendancedate.hashCode() : 0);
        result = 31 * result + courseid;
        return result;
    }
}
