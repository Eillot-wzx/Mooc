package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Root extends JFrame {

    /**
     * 管理端的主页面
     * 根据功能的不同 分别调用三个不同的面板
     */

    //定义各种组件
    private JPanel contentPane;
    private JTabbedPane tabbedPane;

    /**
     * 构造函数 初始化一个窗体对象
     */
    public Root() {

        //各种属性的设置
        setTitle("三月慕课管理端");
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

        RootUsers rootUsers = new RootUsers();
        RootCourse rootCourse = new RootCourse();
        RootRecycle rootRecycle = new RootRecycle();


        tabbedPane.add("用户信息", rootUsers);
        tabbedPane.add("课程信息", rootCourse);
        tabbedPane.add("回收站", rootRecycle);

        contentPane.add(tabbedPane);

        setVisible(true);
        //添加监听事件
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int i = tabbedPane.getSelectedIndex();
                if (i == 0) {
                    rootUsers.refresh();
                } else if (i == 1) {
                    rootCourse.refresh();
                }else if (i==2){
                    rootRecycle.refresh();
                }
            }
        });
    }

}


