package cn.zucc.edu;


import cn.zucc.edu.Dao.AttendanceDao;
import cn.zucc.edu.Service.CourseService;
import cn.zucc.edu.entity.Attendance;
import cn.zucc.edu.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


public class Test1 extends BaseTest {
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void test() {

        List<Course> list=(List<Course>)sessionFactory.openSession().createQuery("from Course where teacherid="+123).list();
        for(Course course:list)
        {
            System.out.println(course.getCoursename());
        }
        Session session=sessionFactory.openSession();
        //开启事物管理
        Transaction transaction=session.beginTransaction();
        Course course1=new Course();
        course1.setCoursename("abc");
        course1.setTeacherid(321);
        session.save(course1);
        transaction.commit();
    }

}
