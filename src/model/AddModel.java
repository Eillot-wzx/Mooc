package model;

import domain.Course;
import domain.User;
import utils.MD5;

import java.sql.SQLException;

public class AddModel extends AllObjModel {

    /**
     * 增加用户方法 向数据库中插入一条数据
     *
     * @param user user对象
     * @return 成功返回true
     */
    public boolean addUser(User user) {
        sql = "insert into users (uname,upassword,uQQ,uidCard) value (?,?,?,?)";
        Object[] params = {user.getUname() + "", MD5.getMD5(user.getUpassword()) + "", user.getuQQ() + "",
                user.getUidCard() + ""};
        try {
            if (qr.update(sql, params) > 0)
                return true;
        } catch (SQLException e) {
        }
        return false;
    }

    /**
     * 添加课程分类信息操作
     *
     * @param str 需要添加的课程信息名字
     * @return 添加成功返回true
     */
    public boolean addClassify(String str) {
        sql = "insert into classification (class_name) value (?)";
        try {
            if (qr.update(sql, str) > 0)
                return true;
        } catch (SQLException e) {
        }
        return false;
    }

    /**
     * 增加课程方法 向课程表中插入一条课程数据
     *
     * @param course
     * @return 添加成功返回true
     */
    public boolean addCourse(Course course) {
        sql = "insert into course (cname,classify,cteacher,cpath,ctime,cmore,climit) value(?,?,?,?,?,?,?)";
        int limit = 0;
        if (course.isClimit()) {
            limit = 1;
        }
        Object[] params = {course.getCname(), course.getClassify(), course.getCteacher(), course.getCpath(),
                course.getCtime(), course.getCmore(), limit};
        try {
            if (qr.update(sql, params) > 0)
                return true;
        } catch (SQLException e) {
        }
        return false;
    }

    /**
     * 添加学习记录方法,向学习记录表中添加数据
     * 注意:此方法调用之前要判断表中是否有学习记录
     *
     * @param uid
     * @param cid
     * @return 添加成功返回true
     */
    public boolean addStudy(int uid, int cid) {
        sql = "insert into study (uid,cid) value (?,?)";
        Object[] params = {uid, cid};
        try {
            if (qr.update(sql, params) > 0)
                return true;
        } catch (SQLException e) {
        }
        return false;
    }

}
