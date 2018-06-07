package cn.zucc.edu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
    private int courseid;
    private int teacherid;
    private String coursename;

    @Id
    @Column(name = "courseid")
    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    @Basic
    @Column(name = "teacherid")
    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    @Basic
    @Column(name = "coursename")
    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (courseid != course.courseid) return false;
        if (teacherid != course.teacherid) return false;
        if (coursename != null ? !coursename.equals(course.coursename) : course.coursename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = courseid;
        result = 31 * result + teacherid;
        result = 31 * result + (coursename != null ? coursename.hashCode() : 0);
        return result;
    }
}
