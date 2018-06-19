package cn.zucc.edu.controller;

import cn.zucc.edu.Service.CourseService;
import cn.zucc.edu.Util.MyResponse;
import cn.zucc.edu.entity.Course;
import cn.zucc.edu.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller()
public class CourseController {
    @Autowired
    CourseService courseService;
    //显示某老师的所有课程
    @RequestMapping(value = "/LoadAllCourse",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse<List<Course>> LoadAllCourse(@RequestBody Teacher teacher)
    {
       MyResponse<List<Course>> myResponse =new MyResponse<List<Course>>();
        myResponse.setMyBody(courseService.LoadAllCourse(Integer.valueOf(teacher.getTeacherid()).intValue()));
        System.out.println();
        myResponse.setCode(1);
        return myResponse;
    }
    //显示某一门课程的
    @RequestMapping("/ReadCourse")
    @ResponseBody
    public Course ReadCourse(@RequestBody Course course){
     return courseService.ReadCourse(course.getCourseid());
    }
    //添加课程
    //返回课程列表,coursename
    @RequestMapping(value = "/AddCourse",method = RequestMethod.POST)
    @ResponseBody
        public MyResponse<List<Course>> AddCourse(@RequestBody List<Course> course){
            MyResponse<List<Course>> myResponse=new MyResponse<List<Course>>();
            for (Course course1:course)
            {
                if (courseService.ReadCourse(course1.getCourseid())!=null)
                {
                    myResponse.setMeesage("id重复");
                }
                else
                {
                    courseService.AddCourse(course1);
                }
            }
            myResponse.setCode(1);
            myResponse.setMyBody(course);
        return myResponse;
    }
    //删除课程
    @RequestMapping("/DelCourse")
    @ResponseBody
    public MyResponse<List<Course>>DelCourse(@RequestBody List<Course> course){
        MyResponse<List<Course>> myResponse=new MyResponse<List<Course>>();
        for (Course course1:course)
        {
            courseService.DelCourse(course1.getCourseid());
        }
        myResponse.setCode(1);
        myResponse.setMyBody(course);
        return myResponse;
    }
    //修改课程
    @RequestMapping("/ModifyCourse")
    @ResponseBody
    public MyResponse<List<Course>> ModifyCourse(@RequestBody List<Course> course){
        MyResponse<List<Course>> myResponse=new MyResponse<List<Course>>();
        for (Course course1:course)
        {
            courseService.ModifyCourse(course1);
        }
        myResponse.setMyBody(course);
        return myResponse;
    }
}
