package view;

import domain.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Student extends JFrame {

    /**
     * 学生用户的窗体
     */

    //定义各种组件
    private JPanel contentPane;
    private JTabbedPane tabbedPane;

    public Student(User user) {
        //各种属性的设置
        setTitle("三月慕课学生端");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 10, 780, 540);

        StudentCourse studentCourse = new StudentCourse(user);
        StudentUser studentUser = new StudentUser(user);
        tabbedPane.add("课程信息", studentCourse);
        tabbedPane.add("个人信息", studentUser);
        contentPane.add(tabbedPane);

        setVisible(true);
        //添加监听事件
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int i = tabbedPane.getSelectedIndex();
                if (i == 0) {
                    studentCourse.refresh();
                } else if (i == 1) {
                    studentUser.refresh();
                }
            }
        });
    }

}
