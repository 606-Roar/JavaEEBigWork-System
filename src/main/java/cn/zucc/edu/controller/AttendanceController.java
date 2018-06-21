package cn.zucc.edu.controller;

import cn.zucc.edu.BackEntity.Backattendancedetails;
import cn.zucc.edu.Service.AttendanceService;
import cn.zucc.edu.Service.SelectedCoursesService;
import cn.zucc.edu.Service.StudentService;
import cn.zucc.edu.Util.MyResponse;
import cn.zucc.edu.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller()
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    StudentService studentService;
    @Autowired
    SelectedCoursesService selectedCoursesService;
    //显示某门课程的考勤列表，有那几次考勤等
    @RequestMapping(value = "/LoadAllAttendance",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse<List<Attendance>> LoadAllAttendance(@RequestBody Course course)
    {
        MyResponse<List<Attendance>> myResponse=new MyResponse<List<Attendance>>();
        myResponse.setCode(1);
        myResponse.setMyBody(attendanceService.loadAttendance(course.getCourseid()));
        return myResponse;
    }
    //显示某次考勤的信息列表，某学生是否出席等
    //返回Backattendancedetails列表
    //studentid,studentname,attendancedetail
    @RequestMapping(value = "/LoadAllAttendanceDetails",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse<List<Backattendancedetails>> LoadAllAttendanceDetails(@RequestBody Attendance attendance)
    {
        MyResponse<List<Backattendancedetails>> myResponse=new MyResponse<List<Backattendancedetails>>();
        List<Backattendancedetails> list=new ArrayList<Backattendancedetails>();
        for (Attendancedetails attendancedetails:attendanceService.loadAttendanceDetails(attendance.getAttendanceid()))
        {
            Backattendancedetails backattendancedetails=new Backattendancedetails();
            backattendancedetails.setStudentid(attendancedetails.getStudentid());
            backattendancedetails.setAttendancedetail(attendancedetails.getAttendancedetail());
            backattendancedetails.setNid(attendancedetails.getNid());
            backattendancedetails.setAttendanceid(attendancedetails.getAttendanceid());
            backattendancedetails.setStudentname(studentService.findStudentByID(attendancedetails.getStudentid()).getStudentname());
            list.add(backattendancedetails);
        }
        myResponse.setCode(1);
        myResponse.setMyBody(list);
        return myResponse;
    }
    //添加一次考勤记录，返回attendanceid->i用于给创建attendancedetail
    //此时会新建一条attendace记录，并且添加这个班上所有学生的attendacedetail记录
    @RequestMapping(value = "/AddAttendance",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse AddAttendance(@RequestBody Attendance attendance)
    {
        MyResponse myResponse=new MyResponse<List<Backattendancedetails>>();
        List<Backattendancedetails> list=new ArrayList<Backattendancedetails>();
        //添加一次attendance记录
       attendance.setAttendancedate(new Date(System.currentTimeMillis()));
       int i=attendanceService.addAttendance(attendance);
       //先通过attendace中的courseid找到所有上这门课的学生
       for (Selectedcourses student:selectedCoursesService.LoadAllStudent(attendance.getCourseid()))
       {
           //添加每个学生的一条Attendancedetail记录
           Attendancedetails attendancedetails=new Attendancedetails();
           attendancedetails.setAttendanceid(i);
           attendancedetails.setStudentid(student.getStudentid());
           attendancedetails.setAttendancedetail("undefined");
           attendanceService.addAttendanceDetails(attendancedetails);

       }
       myResponse.setCode(1);
       myResponse.setMyBody(i);
       return myResponse;
    }
    //添加考勤详情,目前看用不到
    @RequestMapping(value = "/AddAttendanceDetails",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse AddAttendanceDetails(@RequestBody List<Attendancedetails> attendancedetails)
    {
        MyResponse myResponse=new MyResponse();
        myResponse.setCode(1);
        for (Attendancedetails attendancedetails1:attendancedetails) {
            attendanceService.addAttendanceDetails(attendancedetails1);
        }
        return myResponse;
    }
    //进行一次点名或者说修改点名表中信息
    @RequestMapping(value = "modifyAttdendanceDetails",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse ModifyAttendanceDetails(@RequestBody List<Attendancedetails> attendancedetails)
    {
        MyResponse myResponse=new MyResponse();
        myResponse.setCode(1);
        for (Attendancedetails attendancedetails1:attendancedetails){
            attendanceService.modifyAttendanceDetails(attendancedetails1);
        }
        return myResponse;
    }
}
