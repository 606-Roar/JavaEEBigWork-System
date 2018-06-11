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
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
@CrossOrigin(origins = "*")
@Controller()
public class TeacherController {
    @Autowired
    TeacherService teacherService;

//登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse<Teacher> Login(@RequestParam String teacherid ,@RequestParam String password) throws Exception {
        MyResponse myResponse=new MyResponse();
        Teacher teacher=teacherService.ReadTeacher(Integer.valueOf(teacherid).intValue());
        myResponse.setMyBody(teacher);
        if(teacher==null){
            myResponse.setMeesage("无此老师，请正确输入教工号");
        }
        if(teacher.getPassword().equals(password))
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

