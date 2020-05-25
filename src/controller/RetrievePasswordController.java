package controller;

import domain.User;

public class RetrievePasswordController extends AllObjController {
    /**
     * 找回密码方法 验证传入的数据的合法性
     * 若数据合法 则返回数据在数据库的cid值
     *
     * @param QQ
     * @param idCard
     * @return 数据合法返回cid -1数据不全 -2QQ不合法 -3身份证不合法
     */
    public int retrievePassword(String QQ, String idCard) {
        if (!(isNotEmpty(QQ) && isNotEmpty(idCard))) return -1;
        if (!isQQ(QQ)) return -2;
        if (!isIdCard(idCard)) return -3;
        user = new User();
        user.setuQQ(QQ);
        user.setUidCard(idCard);
        return selmodel.retrievePassword(user);
    }

    /**
     * 判断是否为新密码
     *
     * @param passWord1
     * @param passWord2
     * @return 若两次密码不相等 返回-1 密码不合法 返回-2 校验通过返回0
     */
    public int newPassWord(String passWord1, String passWord2) {
        if (!passWord1.equals(passWord2)) return -1;
        if (!isPassword(passWord1)) return -2;
        return 0;
    }

    /**
     * 讲传进来的参数封装为User对象 调用model层进行密码更新操作
     *
     * @param cid
     * @param newPassword
     * @return 更新成功返回true
     */
    public boolean updatePassword(int cid, String newPassword) {
        user = new User();
        user.setUid(cid);
        user.setUpassword(newPassword);
        if (updmodel.updatePassword(user)) return true;
        else return false;
    }
}
