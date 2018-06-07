package cn.zucc.edu.controller;

import cn.zucc.edu.Service.CourseService;
import cn.zucc.edu.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;
    //显示所有课程
    @RequestMapping("/LoadAllCourse")
    @ResponseBody
    public List<Course> LoadAllCourse(int teacherid) {
       return courseService.LoadAllCourse(teacherid);
    }
    //显示一门课程
    @RequestMapping("/ReadCourse")
    @ResponseBody
    public  Course ReadCourse(int couserid){
     return courseService.ReadCourse(couserid);
    }
    //添加课程
    @RequestMapping("/AddCourse")
    @ResponseBody
    public String AddCourse(@RequestBody Course course){
        courseService.AddCourse(course);
        return "ok";
    }
    //删除课程
    @RequestMapping("/DelCourse")
    @ResponseBody
    public String DelCourse(int courseid){
        courseService.DelCourse(courseid);
        return "ok";
    }
    //修改课程
    @RequestMapping("/ModifyCourse")
    @ResponseBody
    public String ModifyCourse(@RequestBody Course course){
        courseService.ModifyCourse(course);
        return "ok";
    }
}
