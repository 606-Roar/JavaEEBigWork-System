package cn.zucc.edu.controller;

import cn.zucc.edu.Service.CourseService;
import cn.zucc.edu.Util.MyResponse;
import cn.zucc.edu.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;
    //显示所有课程
    @RequestMapping(value = "/LoadAllCourse",method = RequestMethod.POST)
    @ResponseBody
    public List<Course> LoadAllCourse(@RequestParam String teacherId)
    {
       return courseService.LoadAllCourse(Integer.valueOf(teacherId).intValue());
    }
    //显示一门课程
    @RequestMapping("/ReadCourse")
    @ResponseBody
    public  Course ReadCourse(int couserid){
     return courseService.ReadCourse(couserid);
    }
    //添加课程
    @RequestMapping(value = "/AddCourse",method = RequestMethod.POST)
    @ResponseBody
        public MyResponse<Course> AddCourse(@RequestBody Course course){
            MyResponse<Course> myResponse=new MyResponse<Course>();
            myResponse.setMyBody(course);
            Course course1=courseService.ReadCourse(course.getCourseid());
            if(course1!=null)
            {
                myResponse.setMeesage("该课程已存在");
            }
            else{
                courseService.AddCourse(course);
                myResponse.setCode(1);
                myResponse.setMeesage("添加完成");
            }
        return myResponse;
    }
    //删除课程
    @RequestMapping("/DelCourse")
    @ResponseBody
    public String DelCourse(int courseid){
        MyResponse myResponse=new MyResponse();
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
