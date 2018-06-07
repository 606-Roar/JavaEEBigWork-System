package cn.zucc.edu.Service;

import cn.zucc.edu.Dao.AttendanceDao;
import cn.zucc.edu.entity.Attendance;
import cn.zucc.edu.entity.Attendancedetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttendanceService {
@Autowired
    AttendanceDao attendanceDao;
    //创建点名
    public void addAttendance(Attendance attendance){
        attendanceDao.addAttendance(attendance);
    }
    //添加点名详情
    public void addAttendanceDetails(Attendancedetails attendancedetails){
        attendanceDao.addAttendanceDetails(attendancedetails);
    }
    //修改点名详情
    public void modifyAttendanceDetails(Attendancedetails attendancedetails){
        attendanceDao.modifyAttendanceDetails(attendancedetails);
    }
    //删除某学生点名详情
    public void delAttendanceDetails(Attendancedetails attendancedetails){
        attendanceDao.delAttendanceDetails(attendancedetails);
    }
    //显示某天点名详情
    public List<Attendancedetails> showAttendanceDetails(int attendanceid){
        return attendanceDao.showAttendanceDetails(attendanceid);
    }
    //删除某天点名
    public void delAttendance(int attendanceid ){
        attendanceDao.delAttendance(attendanceid);
    }
    //显示所有点名
    public List<Attendance> loadAttendance(int attendanceid){
        return attendanceDao.loadAttendance(attendanceid);
    }
}
