package cn.zucc.edu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Homeworkdetails {
    private int hid;
    private int homeworkid;
    private int studentid;
    private String homeworkdetail;

    @Id
    @Column(name = "hid")
    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    @Basic
    @Column(name = "homeworkid")
    public int getHomeworkid() {
        return homeworkid;
    }

    public void setHomeworkid(int homeworkid) {
        this.homeworkid = homeworkid;
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
    @Column(name = "homeworkdetail")
    public String getHomeworkdetail() {
        return homeworkdetail;
    }

    public void setHomeworkdetail(String homeworkdetail) {
        this.homeworkdetail = homeworkdetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Homeworkdetails that = (Homeworkdetails) o;

        if (hid != that.hid) return false;
        if (homeworkid != that.homeworkid) return false;
        if (studentid != that.studentid) return false;
        if (homeworkdetail != null ? !homeworkdetail.equals(that.homeworkdetail) : that.homeworkdetail != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hid;
        result = 31 * result + homeworkid;
        result = 31 * result + studentid;
        result = 31 * result + (homeworkdetail != null ? homeworkdetail.hashCode() : 0);
        return result;
    }
}
