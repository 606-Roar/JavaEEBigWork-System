package cn.zucc.edu.controller;

import cn.zucc.edu.Service.SelectedCoursesService;
import cn.zucc.edu.entity.Selectedcourses;
import cn.zucc.edu.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SelectedcoursesController {
    @Autowired
    SelectedCoursesService selectedCoursesService;
    //显示所有上课学生
    @RequestMapping("/LoadAllStudent")
    @ResponseBody
    public List<Student> LoadAllStudent(int courseid){
        return selectedCoursesService.LoadAllStudent(courseid);
    }
    //显示某个上课学生
    @RequestMapping("/Selectedcourses")
    @ResponseBody
    public Selectedcourses ReadStudent(int cid){
        return selectedCoursesService.ReadStudent(cid);
    }
    //添加上课学生
    @RequestMapping("/AddClassStudent")
    @ResponseBody
    public String AddClassStudent (@RequestBody Selectedcourses selectedcourses){
        selectedCoursesService.AddClassStudent(selectedcourses);
        return "ok";
    }
    //删除上课学生
    @RequestMapping("/DelStudent")
    @ResponseBody
    public String DelStudent(int cid){
        selectedCoursesService.DelStudent(cid);
        return "ok";
    }
    //修改成绩
    @RequestMapping("/ModifyStudentScore")
    @ResponseBody
    public String ModifyStudentScore(@RequestBody Selectedcourses selectedcourses){
        selectedCoursesService.ModifyStudentScore(selectedcourses);
        return "ok";
    }
    //成绩输入
    @RequestMapping("/AddScore")
    @ResponseBody
    public String AddScore(@RequestBody Selectedcourses selectedcourses){
        selectedCoursesService.AddScore(selectedcourses);
        return "ok";
    }
}
