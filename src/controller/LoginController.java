package controller;

import domain.User;

public class LoginController extends AllObjController {

    /**
     * 登陆方法 登陆成功返回用户权限 0/1  QQ/密码为空返回-2 QQ格式错误返回-3 登陆失败返回-1
     *
     * @param QQ
     * @param password
     * @return
     */
    public int login(String QQ, String password) {
        if (!(isNotEmpty(QQ) && isNotEmpty(password))) return -2;
        if (!isQQ(QQ)) return -3;
        user = new User();
        user.setuQQ(QQ);
        user.setUpassword(password);
        return selmodel.login(user);
    }
}
