package cn.zucc.edu.controller;

import cn.zucc.edu.Service.SelectedCoursesService;
import cn.zucc.edu.Util.MyResponse;
import cn.zucc.edu.entity.Course;
import cn.zucc.edu.entity.Selectedcourses;
import cn.zucc.edu.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@Controller
public class SelectedcoursesController {
    @Autowired
    SelectedCoursesService selectedCoursesService;
    //显示某课程的选课信息列表
    @RequestMapping(value = "/LoadAllStudent",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse<List<Selectedcourses>> LoadAllStudent(@RequestBody Course course){
        MyResponse<List<Selectedcourses>> myResponse=new MyResponse<List<Selectedcourses>>();
        myResponse.setCode(1);
        myResponse.setMyBody(selectedCoursesService.LoadAllStudent(course.getCourseid()));
        return myResponse;
    }
    //显示某个上课学生的成绩等信息
    @RequestMapping("/Selectedcourses")
    @ResponseBody
    public MyResponse<Selectedcourses> ReadStudent(@RequestBody Selectedcourses selectedcourses){
        MyResponse<Selectedcourses> myResponse=new MyResponse<Selectedcourses>();
        myResponse.setCode(1);
        myResponse.setMyBody(selectedCoursesService.ReadStudent(selectedcourses.getStudentid()));
        return myResponse;
    }
    //添加某课程的选课信息以及对应的学生信息
    @RequestMapping("/AddClassStudent")
    @ResponseBody
    public MyResponse AddClassStudent (@RequestBody List<Selectedcourses> selectedcourses, @RequestBody List<Student> student){
        MyResponse myResponse=new MyResponse();
       for (Selectedcourses selectedcourses1:selectedcourses){
        selectedCoursesService.AddClassStudent(selectedcourses1);
       }
        for (Student student1:student){
        selectedCoursesService.AddStudent(student1);
       }
        myResponse.setCode(1);
        return myResponse;
    }
    //删除上课学生
    @RequestMapping("/DelStudent")
    @ResponseBody
    public MyResponse DelStudent(@RequestBody List<Student> student){
        MyResponse myResponse=new MyResponse();
        for(Student student1:student){
        selectedCoursesService.DelStudent(student1.getSid());}
        myResponse.setCode(1);
        return myResponse;
    }
    //修改成绩
    @RequestMapping("/ModifyStudentScore")
    @ResponseBody
    public MyResponse ModifyStudentScore(@RequestBody List<Selectedcourses> selectedcourses){
        MyResponse myResponse=new MyResponse();
        for (Selectedcourses selectedcourses1:selectedcourses){
        selectedCoursesService.ModifyStudentScore(selectedcourses1);
        }
        myResponse.setCode(1);
        return myResponse;
    }
    //成绩输入
    @RequestMapping("/AddScore")
    @ResponseBody
    public MyResponse AddScore(@RequestBody List<Selectedcourses> selectedcourses){
        MyResponse myResponse=new MyResponse();
        for (Selectedcourses selectedcourses1:selectedcourses) {
            selectedCoursesService.AddScore(selectedcourses1);
        }
        myResponse.setCode(1);
        return myResponse;
    }
}
