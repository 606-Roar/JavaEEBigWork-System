package cn.zucc.edu.controller;

import cn.zucc.edu.Service.TeacherService;
import cn.zucc.edu.Util.MyResponse;
import cn.zucc.edu.entity.Teacher;
import net.bytebuddy.implementation.bytecode.Throw;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.lang.reflect.Method;
import java.util.List;
@CrossOrigin(origins = "*")
@Controller()
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    //登录
    @RequestMapping(value = "test")
    @ResponseBody
    public String Test(){
        System.out.println("123");
        return "1230";
    }
//登录
    @RequestMapping(value = "/login")
    @ResponseBody
    public MyResponse<Teacher> Login(int teacherid, String psw) throws Exception {
//        System.out.println("你来了");
        MyResponse myResponse=new MyResponse();
        Teacher teacher=teacherService.ReadTeacher(teacherid);
        myResponse.setMyBody(teacher);
        if(teacher==null){
            myResponse.setMeesage("无此老师，请正确输入教工号");
        }
        if(teacher.getPassword().equals(psw))
        {
            myResponse.setCode(1);
        }
        else {
            myResponse.setMeesage("密码错误");
        }
        System.out.println("你来了");
        return myResponse;
    }
    //显示所有老师
    @RequestMapping("/LoadAllTeacher")
    @ResponseBody
    public List<Teacher> LoadAllTeacher(){
        return teacherService.LoadAllTeacher();
    }
    //显示某个老师
    @RequestMapping("/ReadTeacher")
    @ResponseBody
    public  Teacher ReadTeacher(int teacherid){
        return teacherService.ReadTeacher(teacherid);
    }
    //添加老师
    @RequestMapping("/AddTeacher")
    @ResponseBody
    public String AddTeacher(@RequestBody Teacher teacher){
        teacherService.AddTeacher(teacher);
        return "ok";
    }
    //删除老师
    @RequestMapping("/DelTeacher")
    @ResponseBody
    public String DelTeacher(int teacherid){
        teacherService.DelTeacher(teacherid);
        return "ok";
    }
    //修改老师信息
    @RequestMapping("/ModifyTeacher")
    @ResponseBody
    public String ModifyTeacher(@RequestBody Teacher teacher){
        teacherService.ModifyTeacher(teacher);
        return "ok";
    }
}

