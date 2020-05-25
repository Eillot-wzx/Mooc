package controller;

import domain.Course;
import domain.User;
import model.AddModel;
import model.DelModel;
import model.SelModel;
import model.UpdModel;

import java.util.List;
import java.util.regex.Pattern;

public class AllObjController {

    User user = new User();
    Course course = new Course();

    AddModel addmodel = new AddModel();
    DelModel delmodel = new DelModel();
    SelModel selmodel = new SelModel();
    UpdModel updmodel = new UpdModel();

    /**
     * 判断是否为""
     *
     * @param str 传入的字符串
     * @return 非空返回True
     */
    public boolean isNotEmpty(String str) {
        if (str.equals("")) return false;
        return true;
    }

    /**
     * 判断是否为合法用户名
     *
     * @param str
     * @return
     */
    public boolean isName(String str) {
        if (Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z][\\u4e00-\\u9fa5a-zA-Z]+$").matcher(str).matches()) return true;
        return false;
    }

    /**
     * 判断是否为QQ
     *
     * @param str 传入的字符串
     * @return QQ返回True
     */
    public boolean isQQ(String str) {
        if (Pattern.compile("[1-9][0-9]{5,10}").matcher(str).matches()) return true;
        return false;
    }

    /**
     * 判断是否为合法密码
     *
     * @param str 传入的字符串
     * @return 合法返回true
     */
    public boolean isPassword(String str) {
        if (Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$").matcher(str)
                .matches()) return true;
        return false;
    }

    /**
     * 判断是否为身份证号码
     *
     * @param str 传入的字符串
     * @return 身份证号码返回true
     */
    public boolean isIdCard(String str) {

        if (Pattern.compile("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$").matcher(str)
                .matches()) return true;
        return false;
    }

    public Object[][] listCourseToObj(List<Course> courses, Object[][] classificationObj) {
        if (courses != null) {
            Object[][] obj = new Object[courses.size()][6];
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                obj[i][0] = course.getCid();
                obj[i][1] = course.getCname();
                obj[i][2] = course.getCteacher();
                String str = course.getClassify();
                String[] strs = str.split("`");
                String re = "|";

                for (int j = 0; j < strs.length; j++) {
                    for (int j2 = 0; j2 < classificationObj.length; j2++) {
                        if (Integer.valueOf(strs[j]) == Integer.valueOf(classificationObj[j2][0].toString())) {
                            re += (classificationObj[j2][1] + "|");
                            continue;
                        }
                    }
                }
                if (re.equals("|")) {
                    re = "";
                }
                obj[i][3] = re;
                obj[i][4] = course.getCtime();
                if (course.isClimit()) {
                    obj[i][5] = "开放";
                } else {
                    obj[i][5] = "关闭";
                }
            }
            return obj;
        }
        return null;
    }


}
