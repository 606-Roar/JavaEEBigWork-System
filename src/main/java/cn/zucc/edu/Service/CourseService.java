package cn.zucc.edu.Service;

import cn.zucc.edu.Dao.CourseDao;
import cn.zucc.edu.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;
    //显示所有课程
    public List<Course> LoadAllCourse(int teacherid){
        return courseDao.loadAllCourse(teacherid);
    }
    //显示一门课程
    public  Course ReadCourse(int couserid){
        return courseDao.readCourse(couserid);
    }
    //添加课程
    public void AddCourse(Course course){
        courseDao.addCourse(course);
    }
    //删除课程
    public  void DelCourse(int courseid){
        courseDao.delCourse(courseid);
    }
    //修改课程
    public void ModifyCourse(Course course){
        courseDao.modifyCourse(course);
    }
}
