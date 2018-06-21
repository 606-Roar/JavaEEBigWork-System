package cn.zucc.edu.Dao;

import cn.zucc.edu.entity.Course;
import cn.zucc.edu.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
@Repository("StudentDao")
public class StudentDao {
    @Autowired
    SessionFactory sessionFactory;
    //根据studentid查询学生姓名
    public synchronized Student findStudentByID(int studentId)
    {
        List<Student> list=(List<Student>)sessionFactory.openSession().createQuery("from Student where studentid="+studentId).list();
        return list.get(0);
    }
    //添加学生记录
    public synchronized void  addStudent(Student student)
    {
        // 创建一个 Session 对象
        Session session=sessionFactory.openSession();
        //开启事物管理
        Transaction transaction=session.beginTransaction();
        session.save(student);
        transaction.commit();
    }
}
