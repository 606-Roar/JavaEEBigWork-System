package cn.zucc.edu.BackEntity;

public class Backhomeworkdetails {
    private int hid;
    private int homeworkid;
    private int studentid;
    private String homeworkdetail;
    private String studentname;

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getHomeworkid() {
        return homeworkid;
    }

    public void setHomeworkid(int homeworkid) {
        this.homeworkid = homeworkid;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getHomeworkdetail() {
        return homeworkdetail;
    }

    public void setHomeworkdetail(String homeworkdetail) {
        this.homeworkdetail = homeworkdetail;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
}
