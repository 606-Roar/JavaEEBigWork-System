package cn.zucc.edu.Service;

import cn.zucc.edu.Dao.StudentDao;
import cn.zucc.edu.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;
    public Student findStudentByID(int studentid){return studentDao.findStudentByID(studentid);}
}