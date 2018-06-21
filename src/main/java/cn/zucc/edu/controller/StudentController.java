package cn.zucc.edu.controller;

import cn.zucc.edu.Service.StudentService;
import cn.zucc.edu.Util.MyResponse;
import cn.zucc.edu.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Controller()
public class StudentController {
    @Autowired
    StudentService studentService;
    @RequestMapping(value = "/findStudent",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse<Student> LoadAllAttendance(@RequestBody Student student)
    {
        MyResponse<Student> myResponse =new MyResponse<Student>();
        Student student1=new Student();
        student1.setStudentid(111);
        student1.setStudentname(studentService.findStudentByID(student.getStudentid()).getStudentname());
        myResponse.setMyBody(student1);
        return myResponse;
    }
}
