package cn.zucc.edu.Service;

import cn.zucc.edu.Dao.TeacherDao;
import cn.zucc.edu.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherService {
    @Autowired
    TeacherDao teacherDao;
    //显示老师列表
    public  List<Teacher> LoadAllTeacher(){
        return teacherDao.loadAllTeacher();
    }
    //通过id查看老师
    public  Teacher ReadTeacher(int teacherid){
        return teacherDao.readTeacher(teacherid);
    }
    //添加老师
    public void AddTeacher(Teacher teacher){
        teacherDao.addTeacher(teacher);
    }
    //删除老师
    public void DelTeacher(int teacherid ){
        teacherDao.delTeacher(teacherid);
    }
    //修改老师信息
    public void ModifyTeacher(Teacher teacher){
        teacherDao.modifyTeacher(teacher);
    }
    //删除所有老师信息
    public  void  DelAllTeacher(){teacherDao.delAllTeacher();}
}
