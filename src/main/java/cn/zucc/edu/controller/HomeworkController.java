package cn.zucc.edu.controller;

import cn.zucc.edu.Service.HomeworkService;
import cn.zucc.edu.Util.MyResponse;
import cn.zucc.edu.entity.Homework;
import cn.zucc.edu.entity.Homeworkdetails;
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
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@Controller()
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;
    //显示某门课程的所有作业列表
    @RequestMapping(value = "LoadAllHomework",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse<List<Homework>> LoadAllAttendance(@RequestBody Homework homework)
    {
        MyResponse<List<Homework>> myResponse=new MyResponse<List<Homework>>();
        myResponse.setCode(1);
        myResponse.setMyBody(homeworkService.loadAllHomework(homework.getCourseid()));
        return myResponse;
    }
    //显示某个作业的提交信息列表
    @RequestMapping(value = "LoadAllHomeworkdetails",method = RequestMethod.POST)
    @ResponseBody
    public MyResponse<List<Homeworkdetails>> LoadAllAttendance(@RequestBody Homeworkdetails homeworkdetails)
    {
        MyResponse<List<Homeworkdetails>> myResponse=new MyResponse<List<Homeworkdetails>>();
        myResponse.setCode(1);
        myResponse.setMyBody(homeworkService.loadAllHomeworkdetails(homeworkdetails.getHomeworkid()));
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
