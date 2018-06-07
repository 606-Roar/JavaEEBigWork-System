package cn.zucc.edu.Dao;

import cn.zucc.edu.entity.Attendance;
import cn.zucc.edu.entity.Attendancedetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AttendanceDao")
public class AttendanceDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    //创建点名
    public synchronized void addAttendance(Attendance attendance){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Attendance attendance1=new Attendance();
        attendance1.setAttendancedate(attendance.getAttendancedate());
        attendance1.setCourseid(attendance.getCourseid());
        session.save(attendance1);
        transaction.commit();
    }
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
    //显示某天点名详情
    public synchronized List<Attendancedetails> showAttendanceDetails(int attendanceid){
        return this.sessionFactory.getCurrentSession().createQuery("from Attendancedetails where attendanceid="+attendanceid).list();
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
        //Query query=session.createQuery(hqlDelete).setParameteraattendanceid);
        session.delete(attendance);
        transaction.commit();
    }
    //显示所有点名
    public synchronized List<Attendance> loadAttendance(int attendanceid){
        return this.sessionFactory.getCurrentSession().createQuery("from Attendance where attendanceid="+attendanceid).list();
    }
}
