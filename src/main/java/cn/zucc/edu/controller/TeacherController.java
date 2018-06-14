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
    public MyResponse<Teacher> Login(@RequestBody Teacher teacher) throws Exception {
        MyResponse myResponse=new MyResponse();
        Teacher teacher1=teacherService.ReadTeacher(teacher.getTeacherid());
        myResponse.setMyBody(teacher1);
        if(teacher1==null){
            myResponse.setMeesage("无此老师，请正确输入教工号");
        }
        if(teacher1.getPassword().equals(teacher.getPassword()))
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
    @RequestMapping(value = "/ReadTeacher",method = RequestMethod.POST)
    @ResponseBody
    public  MyResponse<Teacher> ReadTeacher(String teacherid) {
        System.out.println(teacherid);
        MyResponse<Teacher> myResponse=new MyResponse<Teacher>();
        Teacher teacher=teacherService.ReadTeacher(Integer.valueOf(teacherid).intValue());
        if(teacher==null){
            myResponse.setMeesage("老师不存在");
        }
        else {
            myResponse.setCode(1);
        }
        myResponse.setMyBody(teacher);
        return myResponse;
    }
    //添加老师
    @RequestMapping("/AddTeacher")
    @ResponseBody
    public MyResponse<List<Teacher>> AddTeacher(@RequestBody List<Teacher> addTeacher,@RequestBody List<Teacher> delTeacher,@RequestBody List<Teacher> modifyTeacher){
        MyResponse<List<Teacher>> myResponse=new MyResponse<List<Teacher>>();
        for(Teacher teacher1:addTeacher){
             teacher1=teacherService.ReadTeacher(teacher1.getTeacherid());
            if(teacher1==null)
            {
                teacherService.AddTeacher(teacher1);
                myResponse.setMeesage(myResponse.getMeesage()+teacher1.getTeacherid()+"添加成功。");
            }
            else{
                myResponse.setMeesage(myResponse.getMeesage()+teacher1.getTeacherid()+"id重复，添加失败。");
            }
            myResponse.setCode(1);
            myResponse.setMeesage(myResponse.getMeesage()+"添加操作完成。");
        }
        for (Teacher teacher2:delTeacher) {
            teacher2 = teacherService.ReadTeacher(teacher2.getTeacherid());
            if(teacher2!=null)
            {
                teacherService.DelTeacher(teacher2.getTeacherid());
                myResponse.setMeesage(myResponse.getMeesage()+teacher2.getTeacherid()+"删除成功。");
            }
            else {
                myResponse.setMeesage(myResponse.getMeesage()+"删除失败。");
            }
        }
        for (Teacher teacher3:modifyTeacher)
        {
            teacherService.ModifyTeacher(teacher3);
            myResponse.setMeesage(myResponse.getMeesage()+teacher3.getTeacherid()+"修改成功。");
        }
        return myResponse;
    }
//    //删除老师
//    @RequestMapping("/DelTeacher")
//    @ResponseBody
//    public MyResponse DelTeacher(@RequestBody List<Teacher> teacher){
//        for(Teacher teacher1:teacher)
//        {
//            teacher1
//        }
//    }
//    //修改老师信息
//    @RequestMapping("/ModifyTeacher")
//    @ResponseBody
//    public MyResponse ModifyTeacher(@RequestBody Teacher teacher){
//        teacherService.ModifyTeacher(teacher);
//        return "ok";
//    }
}

