package model;

import java.sql.SQLException;

public class DelModel extends AllObjModel {

    /**
     * 根据提供的selection参数 对指定的用户进行彻底删除操作
     *
     * @param selection
     * @return 成功返回true
     */
    public boolean delUser(int selection) {
        sql = "delete from users where uid =?";
        try {
            if (qr.update(sql, selection) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 根据提供的selection参数 对指定的课程进行彻底删除操作
     *
     * @param selection
     * @return
     */
    public boolean delCourse(int selection) {
        sql = "delete from course where cid =?";
        try {
            if (qr.update(sql, selection) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 删除全部软删除用户
     *
     * @return 成功返回true
     */
    public boolean delAllUsers() {
        sql = "delete from users where udel =1";
        try {
            if (qr.update(sql) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 删除全部软删除课程
     *
     * @return 成功返回true
     */
    public boolean delAllCourse() {
        sql = "delete from course where cdel=1";
        try {
            if (qr.update(sql) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 删除课表分类操作,由于课表分类的特殊性  所以此项操作直接执行彻底删除操作
     *
     * @param str
     * @return 删除成功返回true
     */
    public boolean delClassification(String str) {
        sql = "delete from classification where class_name=?";
        try {
            if (qr.update(sql, str) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     * 学生退选课程方法 删除Study表中的学习记录
     *
     * @param cid
     * @return 删除成功返回true
     */
    public boolean delStudy(int cid) {
        sql = "delete from study where cid=?";
        try {
            if (qr.update(sql, cid) > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

}