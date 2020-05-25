package controller;

import domain.User;

public class RegisterController extends AllObjController {

    /**
     * 注册用户方法 由view层调用 若数据合法 model层执行增添数据
     *
     * @param username
     * @param password
     * @param passwordAgain
     * @param QQ
     * @param idCard
     * @return 0注册成功 1注册失败 -1完善注册信息
     * -2两次密码不匹配 -3 密码不合法 -4QQ不合法 -5身份证不合法 -6姓名不合法
     */

    public int register(String username, String password, String passwordAgain, String QQ, String idCard) {
        if (!(isNotEmpty(username) && isNotEmpty(password) && isNotEmpty(passwordAgain) && isNotEmpty(QQ) && isNotEmpty(idCard)))
            return -1;
        if (!password.equals(passwordAgain)) return -2;
        if (!isPassword(password)) return -3;
        if (!isQQ(QQ)) return -4;
        if (!isIdCard(idCard)) return -5;
        if (!isName(username)) return -6;
        user = new User();
        user.setUname(username);
        user.setUpassword(password);
        user.setuQQ(QQ);
        user.setUidCard(idCard);
        if (addmodel.addUser(user)) return 0;
        else return -1;
    }

}
