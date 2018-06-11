package cn.zucc.edu.Dao;

import cn.zucc.edu.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin(origins = "*")
@Repository("CourseDao")
public class CourseDao {
    @Autowired
private SessionFactory sessionFactory;

    //显示所有课程
    public synchronized List loadAllCourse(int teacherid){

        List<Course> list=(List<Course>)sessionFactory.openSession().createQuery("from Course where teacherid="+teacherid).list();
        return list;
    }
//显示teacherid对应的
    public synchronized Course readCourse(int courseid){
        Session session=sessionFactory.openSession();
        Course course=session.get(Course.class,courseid);
        return course;
    }
    //添加课程
    public synchronized void addCourse(Course course){
        // 创建一个 Session 对象
        Session session=sessionFactory.openSession();
        //开启事物管理
        Transaction transaction=session.beginTransaction();
        Course course1=new Course();
        course1.setCourseid(course.getCourseid());
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
