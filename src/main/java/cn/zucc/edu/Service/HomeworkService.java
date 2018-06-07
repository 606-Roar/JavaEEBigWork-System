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
    public void addHomework(Homework homework){
        homeworkDao.addHomework(homework);
    }
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
    //查看作业详情
    public  List<Homeworkdetails> showHomeworkdetails(int homeworkid){
        return homeworkDao.showHomeworkdetails(homeworkid);
    }
    //删除作业
    public void delHomework(int homeworkid){
        homeworkDao.delHomework(homeworkid);
    }
    //显示作业
    public List<Homework> loadHomework(int homeworkid){
        return homeworkDao.loadHomework(homeworkid);
    }
}
