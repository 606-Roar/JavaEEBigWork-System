package cn.zucc.edu.Dao;

import cn.zucc.edu.entity.Selectedcourses;
import cn.zucc.edu.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@CrossOrigin(origins = "*")
@Repository("SelectedcoursesDao")
public class SelectedcoursesDao {
    @Autowired
    private SessionFactory sessionFactory;

    //显示course上课学生
    public synchronized List<Student> loadAllStudent(int courseid){
        return this.sessionFactory.openSession().createQuery("from Selectedcourses WHERE courseid="+courseid).list();
    }
    //显示course的某个学生信息
    public synchronized Selectedcourses readstudent(int studentid ){
        Session session=sessionFactory.openSession();
        Selectedcourses selectedcourses=session.get(Selectedcourses.class,studentid);
        return selectedcourses;
    }
    //添加选课信息
    public synchronized  void addClassStudent (Selectedcourses selectedcourses){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Selectedcourses selectedcourses1=new Selectedcourses();
        selectedcourses1.setStudentid(selectedcourses.getStudentid());
        selectedcourses1.setCourseid(selectedcourses.getCourseid());
        session.save(selectedcourses1);
        transaction.commit();
    }
    //添加学生
    public synchronized void addStudent(Student student){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Student student1=new Student();
        student1.setStudentname(student.getStudentname());
        student1.setStudentid(student.getStudentid());
        session.save(student1);
        transaction.commit();
    }
    //删除上课学生
    public synchronized void delStudent(int cid){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Selectedcourses selectedcourses=session.get(Selectedcourses.class,cid);
        if(selectedcourses==null){
            try {
                throw new Exception("无此学生");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.delete(selectedcourses);
        transaction.commit();
    }
    //修改成绩
    public synchronized void modifyStudentScore(Selectedcourses selectedcourses){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Selectedcourses selectedcourses1=session.get(Selectedcourses.class,selectedcourses.getCid());
        if(selectedcourses1==null){
            try {
                throw new Exception("无此学生");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        selectedcourses1.setFinalgrade(selectedcourses.getFinalgrade());
        selectedcourses1.setMidtermresults(selectedcourses.getMidtermresults());
        session.update(selectedcourses1);
        transaction.commit();
    }

    //成绩输入
   public synchronized void addScore(Selectedcourses selectedcourses){
       Session session=sessionFactory.openSession();
       Transaction transaction=session.beginTransaction();
       Selectedcourses selectedcourses1=session.get(Selectedcourses.class,selectedcourses.getCid());
       if(selectedcourses1==null){
           try {
               throw new Exception("无此学生");
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       selectedcourses1.setMidtermresults(selectedcourses.getMidtermresults());
       selectedcourses1.setFinalgrade(selectedcourses.getFinalgrade());
       session.save(selectedcourses1);
       transaction.commit();
   }
}
