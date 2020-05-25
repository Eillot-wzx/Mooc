package controller;

import domain.Course;

import java.util.List;

public class StudentController extends AllObjController {

    /**
     * 判断学习记录表中时候有学习记录
     *
     * @param uid
     * @param cid
     * @return 有学习记录返回true
     */
    public boolean isStudy(int uid, int cid) {
        return selmodel.isStudy(uid, cid);
    }

    /**
     * 添加学习记录操作
     *
     * @param uid
     * @param cid
     * @return 添加成功返回true
     */
    public boolean addStudy(int uid, int cid) {
        return addmodel.addStudy(uid, cid);
    }

    /**
     * 查询当前用户学习课程
     *
     * @param uid
     * @param classificationObj
     * @return
     */
    public Object[][] selectStudyCourse(int uid, Object[][] classificationObj) {
        List<Course> courses = selmodel.selectStudyCourse(uid);
        return listCourseToObj(courses, classificationObj);
    }

    /**
     * 退选操作 删除学习记录表中的数据
     *
     * @param cid
     * @return 删除成功返回true
     */
    public boolean delStudy(int cid) {
        return delmodel.delStudy(cid);
    }

}
