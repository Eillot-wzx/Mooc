package model;

import domain.Course;
import domain.User;
import utils.MD5;

import java.sql.SQLException;

public class UpdModel extends AllObjModel {

    /**
     * 更新密码方法
     *
     * @param user user对象
     * @return 更新成功返回true
     */
    public boolean updatePassword(User user) {
        sql = "update users set upassword=? where uid=?";
        Object[] params = {MD5.getMD5(user.getUpassword()), user.getUid()};
        try {
            if (qr.update(sql, params) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 删除用户方法 软删除
     *
     * @param cid
     * @return 删除成功返回true
     */
    public boolean delUesrFalse(int cid) {
        sql = "update users set udel=1 where uid=?";
        try {
            if (qr.update(sql, cid) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 更新用户方法
     *
     * @param user 待更新的用户数据
     * @return 成功返回true  失败返回false
     */
    public boolean editUser(User user) {
        sql = "update users set uname=?,uQQ=?,uidCard=?,ulimit=? where uid=?";
        Object[] params = {user.getUname(), user.getuQQ(), user.getUidCard(), user.isUlimit(), user.getUid()};
        try {
            if (qr.update(sql, params) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 编辑课程操作
     *
     * @param course
     * @return 更新成功返回true
     */
    public boolean editCourse(Course course) {
        sql = "update course set cname=?,classify=?,cteacher=?,cpath=?,ctime=?,cmore=?,climit=? where cid=?";
        Object[] params = {
                course.getCname(),
                course.getClassify(),
                course.getCteacher(),
                course.getCpath(),
                course.getCtime(),
                course.getCmore(),
                course.isClimit(),
                course.getCid()
        };
        try {
            if (qr.update(sql, params) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 软删除用户恢复方法 根据提供的selection参数 对指定用户进行恢复
     *
     * @param selection
     * @return 成功返回true
     */
    public boolean recoverUser(int selection) {
        sql = "update users set udel=0 where uid=?";
        try {
            if (qr.update(sql, selection) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 软删除课程恢复方法 根据提供的selection参数 对指定课程进行恢复
     *
     * @param selection
     * @return 成功返回true
     */
    public boolean recoverCourse(int selection) {
        sql = "update course set cdel=0 where cid=?";
        try {
            if (qr.update(sql, selection) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 将所有软删除用户恢复到正常用户
     *
     * @return 成功返回true
     */
    public boolean recoverAllUser() {
        sql = "update users set udel=0 where udel=1";
        try {
            if (qr.update(sql) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 将所有软删除课程恢复到正常课程
     *
     * @return 成功返回true
     */
    public boolean recoverAllCourse() {
        sql = "update course set cdel=0 where cdel=1";
        try {
            if (qr.update(sql) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 软删除指定的课程操作
     *
     * @param cid 指定的课程cid
     * @return 删除成功返回true
     */
    public boolean delCourseFalse(int cid) {
        sql = "update course set cdel=1 where cid=?";
        try {
            if (qr.update(sql, cid) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 更新课表分类操作 进行重命名操作
     *
     * @param oldnname
     * @param newname
     * @return 成功返回true
     */
    public boolean updclassification(String oldnname, String newname) {
        sql = "update classification set class_name=? where class_name=?";
        try {
            if (qr.update(sql, newname, oldnname) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

}