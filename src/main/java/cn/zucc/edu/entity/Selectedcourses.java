package cn.zucc.edu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Selectedcourses {
    private int cid;
    private int studentid;
    private int courseid;
    private String midtermresults;
    private String finalgrade;

    @Id
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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
    @Column(name = "courseid")
    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    @Basic
    @Column(name = "midtermresults")
    public String getMidtermresults() {
        return midtermresults;
    }

    public void setMidtermresults(String midtermresults) {
        this.midtermresults = midtermresults;
    }

    @Basic
    @Column(name = "finalgrade")
    public String getFinalgrade() {
        return finalgrade;
    }

    public void setFinalgrade(String finalgrade) {
        this.finalgrade = finalgrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Selectedcourses that = (Selectedcourses) o;

        if (cid != that.cid) return false;
        if (studentid != that.studentid) return false;
        if (courseid != that.courseid) return false;
        if (midtermresults != null ? !midtermresults.equals(that.midtermresults) : that.midtermresults != null)
            return false;
        if (finalgrade != null ? !finalgrade.equals(that.finalgrade) : that.finalgrade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + studentid;
        result = 31 * result + courseid;
        result = 31 * result + (midtermresults != null ? midtermresults.hashCode() : 0);
        result = 31 * result + (finalgrade != null ? finalgrade.hashCode() : 0);
        return result;
    }
}
