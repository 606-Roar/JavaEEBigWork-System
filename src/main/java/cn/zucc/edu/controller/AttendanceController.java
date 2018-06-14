package cn.zucc.edu.controller;

import cn.zucc.edu.Service.AttendanceService;
import cn.zucc.edu.Util.MyResponse;
import cn.zucc.edu.entity.Attendance;
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

    @RequestMapping(value = "/LoadAllAttendace",method = RequestMethod.POST)
    @ResponseBody
    public List<Attendance> LoadAllAttendance(@RequestBody Course course)
    {
        return attendanceService.loadAttendance(course.getCourseid());
    }
    @RequestMapping(value = "/LoadAllAttendancedetails",method = RequestMethod.POST)
    @ResponseBody
    public List<Attendance> LoadAllAttendancedetails(@RequestBody Attendance attendance)
    {
        return attendanceService.loadAttendance(attendance.getAttendanceid());
    }


}
