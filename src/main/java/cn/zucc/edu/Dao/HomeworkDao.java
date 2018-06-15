package cn.zucc.edu.Dao;

import cn.zucc.edu.entity.Homework;
import cn.zucc.edu.entity.Homeworkdetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin(origins = "*")
@Repository("HomeworkDao")
public class HomeworkDao {
    @Autowired
    private SessionFactory sessionFactory;
    //创建作业
    public synchronized void addHomework(Homework homework){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Homework homework1=new Homework();
        homework1.setCourseid(homework.getCourseid());
        homework1.setHomeworkstartdate(homework.getHomeworkstartdate());
        homework1.setHomeworkenddate(homework.getHomeworkenddate());
        session.save(homework1);
        transaction.commit();
    }
    //修改作业的时间等
    public  synchronized  void modifHomework(Homework homework)
    {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Homework homework1=session.get(Homework.class,homework.getHomeworkid());
        if(homework1==null){
            try {
                throw new Exception("此作业不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        homework1.setHomeworkstartdate(homework.getHomeworkstartdate());
        homework1.setHomeworkenddate(homework.getHomeworkenddate());
        session.update(homework1);
        transaction.commit();
    }
    //提交作业，添加作业详情
    public synchronized void addHomeworkDetails(Homeworkdetails homeworkdetails){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Homeworkdetails homeworkdetails1=new Homeworkdetails();
        homeworkdetails1.setHomeworkid(homeworkdetails.getHomeworkid());
        homeworkdetails1.setStudentid(homeworkdetails.getStudentid());
        homeworkdetails1.setHomeworkdetail(homeworkdetails.getHomeworkdetail());
        session.save(homeworkdetails1);
        transaction.commit();
    }
    //再次提交作业，修改作业详情
    public  synchronized void modifyHomeworkDetails(Homeworkdetails homeworkdetails){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Homeworkdetails homeworkdetails1=session.get(Homeworkdetails.class,homeworkdetails.getHid());
        if(homeworkdetails1==null){
            try {
                throw new Exception("此作业不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        homeworkdetails1.setHomeworkdetail(homeworkdetails.getHomeworkdetail());
        session.update(homeworkdetails);
        transaction.commit();
    }
    //删除作业详情
    public synchronized void delHomeworkDetails(Homeworkdetails homeworkdetails){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Homeworkdetails homeworkdetails1=session.get(Homeworkdetails.class,homeworkdetails.getHid());
        if(homeworkdetails1==null){
            try {
                throw new Exception("此作业不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.delete(homeworkdetails1);
        transaction.commit();
    }
    //查看作业详情
    public synchronized List<Homeworkdetails> loadHomeworkdetails(int homeworkid){
        return this.sessionFactory.openSession().createQuery("from Homeworkdetails where homeworkid="+homeworkid).list();
    }
    //删除作业
    public synchronized void delHomework(int homeworkid){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Homework homework=session.get(Homework.class,homeworkid);
        if(homework==null){
            try {
                throw new Exception("此作业不存在");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        session.delete(homework);
        transaction.commit();
    }
    //显示作业
    public synchronized List<Homework> loadHomework(int courseid){
        return this.sessionFactory.openSession().createQuery("from Homework where homeworkid="+courseid).list();
    }
}
