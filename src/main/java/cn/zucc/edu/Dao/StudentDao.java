package cn.zucc.edu.Dao;

import cn.zucc.edu.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@Repository("StudentDao")
public class StudentDao {
    @Autowired
    SessionFactory sessionFactory;
    //根据studentid查询学生姓名
    public synchronized Student findStudentByID(int studentId)
    {
        Session session=sessionFactory.openSession();
        Student student=session.get(Student.class,studentId);
        return student;
    }
}
