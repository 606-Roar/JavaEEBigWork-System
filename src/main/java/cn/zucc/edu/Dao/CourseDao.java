package cn.zucc.edu.Dao;

import cn.zucc.edu.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("CourseDao")
public class CourseDao {
private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    //显示所有课程
    public synchronized List loadAllCourse(int teacherid){
        return this.sessionFactory.getCurrentSession().createQuery("from Course where teacherid="+teacherid).list();
    }
    public synchronized Course readCourse(int couserid){
        Session session=sessionFactory.openSession();
        Course course=session.get(Course.class,couserid);
        return course;
    }
    //添加课程
    public synchronized void addCourse(Course course){
        // 创建一个 Session 对象
        Session session=sessionFactory.openSession();
        //开启事物管理
        Transaction transaction=session.beginTransaction();
        Course course1=new Course();
        course1.setCoursename(course.getCoursename());
        course1.setTeacherid(course.getTeacherid());
        session.save(course1);
        transaction.commit();
    }
    //删除课程
    public synchronized void delCourse(int courseid){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Course course=session.get(Course.class,courseid);
        if(course==null){
            try {
                throw new Exception("此课程不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.delete(course);
        transaction.commit();
    }
    //修改课程
    public synchronized void modifyCourse(Course course){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Course course1=session.get(Course.class,course.getCourseid());
        if(course1==null){
            try {
                throw new Exception("此课程不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        course1.setCoursename(course.getCoursename());
        session.update(course1);
        transaction.commit();
    }

}
