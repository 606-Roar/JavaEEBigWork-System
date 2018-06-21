package cn.zucc.edu.controller;

import cn.zucc.edu.BackEntity.Backhomeworkdetails;
import cn.zucc.edu.Service.HomeworkService;
import cn.zucc.edu.Service.SelectedCoursesService;
import cn.zucc.edu.Service.StudentService;
import cn.zucc.edu.Util.MyResponse;
import cn.zucc.edu.entity.Course;
import cn.zucc.edu.entity.Homework;
import cn.zucc.edu.entity.Homeworkdetails;
import cn.zucc.edu.entity.Selectedcourses;
import com.mchange.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@Controller()
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;
    @Autowired
    StudentService studentService;
    @Autowired
    SelectedCoursesService selectedCoursesService;
    //显示某门课程courseid的所有作业列表
    @RequestMapping(value = "LoadAllHomework",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse<List<Homework>> LoadAllAttendance(@RequestBody Course course)
    {
        MyResponse<List<Homework>> myResponse=new MyResponse<List<Homework>>();
        myResponse.setCode(1);
        myResponse.setMyBody(homeworkService.loadAllHomework(course.getCourseid()));
        return myResponse;
    }
    //显示某个作业的基本信息列表
    @RequestMapping(value = "LoadHomework",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse<Homework> LoadAllAttendance(@RequestBody Homework homework)
    {
        MyResponse<Homework> myResponse=new MyResponse<Homework>();
        myResponse.setCode(1);
        myResponse.setMyBody(homeworkService.LoadHomework(homework.getHomeworkid()));
        return myResponse;
    }
    //显示某个作业的提交信息列表
    @RequestMapping(value = "LoadAllHomeworkdetails",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse<List<Backhomeworkdetails>> LoadAllHomeworkdetails(@RequestBody Homework homework)
    {
        MyResponse<List<Backhomeworkdetails>> myResponse=new MyResponse<List<Backhomeworkdetails>>();
        List<Backhomeworkdetails> list=new ArrayList<Backhomeworkdetails>();
        for (Homeworkdetails homeworkdetails:homeworkService.loadAllHomeworkdetails(homework.getHomeworkid()))
        {
            Backhomeworkdetails backattendancedetails=new Backhomeworkdetails();
            backattendancedetails.setStudentid(homeworkdetails.getStudentid());
            backattendancedetails.setHid(homeworkdetails.getHid());
            backattendancedetails.setHomeworkdetail(homeworkdetails.getHomeworkdetail());
            backattendancedetails.setHomeworkid(homeworkdetails.getHomeworkid());
            backattendancedetails.setStudentname(studentService.findStudentByID(homeworkdetails.getStudentid()).getStudentname());
            list.add(backattendancedetails);
        }
        myResponse.setCode(1);
        myResponse.setMyBody(list);
        return myResponse;
    }
    //添加一次作业提交
    @RequestMapping(value = "AddHomework",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse AddHomework(@RequestBody Homework homework)
    {
        MyResponse myResponse=new MyResponse<List<Backhomeworkdetails>>();
        List<Backhomeworkdetails> list=new ArrayList<Backhomeworkdetails>();
        //添加一次attendance记录
        homework.setHomeworkstartdate(new Date(System.currentTimeMillis()));
        homework.setHomeworkenddate(new Date(System.currentTimeMillis()));
        int i=homeworkService.addHomework(homework);

        //先通过homework中的courseid找到所有上这门课的学生
        for (Selectedcourses student:selectedCoursesService.LoadAllStudent(homework.getCourseid()))
        {
            //添加每个学生的一条Homeworkdetail记录
            Homeworkdetails homeworkdetails=new Homeworkdetails();
            homeworkdetails.setStudentid(student.getStudentid());
            homeworkdetails.setHomeworkid(i);
            homeworkdetails.setHomeworkdetail("undefined");
            homeworkService.addHomeworkDetails(homeworkdetails);
        }
        myResponse.setCode(1);
        myResponse.setMyBody(i);
        return myResponse;
    }
    //
    @RequestMapping(value = "ModifyHomework",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse ModifyHomework(@RequestBody  List<Homeworkdetails> homeworkdetails)
    {
        MyResponse myResponse=new MyResponse();
        myResponse.setCode(1);
        for (Homeworkdetails homeworkdetails1:homeworkdetails){
            homeworkService.modifyHomeworkDetails(homeworkdetails1);
        }
        return myResponse;
    }
    //上传作业附件
    @RequestMapping(value = "UploadAttachment",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse UploadAttachment(@RequestParam List<MultipartFile> uploadfile, HttpServletRequest request,String homeworkid)
    {
        MyResponse myResponse =new MyResponse();
        if(!uploadfile.isEmpty()&&uploadfile.size()>0)
        {
            for (MultipartFile file :uploadfile) {
                String originalFilename = file.getOriginalFilename();
                String dirPath = request.getServletContext().getRealPath("/upload/");
                File filePath = new File(dirPath);
                if (!filePath.exists()) {
                    filePath.mkdir();
                }

                String newFilename = homeworkid + "_" + UUID.randomUUID() + "_" + originalFilename;
                try{
                    file.transferTo(new File(dirPath+newFilename));
                }catch (Exception e){
                    e.printStackTrace();
                    myResponse.setMeesage("error");
                    return myResponse;
                }
                myResponse.setCode(1);
            }
        }
        return myResponse;
    }
    @RequestMapping(value = "/DownloadAttachment",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<byte[]> DownloadAttachment(HttpServletRequest request,String homeworkid) throws IOException {
        String path =request.getServletContext().getRealPath("/upload/");
        File file =new File(path+File.separator+homeworkid);
        HttpHeaders headers=new HttpHeaders();
        headers.setContentDispositionFormData("attachment",homeworkid);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.getBytes(file),headers, HttpStatus.OK);
    }

}
