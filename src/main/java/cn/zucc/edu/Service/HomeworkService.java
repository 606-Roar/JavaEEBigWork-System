package cn.zucc.edu.Service;

import cn.zucc.edu.Dao.HomeworkDao;
import cn.zucc.edu.entity.Homework;
import cn.zucc.edu.entity.Homeworkdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HomeworkService {
    @Autowired
    HomeworkDao homeworkDao;
    //创建作业
    public int addHomework(Homework homework){
        return homeworkDao.addHomework(homework);
    }
    //添加作业详情
    public void modifyHomework(Homework homework){homeworkDao.modifyHomework(homework);}
    //添加作业详情
    public void addHomeworkDetails(Homeworkdetails homeworkdetails){
        homeworkDao.addHomeworkDetails(homeworkdetails);
    }
    //修改作业详情
    public void modifyHomeworkDetails(Homeworkdetails homeworkdetails){
        homeworkDao.modifyHomeworkDetails(homeworkdetails);
    }
    //删除作业详情
    public void delHomeworkDetails(Homeworkdetails homeworkdetails){
        homeworkDao.delHomeworkDetails(homeworkdetails);
    }
    //删除作业
    public void delHomework(int homeworkid){
        homeworkDao.delHomework(homeworkid);
    }
    //显示课程下所有作业列表
    public List<Homework> loadAllHomework(int courseid){
        return homeworkDao.loadAllHomework(courseid);
    }
    //显示某个作业的基本信息
    public Homework LoadHomework(int homeworkid){return homeworkDao.loadHomework(homeworkid);}
    //查看某作业的详情
    public  List<Homeworkdetails> loadAllHomeworkdetails(int homeworkid){
        return homeworkDao.loadHomeworkdetails(homeworkid);
    }


}
