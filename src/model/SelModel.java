package model;

import domain.Course;
import domain.Study;
import domain.User;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import utils.MD5;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SelModel extends AllObjModel {
    /**
     * 找回密码方法根据传入的user对象判断数据库中 是否存在数据 若存在数据返回数据的cid
     *
     * @param user
     * @return cid 查询失败返回0
     */
    public int retrievePassword(User user) {
        sql = "select * from users where uQQ=? and uidCard=? and udel!=1";
        Object[] params = {user.getuQQ(), user.getUidCard()};
        try {
            Map<String, Object> map = qr.query(sql, new MapHandler(), params);
            if (map != null) {
                return (int) map.get("uid");
            }
        } catch (SQLException e) {

        }
        return 0;

    }

    /**
     * 登陆方法 登陆成功返回用户权限 0学生 1管理员 登陆失败返回-1
     *
     * @param user
     * @return
     */
    public int login(User user) {
        sql = "select * from users where uQQ=? and upassword=? and udel!=1";
        Object[] params = {user.getuQQ(), MD5.getMD5(user.getUpassword())};
        try {
            Map<String, Object> map = qr.query(sql, new MapHandler(), params);
            if (map != null) {
                if ((boolean) map.get("ulimit")) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {

        }
        return -1;
    }

    /**
     * map 集合转换为页数信息
     * @param map
     * @return
     */
    private int mapToPage(Map<String, Object> map) {
        long l = (long) map.get("count(*)");
        if (l % 10 == 0) {
            return (int) l / 10;
        } else {
            return (int) ((l / 10) + 1l);
        }
    }

    /**
     * 查询所有合法用户的页数 每页10个
     *
     * @return 合法用户的页数
     */
    public int showLiveUserPage() {
        int number = 0;
        sql = "select count(*) from users where udel != 1";
        try {
            Map<String, Object> map = qr.query(sql, new MapHandler());
            number = mapToPage(map);
        } catch (SQLException e) {

        }
        return number;
    }

    /**
     * 查询所有合法课程的页数 每页10个
     *
     * @return
     */
    public int showLiveCoursePage() {
        int number = 0;
        sql = "select count(*) from course where cdel != 1";
        try {
            Map<String, Object> map = qr.query(sql, new MapHandler());
            number = mapToPage(map);
        } catch (SQLException e) {

        }
        return number;

    }

    /**
     * 对map集合进行处理 如果存在数据返回true
     *
     * @param map
     * @return
     */
    private boolean mapToCount(Map<String, Object> map) {
        long l = (long) map.get("count(*)");
        if (l != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询无效用户个数
     *
     * @return 存在返回true
     */
    public boolean isDelUsers() {
        sql = "select count(*) from users where udel=1";
        try {
            Map<String, Object> map = qr.query(sql, new MapHandler());
            return mapToCount(map);
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 查询无效课程个数
     *
     * @return 存在返回true
     */
    public boolean isDelCourse() {
        sql = "select count(*) from course where cdel=1";
        try {
            Map<String, Object> map = qr.query(sql, new MapHandler());
            return mapToCount(map);
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 按页数查询用户信息
     *
     * @param pageNow 查询的页数
     * @return 用户信息集合 查询失败返回null
     */
    public List<User> selectPageUser(int pageNow) {
        sql = " select * from users where udel !=1 limit ?,10";
        try {
            List<User> users = qr.query(sql, new BeanListHandler<User>(User.class), pageNow * 10 - 10);
            return users;
        } catch (SQLException e) {

        }
        return null;
    }

    /**
     * 按页数查询课程信息
     *
     * @param pageNow 查询的页数
     * @return 用户信息集合 查询失败返回null
     */
    public List<Course> selectPageCourse(int pageNow) {
        sql = " select * from course where cdel !=1 limit ?,10";
        try {
            List<Course> courses = qr.query(sql, new BeanListHandler<Course>(Course.class), pageNow * 10 - 10);
            return courses;
        } catch (SQLException e) {

        }
        return null;
    }

    /**
     * 查询无效用户方法
     *
     * @return 返回结果集
     */
    public List<User> selectDelUser() {
        sql = "select * from users where udel=1";
        try {
            List<User> users = qr.query(sql, new BeanListHandler<User>(User.class));
            return users;
        } catch (SQLException e) {

        }
        return null;
    }

    /**
     * 查询无效课程方法
     *
     * @return
     */
    public List<Course> selectDelCourse() {
        sql = "select * from course where cdel=1";
        try {
            List<Course> courses = qr.query(sql, new BeanListHandler<Course>(Course.class));
            return courses;
        } catch (SQLException e) {

        }
        return null;
    }

    /**
     * 用户搜索方法 搜索失败返回null
     *
     * @param count 1名称查找 2QQ查找 3身份证查找
     * @param str   查找的条件
     * @return 查找成功返回结果集 失败返回null
     */
    public List<User> searchUsers(int count, String str) {
        List<User> users = null;
        if (count == 0) {
            sql = "select * from users where uname=? and udel!=1";
        } else if (count == 1) {
            sql = "select * from users where uQQ=? and udel!=1";
        } else {
            sql = "select * from users where uidCard=? and udel!=1";
        }
        Object[] params = {str};
        try {
            users = qr.query(sql, new BeanListHandler<User>(User.class), params);
        } catch (SQLException e) {
        }
        return users;
    }

    /**
     * 智能搜索课程方法
     * @param str 查询条件
     * @return 结果集
     */
    public List<Course> searchCoursesRoot(String str){
        List<Course>  courses=null;
        sql="select * from course where cname like ? or cteacher like ? or classify like ? and cdel !=1";
        Object[] params ={str,str,str};
        try {
            courses = qr.query(sql, new BeanListHandler<Course>(Course.class), params);
        } catch (SQLException e) {
        }
        return courses;
    }
    /**
     * 课程搜索方法 搜索失败返回null
     *
     * @param count 0课程名查找  1教师名城查找 2分类查找
     * @param str   查找条件
     * @return 查找成功返回结果集 失败返回null
     */
    public List<Course> searchCourses(int count, String str) {
        List<Course> courses = null;
        if (count == 0) {
            sql = "select * from course where cname=? and cdel !=1";
        } else if (count == 1) {
            sql = "select * from course where cteacher=? and cdel !=1";
        } else if (count == 2) {
            sql = "select * from course where classify like ? and cdel !=1";
        }
        Object[] params = {str};
        try {
            courses = qr.query(sql, new BeanListHandler<Course>(Course.class), params);
        } catch (SQLException e) {
        }
        return courses;
    }

    /**
     * 根据uid 查询用户操作
     *
     * @param uid
     * @return
     */
    public Map<String, Object> searchUsersByUid(int uid) {
        Map<String, Object> map = null;
        sql = "select * from users where uid=? and udel!=1";
        try {
            map = qr.query(sql, new MapHandler(), uid);
        } catch (SQLException e) {
        }
        return map;
    }

    /**
     * 根据cid 查询课程操作
     *
     * @param cid
     * @return
     */
    public Map<String, Object> searchCourseByCid(int cid) {
        Map<String, Object> map = null;
        sql = "select * from course where cid=? and cdel!=1";
        try {
            map = qr.query(sql, new MapHandler(), cid);
        } catch (SQLException e) {
        }
        return map;
    }

    /**
     * 查选课程分类方法
     *
     * @return 课程分类集合
     */
    public List<Map<String, Object>> showClassify() {
        List<Map<String, Object>> list = null;
        sql = "select * from  classification";
        try {
            list = qr.query(sql, new MapListHandler());
        } catch (SQLException e) {
        }
        return list;
    }

    /**
     * 根据QQ查找用户信息
     *
     * @param qq
     * @return
     */
    public User searchUsersByQQ(String qq) {
        sql = "select * from users where uQQ=?";
        User user = null;
        try {
            user = qr.query(sql, new BeanHandler<User>(User.class), qq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 判断学习记录表中时候有学习记录
     *
     * @param uid
     * @param cid
     * @return 有学习记录返回true
     */
    public boolean isStudy(int uid, int cid) {
        sql = "select * from study where uid=? && cid=?";
        Study study = null;
        try {
            study = qr.query(sql, new BeanHandler<Study>(Study.class), uid, cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (study == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据用户uid查询学习记录
     *
     * @param uid
     * @return
     */
    public List<Course> selectStudyCourse(int uid) {

        List<Course> courses = null;
        sql = "select * from course where cid=any(select cid from study where uid=?) && climit=1";
        Object[] params = {uid};
        try {
            courses = qr.query(sql, new BeanListHandler<Course>(Course.class), params);
        } catch (SQLException e) {
        }
        return courses;
    }
}