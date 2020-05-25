package controller;

import domain.Course;
import domain.User;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RootController extends AllObjController {
    /**
     * 编辑用户信息方法
     * 完善信息-1 QQ不合法-2 身份证不合法 -3 姓名不合法-4 成功0 失败1
     *
     * @param uid
     * @param uname
     * @param uQQ
     * @param uidCard
     * @param ulimit
     * @return
     */
    public int editUser(int uid, String uname, String uQQ, String uidCard, boolean ulimit) {
        if (!isNotEmpty(uname) && isNotEmpty(uQQ) && isNotEmpty(uidCard)) return -1;
        if (!isQQ(uQQ)) return -2;
        if (!isIdCard(uidCard)) return -3;
        if (!isName(uname)) return -4;

        user.setUid(uid);
        user.setUname(uname);
        user.setuQQ(uQQ);
        user.setUidCard(uidCard);
        user.setUlimit(ulimit);

        if (updmodel.editUser(user)) return 0;
        else return 1;
    }

    /**
     * 查询所有有效用户
     *
     * @return 有效用户的个数
     */
    public int showLiveUserPage() {
        return selmodel.showLiveUserPage();
    }

    /**
     * 查询所有有效课程
     *
     * @return
     */
    public int showLiveCoursePage() {
        return selmodel.showLiveCoursePage();
    }

    /**
     * 查询指定页数的用户数据 将model层的集合转换为二位数组
     *
     * @param pageNow 查询的页数
     * @return 二位数组
     */
    public Object[][] selectPageUser(int pageNow) {
        List<User> users = selmodel.selectPageUser(pageNow);
        return listUserToObj(users);
    }

    /**
     * 将listUser 处理为 obj[][]
     *
     * @param users
     * @return
     */
    private Object[][] listUserToObj(List<User> users) {
        if (users != null) {
            Object[][] obj = new Object[users.size()][5];
            for (int i = 0; i < users.size(); i++) {
                user = users.get(i);
                obj[i][0] = user.getUid();
                obj[i][1] = user.getUname();
                obj[i][2] = user.getuQQ();
                obj[i][3] = user.getUidCard();
                if (user.isUlimit()) {
                    obj[i][4] = "管理员";
                } else {
                    obj[i][4] = "学生";
                }
            }
            return obj;
        }
        return null;
    }

    /**
     * 查询指定页数的课程数据 将model层的集合转换为二位数组
     *
     * @param pageNow 查询的页数
     * @return 二位数组
     */
    public Object[][] selectPageCourse(int pageNow, Object[][] classificationObj) {
        List<Course> courses = selmodel.selectPageCourse(pageNow);
        return listCourseToObj(courses, classificationObj);
    }

    /**
     * 判断跳转的页面是否合法
     *
     * @param str
     * @param pageSum
     * @return 合法数字返回true
     */
    public boolean isTruePage(String str, int pageSum) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(str);
        boolean b = matcher.matches();
        if (b) {
            int i = Integer.valueOf(str);
            if (i > 0 && i <= pageSum) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据用户提供的uid  搜索到 指定用户
     *
     * @param uid
     * @return 单一用户
     */
    public User searchUsersByUid(int uid) {
        Map<String, Object> map = selmodel.searchUsersByUid(uid);
        user.setUid((int) map.get("uid"));
        user.setUname((String) map.get("uname"));
        user.setuQQ((String) map.get("uQQ"));
        user.setUidCard((String) map.get("uidCard"));
        user.setUpassword((String) map.get("upassword"));
        user.setUlimit((boolean) map.get("ulimit"));
        user.setUdel((boolean) map.get("udel"));
        return user;
    }

    /**
     * 根据用户提供的cid 搜索到指定的课程
     *
     * @param cid
     * @return
     */
    public Course searchCourseByCid(int cid) {
        Map<String, Object> map = selmodel.searchCourseByCid(cid);
        course.setCid((int) map.get("cid"));
        course.setCname((String) map.get("cname"));
        course.setClassify((String) map.get("classify"));
        course.setCteacher((String) map.get("cteacher"));
        course.setCpath((String) map.get("cpath"));
        course.setCtime((int) map.get("ctime"));
        course.setCmore((String) map.get("cmore"));
        course.setClimit((boolean) map.get("climit"));
        course.setCdel((boolean) map.get("cdel"));
        return course;
    }

    /**
     * 用户搜索方法 此方法不进行过滤
     *
     * @param count 姓名搜索0 QQ搜索1 身份证搜索2
     * @param str   搜索的条件
     * @return 结果集数组
     */
    public Object[][] searchUsers(int count, String str) {
        List<User> users = selmodel.searchUsers(count, str);
        Object[][] obj = null;
        if (user != null) {
            obj = new Object[users.size()][5];
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                obj[i][0] = user.getUid();
                obj[i][1] = user.getUname();
                obj[i][2] = user.getuQQ();
                obj[i][3] = user.getUidCard();
                if (user.isUlimit()) {
                    obj[i][4] = "管理员";
                } else {
                    obj[i][4] = "学生";
                }
            }
        }
        return obj;
    }

    /**
     * 用户搜索方法 通过QQ号进行搜索
     *
     * @param QQ 搜索条件
     * @return
     */
    public User searchUsersByQQ(String QQ) {
        return selmodel.searchUsersByQQ(QQ);
    }

    /**
     * 按页数搜索课程操作
     *
     * @param count             搜索模式 详见selmdel对应方法
     * @param str               搜索条件
     * @param classificationObj 需要处理的课程分类信息
     * @return
     */
    public Object[][] serchCourses(int count, String str, Object[][] classificationObj) {

        List<Course> courses = null;
        if (count == 3) {
            courses = selmodel.searchCoursesRoot("%" + str + "%");
        } else {
            courses = selmodel.searchCourses(count, str);
        }

        return listCourseToObj(courses, classificationObj);
    }

    /**
     * 删除用户操作 软删除 调用updmodel方法进行数据更新
     *
     * @param cid 需要删除的cid
     * @return
     */
    public boolean delUesrFalse(int cid) {
        return updmodel.delUesrFalse(cid);
    }

    /**
     * 软删除课程课程信息操作
     *
     * @param cid
     * @return
     */
    public boolean delCourseFalse(int cid) {
        return updmodel.delCourseFalse(cid);
    }

    /**
     * 查询无效用户个数
     *
     * @return 存在返回true
     */
    public boolean isDelUsers() {
        return selmodel.isDelUsers();
    }

    /**
     * 查询无效课程信息个数
     *
     * @return 存在返回true
     */
    public boolean isDelCourse() {
        return selmodel.isDelCourse();
    }

    /**
     * 查询所有无效用户
     *
     * @return 结果集数组
     */
    public Object[][] selectDelUser() {
        List<User> users = selmodel.selectDelUser();
        return listUserToObj(users);
    }

    /**
     * 查选所有无效课程信息
     *
     * @param classificationObj 课程处理所需要的课程分类信息
     * @return 结果集数组
     */
    public Object[][] selectDelCourse(Object[][] classificationObj) {
        List<Course> courses = selmodel.selectDelCourse();
        return listCourseToObj(courses, classificationObj);
    }

    /**
     * 软删除用户恢复方法
     *
     * @param selection
     * @return
     */
    public boolean recoverUser(int selection) {
        return updmodel.recoverUser(selection);
    }

    /**
     * 软删除课程恢复方法
     *
     * @param selection
     * @return
     */
    public boolean recoverCourse(int selection) {
        return updmodel.recoverCourse(selection);
    }

    /**
     * 删除指定用户方法
     *
     * @param selection 指定用户的cid参数
     * @return
     */
    public boolean delUser(int selection) {
        return delmodel.delUser(selection);
    }

    /**
     * 删除指定课程方法
     *
     * @param selection
     * @return
     */
    public boolean delCourse(int selection) {
        return delmodel.delCourse(selection);
    }

    /**
     * 删除全部用户方法
     *
     * @return 成功返回true
     */
    public boolean delAllUsers() {
        return delmodel.delAllUsers();
    }

    /**
     * 删除全部课程信息方法
     *
     * @return 成功返回true
     */
    public boolean delAllCourse() {
        return delmodel.delAllCourse();
    }

    /**
     * 将所有软删除用户恢复到正常状态
     *
     * @return 成功返回true
     */
    public boolean recoverAllUser() {
        return updmodel.recoverAllUser();
    }

    /**
     * 将所有软删除课程恢复到正常状态
     *
     * @return
     */
    public boolean recoverAllCourse() {
        return updmodel.recoverAllCourse();
    }

    /**
     * 查看所有课程分类
     *
     * @return
     */
    public Object[][] showClassify() {
        List<Map<String, Object>> list = selmodel.showClassify();
        Object[][] classificationObj = new Object[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            classificationObj[i][0] = map.get("class_id");
            classificationObj[i][1] = map.get("class_name");
        }
        return classificationObj;
    }

    /**
     * 删除指定课表分类操作
     *
     * @param str
     * @return 删除成功返回true
     */
    public boolean delClassification(String str) {
        return delmodel.delClassification(str);
    }

    /**
     * 重命名课程分类信息
     *
     * @param oldnname
     * @param newname
     * @return 成功返回true
     */
    public boolean updclassification(String oldnname, String newname) {
        return updmodel.updclassification(oldnname, newname);
    }

    /**
     * 添加课程分类操作
     *
     * @param str
     * @return
     */
    public boolean addClassify(String str) {
        return addmodel.addClassify(str);
    }

    /**
     * 编辑课程操作
     *
     * @param cid
     * @param cname
     * @param cteacher
     * @param cpath
     * @param classificationModelNow
     * @param more
     * @param limit
     * @param ctime
     * @return 信息不全-1 教师姓名不正确-3 课时信息错误-4 成功返回0 失败返回1
     */
    public int editCourse(int cid, String cname, String cteacher, String cpath, DefaultListModel<String> classificationModelNow, String more, boolean limit, String ctime) {
        if (!(isNotEmpty(cname) && isNotEmpty(cteacher) && isNotEmpty(cpath) && isNotEmpty(more) && isNotEmpty(ctime))) {
            return -1;
        }
        if (classificationModelNow.getSize() == 0) {
            return -1;
        }
        if (!isName(cteacher)) {
            return -3;
        }
        if (!isTruePage(ctime, 100)) {
            return -4;
        }
        String s = "";
        Object[][] objects = showClassify();
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < classificationModelNow.getSize(); j++) {
                if (objects[i][1].equals(classificationModelNow.get(j))) {
                    s += (Integer.valueOf(objects[i][0] + "") + "`");
                }
            }
        }
        course.setCid(cid);
        course.setCname(cname);
        course.setClassify(s);
        course.setCteacher(cteacher);
        course.setCpath(cpath);
        course.setCtime(Integer.valueOf(ctime));
        course.setCmore(more);
        course.setClimit(limit);
        if (updmodel.editCourse(course)) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 添加课程操作
     *
     * @param cname
     * @param cteacher
     * @param cpath
     * @param classificationModelNow
     * @param more
     * @param limit
     * @param ctime
     * @return 信息不全返回-1  教师姓名返回-3   ctime不合法返回-4 成功返回0 失败1
     */
    public int addCourse(String cname, String cteacher, String cpath, DefaultListModel<String> classificationModelNow, String more, boolean limit, String ctime) {

        if (!(isNotEmpty(cname) && isNotEmpty(cteacher) && isNotEmpty(cpath) && isNotEmpty(more) && isNotEmpty(ctime))) {
            return -1;
        }
        if (classificationModelNow.getSize() == 0) {
            return -1;
        }

        if (!isName(cteacher)) {
            return -3;
        }
        if (!isTruePage(ctime, 100)) {
            return -4;
        }
        String s = "";
        Object[][] objects = showClassify();
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < classificationModelNow.getSize(); j++) {
                if (objects[i][1].equals(classificationModelNow.get(j))) {
                    s += (Integer.valueOf(objects[i][0] + "") + "`");
                }
            }
        }

        course.setCname(cname);
        course.setClassify(s);
        course.setCteacher(cteacher);
        course.setCpath(cpath);
        course.setCtime(Integer.valueOf(ctime));
        course.setCmore(more);
        course.setClimit(limit);

        if (addmodel.addCourse(course)) {
            return 0;
        } else {
            return 1;
        }

    }

}
