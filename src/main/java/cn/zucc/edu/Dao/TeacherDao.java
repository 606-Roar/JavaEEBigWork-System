package cn.zucc.edu.Dao;

import cn.zucc.edu.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository("TeacherDao")
public class TeacherDao {
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    //显示老师列表
    public synchronized List<Teacher>  loadAllTeacher(){
    return this.sessionFactory.getCurrentSession().createQuery("from Teacher").list();
    }
    //通过id查看老师
    public synchronized Teacher readTeacher(int teacherid){
        Session session=sessionFactory.openSession();
        Teacher teacher=session.get(Teacher.class,teacherid);
        return teacher;
    }
    //添加老师
    public synchronized void addteacher(Teacher user){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Teacher teacher=new Teacher();
        //老师工号如何处理？
        teacher.setTeacherid(user.getTeacherid());
        teacher.setTeachername(user.getTeachername());
        teacher.setPassword(user.getPassword());
        teacher.setType(user.getType());
        session.save(teacher);
        transaction.commit();
    }
    //删除老师
    public synchronized void delteacher(int userid ){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Teacher teacher=session.get(Teacher.class,userid);
            if(teacher==null){
                try {
                    throw new Exception("此老师不存在");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            session.delete(teacher);
            transaction.commit();
    }
    //修改老师信息
    public synchronized void modifyteacher(Teacher teacher){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Teacher teacher1=session.get(Teacher.class,teacher.getTeacherid());
        if(teacher==null){
            try {
                throw new Exception("此老师不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        teacher1.setPassword(teacher.getPassword());
        teacher1.setType(teacher.getType());
        teacher1.setTeachername(teacher.getTeachername());
        session.update(teacher1);
        transaction.commit();
    }
}
