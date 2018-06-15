package cn.zucc.edu.controller;

import cn.zucc.edu.Service.AttendanceService;
import cn.zucc.edu.Util.MyResponse;
import cn.zucc.edu.entity.Attendance;
import cn.zucc.edu.entity.Attendancedetails;
import cn.zucc.edu.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller()
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;
//显示某门课程的考情信息，有那几次考勤等
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
    @RequestMapping(value = "/LoadAllAttendanceDetails",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse<List<Attendancedetails>> LoadAllAttendanceDetails(@RequestBody Attendance attendance)
    {
        MyResponse<List<Attendancedetails>> myResponse=new MyResponse<List<Attendancedetails>>();
        myResponse.setMyBody(attendanceService.loadAttendanceDetails(attendance.getAttendanceid()));
        return myResponse;
    }
    //添加一次考勤记录
    @RequestMapping(value = "/AddAttendance",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse AddAttendance(@RequestBody Attendance attendance)
    {
        MyResponse<List<Attendance>> myResponse=new MyResponse();
        myResponse.setCode(1);
       attendanceService.addAttendance(attendance);
        return myResponse;
    }
    //添加考勤详情
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
    //修改一次考勤的详情
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
