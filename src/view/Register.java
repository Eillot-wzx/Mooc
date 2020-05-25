package view;

import controller.RegisterController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register extends JFrame {

    /**
     * 用户注册窗口
     * 功能同管理员的增加用户功能
     */

    //定义各种组件
    private JPanel contentPane;
    private JTextField username;
    private JTextField QQ;
    private JTextField IDCard;
    private JPasswordField password;
    private JPasswordField passwordAgain;
    private JLabel message;
    private JLabel fixedUsername;
    private JLabel fixedPassword;
    private JLabel fixedPasswordAgain;
    private JLabel fixedQQ;
    private JLabel fixedIdCard;
    private JButton register;
    //获取controller对象
    private RegisterController registerController = new RegisterController();

    /**
     * 构造函数 提供注册窗口
     */
    public Register() {

        //组件的属性设置
        setTitle("三月慕课注册");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(250, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setResizable(false);
        setContentPane(contentPane);

        fixedUsername = new JLabel("姓名:");
        fixedUsername.setBounds(30, 30, 40, 30);
        contentPane.add(fixedUsername);

        fixedPassword = new JLabel("密码:");
        fixedPassword.setBounds(30, 60, 40, 30);
        contentPane.add(fixedPassword);

        fixedPasswordAgain = new JLabel("确认密码:");
        fixedPasswordAgain.setBounds(30, 90, 60, 30);
        contentPane.add(fixedPasswordAgain);

        fixedQQ = new JLabel("QQ:");
        fixedQQ.setBounds(30, 120, 60, 30);
        contentPane.add(fixedQQ);

        fixedIdCard = new JLabel("身份证号:");
        fixedIdCard.setBounds(30, 150, 60, 30);
        contentPane.add(fixedIdCard);

        register = new JButton("立即注册");
        register.setBounds(60, 200, 120, 35);
        contentPane.add(register);

        username = new JTextField();
        username.setBounds(100, 30, 100, 30);
        contentPane.add(username);

        QQ = new JTextField();
        QQ.setBounds(100, 120, 100, 30);
        contentPane.add(QQ);

        IDCard = new JTextField();
        IDCard.setBounds(100, 150, 100, 30);
        contentPane.add(IDCard);

        password = new JPasswordField();
        password.setBounds(100, 60, 100, 30);
        contentPane.add(password);

        passwordAgain = new JPasswordField();
        passwordAgain.setBounds(100, 90, 100, 30);
        contentPane.add(passwordAgain);

        message = new JLabel("");
        message.setForeground(Color.RED);
        message.setBounds(20, 10, 200, 15);
        message.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(message);

        setVisible(true);

        // 添加事件监听
        username.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                message.setText("请输入正确的姓名");
            }
        });
        password.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                message.setText("请输入密码(6-16位数字与密码组合)");
            }
        });
        passwordAgain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                message.setText("请在输入一次密码");
            }
        });
        QQ.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                message.setText("请输入正确的QQ号");
            }
        });
        IDCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                message.setText("请输入正确的身份证号码");
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message.setText("");
                switch (registerController.register(username.getText(), String.valueOf(password.getPassword()), String.valueOf(passwordAgain.getPassword()), QQ.getText(), IDCard.getText()
                )) {
                    case -6:
                        JOptionPane.showMessageDialog(null, "姓名不合法()", "", JOptionPane.ERROR_MESSAGE);
                        username.setText("");
                        username.grabFocus();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "注册成功", "", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "您的信息已经注册过了,如果不是您本人操作,请及时联系管理员", "", JOptionPane.ERROR_MESSAGE);
                        break;
                    case -1:
                        JOptionPane.showMessageDialog(null, "请完善注册信息", "", JOptionPane.ERROR_MESSAGE);
                        break;
                    case -2:
                        JOptionPane.showMessageDialog(null, "两次密码不一致", "", JOptionPane.ERROR_MESSAGE);
                        password.setText("");
                        passwordAgain.setText("");
                        password.grabFocus();
                        break;
                    case -3:
                        JOptionPane.showMessageDialog(null, "密码不合法", "", JOptionPane.ERROR_MESSAGE);
                        password.setText("");
                        passwordAgain.setText("");
                        password.grabFocus();
                        break;
                    case -4:
                        JOptionPane.showMessageDialog(null, "QQ不合法", "", JOptionPane.ERROR_MESSAGE);
                        QQ.setText("");
                        QQ.grabFocus();
                        break;
                    case -5:
                        JOptionPane.showMessageDialog(null, "身份证不合法", "", JOptionPane.ERROR_MESSAGE);
                        IDCard.setText("");
                        IDCard.grabFocus();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "未知错误请联系管理员", "", JOptionPane.ERROR_MESSAGE);
                        dispose();
                }
            }
        });

    }
}
