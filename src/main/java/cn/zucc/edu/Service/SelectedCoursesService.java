package cn.zucc.edu.Service;

import cn.zucc.edu.Dao.SelectedcoursesDao;
import cn.zucc.edu.entity.Selectedcourses;
import cn.zucc.edu.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SelectedCoursesService {
    @Autowired
    SelectedcoursesDao selectedcoursesDao;
    //显示所有上课学生
    public List<Student> LoadAllStudent(int courseid){
        return selectedcoursesDao.loadallStudent(courseid);
    }
    //显示上课学生
    public Selectedcourses ReadStudent(int cid){
        return selectedcoursesDao.readstudent(cid);
    }
    //添加上课学生
    public void AddClassStudent (Selectedcourses selectedcourses){
        selectedcoursesDao.addClassStudent(selectedcourses);
    }
    //删除上课学生
    public void DelStudent(int cid){
        selectedcoursesDao.delStudent(cid);
    }
    //修改成绩
    public void ModifyStudentScore(Selectedcourses selectedcourses){
        selectedcoursesDao.modifyStudentScore(selectedcourses);
    }
    //成绩输入
    public void AddScore(Selectedcourses selectedcourses){
        selectedcoursesDao.addScore(selectedcourses);
    }
}
