package view;

import controller.LoginController;
import controller.RootController;
import domain.User;
import utils.DES;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;


public class Login extends JFrame {

    /**
     * 登陆页面
     * 理论上是所有的窗口的起始窗口
     * 提供登陆的基本操作
     */

    //定义各种组件
    private JPanel contentPane;
    private JTextField QQ;
    private JPasswordField password;
    private JButton login;
    private JButton register;
    private JButton losePassword;
    private JLabel message;
    private JLabel lblQQ;
    private JLabel lblPassword;
    private JCheckBox remberPassword;
    //获取controller对象
    private LoginController loginController = new LoginController();

    /**
     * 构造函数 属性的基本设置
     */
    public Login() {

        // 定义一个数组 0为帐号 1为密码
        String[] str = new String[2];

        // 读取本地文件 初始化数据
        initData(str);

        //各种组件的设置
        setTitle("三月慕课");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        message = new JLabel("三月慕课");
        message.setFont(new Font("汉仪综艺体简", Font.PLAIN, 33));
        message.setBounds(110, 10, 160, 50);
        contentPane.add(message);

        lblQQ = new JLabel("QQ号:");
        lblQQ.setBounds(50, 80, 40, 20);
        contentPane.add(lblQQ);

        lblPassword = new JLabel("密码:");
        lblPassword.setBounds(50, 120, 40, 20);
        contentPane.add(lblPassword);

        QQ = new JTextField(str[0]);
        QQ.setBounds(90, 80, 80, 20);
        contentPane.add(QQ);

        password = new JPasswordField(str[1]);
        password.setBounds(90, 120, 80, 20);
        contentPane.add(password);

        register = new JButton("注册");
        register.setBounds(200, 75, 90, 30);
        contentPane.add(register);

        losePassword = new JButton("找回密码");
        losePassword.setBounds(200, 115, 90, 30);
        contentPane.add(losePassword);

        //如果读取的帐号信息不为空 就选中记住密码
        remberPassword = new JCheckBox("记住密码");
        if (!str[1].equals("")) {
            remberPassword.setSelected(true);
        }
        remberPassword.setBounds(170, 155, 120, 25);
        contentPane.add(remberPassword);

        login = new JButton("登陆");
        login.setBounds(70, 200, 200, 40);
        contentPane.add(login);

        setVisible(true);
        //添加监听事件
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            new Register();
                        } catch (Exception e) {

                        }
                    }
                });
            }
        });
        losePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            new RetrievePassword();
                        } catch (Exception e) {

                        }
                    }
                });
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = loginController.login(QQ.getText(), String.valueOf(password.getPassword()));
                //登陆成功则写入帐号密码信息 并初始化新窗口
                if (i > -1) {
                    str[0] = QQ.getText();
                    if (remberPassword.isSelected()) {
                        str[1] = String.valueOf(password.getPassword());
                    } else {
                        str[1] = "";
                    }
                    writeData(str);
                    if (i == 1) {
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                try {
                                    new Root();
                                } catch (Exception e) {

                                }
                            }
                        });
                    } else {
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                try {
                                    RootController rootController=new RootController();
                                    User user=rootController.searchUsersByQQ(QQ.getText());
                                    new Student(user);
                                } catch (Exception e) {

                                }
                            }
                        });
                    }
                    dispose();
                } else if (i == -2) {
                    JOptionPane.showMessageDialog(null, "请完善登陆信息", "", JOptionPane.ERROR_MESSAGE);
                } else if (i == -3) {
                    JOptionPane.showMessageDialog(null, "QQ号码不合法", "", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "QQ号密码错误", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * 将账号密码信息写到本地文件
     *
     * @param str str[0]==帐号 srt[1]==密码
     */
    private void writeData(String[] str) {
        try {
            Properties pps = new Properties();
            pps.setProperty("loginUser", str[0]);
            pps.setProperty("loginPass", DES.encrypt(str[1]));
            FileWriter fw = new FileWriter(".//src//login.properties");
            pps.store(fw, "");
        } catch (IOException e) {
            System.out.println("本地写入帐号密码失败");
        }

    }

    /**
     * 初始化数据 读取本地保存的帐号密码信息
     *
     * @param str 读取的信息存储在str数组中 str[0]==帐号 str[1]==密码
     */
    private void initData(String[] str) {

        try {
            Properties pro = new Properties();
            FileReader fr = new FileReader(".//src//login.properties");
            pro.load(fr);
            str[0] = pro.getProperty("loginUser");
            if (pro.getProperty("loginPass").equals("")){
                str[1] = pro.getProperty("loginPass");
            }else{
                str[1] = DES.decrypt(pro.getProperty("loginPass"));
            }
            fr.close();
        } catch (IOException e) {
            System.out.println("读取本地帐号密码失败");
        }
    }

}
