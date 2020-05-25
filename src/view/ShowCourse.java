package view;

import controller.RootController;
import domain.Course;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowCourse extends JFrame {

    /**
     * 展示课程详细信息窗口
     */
    //定义各种组件
    private JList classify;
    private JPanel contentPane;
    private JTextField cname;
    private JTextField cteacher;
    private JTextField cpath;
    private JTextField ctime;
    private JTextArea more;
    private JScrollPane jScrollPaneArea;
    private JScrollPane jScrollPaneList;
    private JComboBox limit;
    private JButton goOut;

    //定义数据容器
    private Object[][] classificationObj;

    //获取Controller对象
    RootController rootController = new RootController();

    public ShowCourse(Course course) {

        //初始化组件
        setTitle("查看详细信息");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label_name = new JLabel("课程名称");
        label_name.setBounds(50, 30, 60, 20);
        contentPane.add(label_name);

        cname = new JTextField(course.getCname());
        cname.setBounds(150, 30, 150, 20);
        contentPane.add(cname);

        JLabel label_teacher = new JLabel("教师名");
        label_teacher.setBounds(50, 70, 60, 20);
        contentPane.add(label_teacher);

        cteacher = new JTextField(course.getCteacher());
        cteacher.setBounds(150, 70, 150, 20);
        contentPane.add(cteacher);

        JLabel label_path = new JLabel("路径");
        label_path.setBounds(50, 110, 60, 20);
        contentPane.add(label_path);

        cpath = new JTextField(course.getCpath());
        cpath.setBounds(150, 110, 150, 20);
        add(cpath);

        JLabel label_classify = new JLabel("分类");
        label_classify.setBounds(50, 150, 60, 20);
        contentPane.add(label_classify);

        classify = new JList();
        classify.setBounds(150, 150, 80, 100);
        contentPane.add(classify);

        jScrollPaneList = new JScrollPane(classify);
        jScrollPaneList.setBounds(150, 150, 80, 100);
        contentPane.add(jScrollPaneList);

        JLabel label_more = new JLabel("详细信息");
        label_more.setBounds(50, 270, 60, 20);
        contentPane.add(label_more);

        more = new JTextArea(course.getCmore());
        more.setBounds(150, 270, 280, 150);
        more.setLineWrap(true);
        contentPane.add(more);

        jScrollPaneArea = new JScrollPane(more);
        jScrollPaneArea.setBounds(150, 270, 280, 150);
        contentPane.add(jScrollPaneArea);

        JLabel label_limit = new JLabel("开放情况");
        label_limit.setBounds(50, 440, 60, 20);
        contentPane.add(label_limit);

        limit = new JComboBox();
        limit.setModel(new DefaultComboBoxModel(new String[]{"开放", "关闭"}));
        limit.setBounds(150, 440, 60, 20);
        contentPane.add(limit);
        if (!course.isClimit()) {
            limit.setSelectedIndex(1);
        }

        JLabel label_time = new JLabel("课时");
        label_time.setBounds(300, 440, 60, 20);
        contentPane.add(label_time);

        ctime = new JTextField(String.valueOf(course.getCtime()));
        ctime.setBounds(340, 440, 60, 20);
        contentPane.add(ctime);

        goOut = new JButton("退出");
        goOut.setBounds(190, 500, 100, 30);
        contentPane.add(goOut);

        goOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        refresh(course);
        setVisible(true);
    }

    /**
     * 页面刷新
     *
     * @param course
     */
    public void refresh(Course course) {
        classificationObj = rootController.showClassify();
        showclassify(classificationObj, course);
        prohibitAll();
    }

    /**
     * 将组件全部设为不可编辑
     */
    private void prohibitAll() {
        cname.setEnabled(false);
        cteacher.setEnabled(false);
        cpath.setEnabled(false);
        classify.setEnabled(false);
        more.setEnabled(false);
        limit.setEnabled(false);
        ctime.setEnabled(false);
    }

    /**
     * 展示课程信息
     *
     * @param classificationObj
     * @param course
     */
    private void showclassify(Object[][] classificationObj, Course course) {
        String str = course.getClassify();
        String[] strs = str.split("`");
        String[] s = new String[strs.length];
        for (int j = 0; j < strs.length; j++) {
            for (int j2 = 0; j2 < classificationObj.length; j2++) {
                if (Integer.valueOf(strs[j]) == Integer.valueOf(classificationObj[j2][0].toString())) {
                    s[j] = classificationObj[j2][1] + "";
                }
            }
        }
        classify.setModel(new DefaultComboBoxModel(s));
    }
}
