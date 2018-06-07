package cn.zucc.edu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Homework {
    private int homeworkid;
    private Date homeworkstartdate;
    private int courseid;
    private Date homeworkenddate;

    @Id
    @Column(name = "homeworkid")
    public int getHomeworkid() {
        return homeworkid;
    }

    public void setHomeworkid(int homeworkid) {
        this.homeworkid = homeworkid;
    }

    @Basic
    @Column(name = "homeworkstartdate")
    public Date getHomeworkstartdate() {
        return homeworkstartdate;
    }

    public void setHomeworkstartdate(Date homeworkstartdate) {
        this.homeworkstartdate = homeworkstartdate;
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
    @Column(name = "homeworkenddate")
    public Date getHomeworkenddate() {
        return homeworkenddate;
    }

    public void setHomeworkenddate(Date homeworkenddate) {
        this.homeworkenddate = homeworkenddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Homework homework = (Homework) o;

        if (homeworkid != homework.homeworkid) return false;
        if (courseid != homework.courseid) return false;
        if (homeworkstartdate != null ? !homeworkstartdate.equals(homework.homeworkstartdate) : homework.homeworkstartdate != null)
            return false;
        if (homeworkenddate != null ? !homeworkenddate.equals(homework.homeworkenddate) : homework.homeworkenddate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = homeworkid;
        result = 31 * result + (homeworkstartdate != null ? homeworkstartdate.hashCode() : 0);
        result = 31 * result + courseid;
        result = 31 * result + (homeworkenddate != null ? homeworkenddate.hashCode() : 0);
        return result;
    }
}
