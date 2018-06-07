package cn.zucc.edu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teacher {
    private int teacherid;
    private String teachername;
    private String type;
    private String password;

    @Id
    @Column(name = "teacherid")
    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    @Basic
    @Column(name = "teachername")
    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (teacherid != teacher.teacherid) return false;
        if (teachername != null ? !teachername.equals(teacher.teachername) : teacher.teachername != null) return false;
        if (type != null ? !type.equals(teacher.type) : teacher.type != null) return false;
        if (password != null ? !password.equals(teacher.password) : teacher.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teacherid;
        result = 31 * result + (teachername != null ? teachername.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
