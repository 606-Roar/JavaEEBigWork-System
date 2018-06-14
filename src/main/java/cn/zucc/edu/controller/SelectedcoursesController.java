package cn.zucc.edu.controller;

import cn.zucc.edu.Service.SelectedCoursesService;
import cn.zucc.edu.entity.Course;
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
    //显示某课程的选课信息列表
    @RequestMapping("/LoadAllStudent")
    @ResponseBody
    public List<Student> LoadAllStudent(@RequestBody Course course){
        return selectedCoursesService.LoadAllStudent(course.getCourseid());
    }
    //显示某个上课学生信息
    @RequestMapping("/Selectedcourses")
    @ResponseBody
    public Selectedcourses ReadStudent(@RequestBody Selectedcourses selectedcourses){
        return selectedCoursesService.ReadStudent(selectedcourses.getStudentid());
    }
    //添加某课程的选课信息以及对应的学生信息
    @RequestMapping("/AddClassStudent")
    @ResponseBody
    public String AddClassStudent (@RequestBody List<Selectedcourses> selectedcourses, @RequestBody List<Student> student){
       for (Selectedcourses selectedcourses1:selectedcourses){
        selectedCoursesService.AddClassStudent(selectedcourses1);}
        for (Student student1:student){
        selectedCoursesService.AddStudent(student1);}
        return "ok";
    }
    //删除上课学生
    @RequestMapping("/DelStudent")
    @ResponseBody
    public String DelStudent(@RequestBody List<Student> student){
        for(Student student1:student){
        selectedCoursesService.DelStudent(student1.getSid());}
        return "ok";
    }
    //修改成绩
    @RequestMapping("/ModifyStudentScore")
    @ResponseBody
    public String ModifyStudentScore(@RequestBody List<Selectedcourses> selectedcourses){
        for (Selectedcourses selectedcourses1:selectedcourses){
        selectedCoursesService.ModifyStudentScore(selectedcourses1);}
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
