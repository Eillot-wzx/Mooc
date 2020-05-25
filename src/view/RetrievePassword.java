package view;

import controller.RetrievePasswordController;
import utils.SendEmail;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RetrievePassword extends JFrame {

    /**
     * 密码找回窗口
     */

    //定义各种组件
    private JPanel contentPane;
    private JTextField idCard;
    private JTextField QQ;
    private JLabel messageIdCard;
    private JLabel messageQQ;
    private JButton retrievePassword;

    //获取controller对象
    RetrievePasswordController retrievePasswordController = new RetrievePasswordController();

    /**
     * 构造函数 对窗体进行初始化
     */
    public RetrievePassword() {

        //各种组件的设置
        setTitle("三月慕课密码找回");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(430, 380);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        messageQQ = new JLabel("请输入要找回用户的QQ:");
        messageQQ.setFont(new Font("宋体", Font.BOLD, 20));
        messageQQ.setBounds(100, 44, 300, 60);
        contentPane.add(messageQQ);

        messageIdCard = new JLabel("请输入要找回的用户的身份证号码:");
        messageIdCard.setFont(new Font("宋体", Font.BOLD, 20));
        messageIdCard.setBounds(50, 125, 340, 60);
        contentPane.add(messageIdCard);

        QQ = new JTextField();
        QQ.setBounds(90, 110, 230, 20);
        contentPane.add(QQ);

        idCard = new JTextField();
        idCard.setBounds(90, 200, 230, 20);
        contentPane.add(idCard);

        retrievePassword = new JButton("找回密码");
        retrievePassword.setFont(new Font("宋体", Font.BOLD, 18));
        retrievePassword.setBounds(130, 250, 150, 60);
        contentPane.add(retrievePassword);

        setVisible(true);

        //事件监听
        retrievePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cid = retrievePasswordController.retrievePassword(QQ.getText(), idCard.getText());
                switch (cid) {
                    case 0:
                        JOptionPane.showMessageDialog(null, "QQ号码与身份证号码不匹配", "", JOptionPane.ERROR_MESSAGE);
                        break;
                    case -1:
                        JOptionPane.showMessageDialog(null, "请完善注册信息", "", JOptionPane.ERROR_MESSAGE);
                        break;
                    case -2:
                        JOptionPane.showMessageDialog(null, "QQ不合法", "", JOptionPane.ERROR_MESSAGE);
                        QQ.setText("");
                        QQ.grabFocus();
                        break;
                    case -3:
                        JOptionPane.showMessageDialog(null, "身份证不合法", "", JOptionPane.ERROR_MESSAGE);
                        idCard.setText("");
                        idCard.grabFocus();
                        break;
                }
                if (cid > 0) {
                    int random = (int) ((Math.random() * 9 + 1) * 100000);
                    //System.out.println(random); //测试输出验证码
                    new SendEmail(QQ.getText() + "@qq.com", String.valueOf(random));//通过邮件进行发送验证码信息
                    String inputValue = JOptionPane.showInputDialog("我们已经向您的QQ邮箱邮箱中发送了一个验证码,请您输入验证码");
                    if (inputValue != null) {
                        while ((!inputValue.equals(String.valueOf(random)))) {
                            inputValue = JOptionPane.showInputDialog("验证码错误,请重新输入");
                            if (inputValue == null) {
                                inputValue = "";
                                break;
                            }
                        }
                        if (inputValue.equals(String.valueOf(random))) {
                            JOptionPane.showMessageDialog(null, "验证成功", "", JOptionPane.INFORMATION_MESSAGE);
                            String passWord1 = JOptionPane.showInputDialog("请输入您的新密码");
                            String passWord2 = "";
                            if (passWord1 != null) {
                                passWord2 = JOptionPane.showInputDialog("请在输入一次您的新密码");
                                int i = retrievePasswordController.newPassWord(passWord1, passWord2);
                                while (i != 0) {
                                    if (i == -2) {
                                        JOptionPane.showMessageDialog(null, "QQ不合法,请重新输入", "", JOptionPane.ERROR_MESSAGE);
                                    } else if (i == -1) {
                                        JOptionPane.showMessageDialog(null, "两次密码不相等,请重新输入", "", JOptionPane.ERROR_MESSAGE);
                                    }
                                    passWord1 = JOptionPane.showInputDialog("请输入您的新密码");
                                    if (passWord1 != null) {
                                        passWord2 = JOptionPane.showInputDialog("请在输入一次您的新密码");
                                    } else {
                                        break;
                                    }
                                    if (passWord2 == null) {
                                        break;
                                    }
                                    i = retrievePasswordController.newPassWord(passWord1, passWord2);
                                }
                                if (i == 0) {
                                    if (retrievePasswordController.updatePassword(cid, passWord2)) {
                                        JOptionPane.showMessageDialog(null, "修改成功", "", JOptionPane.INFORMATION_MESSAGE);
                                        dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "修改失败", "", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

}
