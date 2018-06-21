package cn.zucc.edu.Dao;

import cn.zucc.edu.entity.Attendance;
import cn.zucc.edu.entity.Attendancedetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin(origins = "*")
@Repository("AttendanceDao")
public class AttendanceDao {
    @Autowired
    private SessionFactory sessionFactory;

    //创建点名,返回attendanceid
    public synchronized int addAttendance(Attendance attendance){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Attendance attendance1=new Attendance();
        attendance1.setAttendancedate(attendance.getAttendancedate());
        attendance1.setCourseid(attendance.getCourseid());
        int attendanceId=(Integer)session.save(attendance1);
        transaction.commit();
        return attendanceId;
    }
    //查看点名

    //添加点名详情
    public synchronized void addAttendanceDetails(Attendancedetails attendancedetails){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Attendancedetails attendancedetails1=new Attendancedetails();
        attendancedetails1.setAttendanceid(attendancedetails.getAttendanceid());
        attendancedetails1.setStudentid(attendancedetails.getStudentid());
        attendancedetails1.setAttendancedetail(attendancedetails.getAttendancedetail());
        session.save(attendancedetails1);
        transaction.commit();
    }
    //修改点名详情
    public synchronized void modifyAttendanceDetails(Attendancedetails attendancedetails){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Attendancedetails attendancedetails1=session.get(Attendancedetails.class,attendancedetails.getNid());
        if(attendancedetails1==null){
            try {
                throw new Exception("不存在此学生");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        attendancedetails1.setAttendancedetail(attendancedetails.getAttendancedetail());
        session.update(attendancedetails1);
        transaction.commit();
    }
    //删除某学生点名详情
    public synchronized void delAttendanceDetails(Attendancedetails attendancedetails){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Attendancedetails attendancedetails1=session.get(Attendancedetails.class,attendancedetails.getNid());
        if(attendancedetails1==null){
            try {
                throw new Exception("不存在此学生");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.delete(attendancedetails1);
        transaction.commit();
    }
    //显示某次点名的详情
    public synchronized List<Attendancedetails> loadAttendanceDetails(int attendanceid){
        return this.sessionFactory.openSession().createQuery("from Attendancedetails where attendanceid="+attendanceid).list();
    }
    //删除某天点名
    public synchronized void delAttendance(int attendanceid ){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Attendance attendance=session.get(Attendance.class,attendanceid);
        if(attendance==null){
            try {
                throw new Exception("此天点名不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String hqlDelete="delete Attendancedetails where attendanceid=:attid";
        session.delete(attendance);
        transaction.commit();
    }
    //load课程下所有点名列表
    public synchronized List<Attendance> loadAttendance(int courseid){
        return this.sessionFactory.openSession().createQuery("from Attendance where courseid="+courseid).list();
    }
}
