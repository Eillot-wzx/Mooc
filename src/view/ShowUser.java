package view;

import domain.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class ShowUser extends JFrame {

    /**
     * 学生详细信息页面 构造函数传进user对象 对user对象进行处理
     * 其中处理最频繁的为身份证属性
     * 进行详细信息展示
     */

    //定义组件 此窗口只做展示目的 不对数据库进行操作 所以不定义controller对象
    private JPanel contentPane;

    /**
     * 构造函数 对user对象处理
     * 展示详细信息
     *
     * @param user
     */
    public ShowUser(User user) {
        //组件初始化
        setTitle("查看详细信息");
        setSize(300, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("姓名:" + user.getUname());
        label.setBounds(50, 40, 200, 15);
        contentPane.add(label);

        JLabel lblQq = new JLabel("QQ号:" + user.getuQQ());
        lblQq.setBounds(50, 160, 200, 15);
        contentPane.add(lblQq);

        String idCard = user.getUidCard();

        JLabel label_3 = new JLabel("身份证号:" + idCard);
        label_3.setBounds(50, 190, 300, 15);
        contentPane.add(label_3);

        Calendar a = Calendar.getInstance();
        int years = (a.get(Calendar.YEAR)) - (Integer.valueOf(idCard.substring(6, 10)));
        JLabel label_1 = new JLabel("年龄:" + years);
        label_1.setBounds(50, 100, 200, 15);
        contentPane.add(label_1);

        String limit = "";
        if (user.isUlimit()) {
            limit = "管理员";
        } else {
            limit = "学生";
        }

        JLabel label_4 = new JLabel("权限:" + limit);
        label_4.setBounds(50, 220, 200, 15);
        contentPane.add(label_4);

        int sex = Integer.valueOf(idCard.substring(16, 17));
        String sexs;
        if (sex % 2 == 0) {
            sexs = "女";
        } else {
            sexs = "男";
        }

        JLabel label_5 = new JLabel("性别:" + sexs);
        label_5.setBounds(50, 70, 200, 15);
        contentPane.add(label_5);

        String birthday = idCard.substring(10, 12) + "月" + idCard.substring(12, 14) + "日";

        JLabel label_2 = new JLabel("生日:" + birthday);
        label_2.setBounds(50, 130, 200, 15);
        contentPane.add(label_2);

        JButton button = new JButton("退出");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        button.setBounds(90, 270, 100, 20);
        contentPane.add(button);

        setVisible(true);
    }

}