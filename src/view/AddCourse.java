package view;

import controller.RootController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class AddCourse extends JFrame {
    /**
     * 增加课程窗口
     */

    //定义各种组件
    private JList classify;
    private JList classifyNew;
    private JPanel contentPane;
    private JTextField cname;
    private JTextField cteacher;
    private JTextField cpath;
    private JTextField ctime;
    private JTextArea more;
    private JScrollPane jScrollPaneArea;
    private JScrollPane jScrollPaneList;
    private JScrollPane jScrollPaneListNew;
    private JButton into;
    private JButton out;
    private JButton refre;
    private JButton path;
    private JButton addclassify;
    private JComboBox limit;
    private JButton addCourse;
    private JPopupMenu popupMenu;

    //初始化数据容器
    private DefaultListModel<String> classificationModel;
    private DefaultListModel<String> classificationModelNow = new DefaultListModel<String>();
    //初始化选中位置
    private int classifySelected = -1;
    private int classifyNewSelected = -1;
    //定义Controller对象
    RootController rootController = new RootController();

    public AddCourse() {

        //对各种组件信息的赋值
        setTitle("添加课程");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label_name = new JLabel("课程名称");
        label_name.setBounds(50, 30, 60, 20);
        contentPane.add(label_name);

        cname = new JTextField();
        cname.setBounds(150, 30, 150, 20);
        contentPane.add(cname);

        JLabel label_teacher = new JLabel("教师名");
        label_teacher.setBounds(50, 70, 60, 20);
        contentPane.add(label_teacher);

        cteacher = new JTextField();
        cteacher.setBounds(150, 70, 150, 20);
        contentPane.add(cteacher);

        JLabel label_path = new JLabel("路径");
        label_path.setBounds(50, 110, 60, 20);
        contentPane.add(label_path);

        cpath = new JTextField();
        cpath.setBounds(150, 110, 150, 20);
        add(cpath);

        path = new JButton("...");
        path.setBounds(310, 110, 20, 20);
        contentPane.add(path);

        JLabel label_classify = new JLabel("分类");
        label_classify.setBounds(50, 150, 60, 20);
        contentPane.add(label_classify);

        classify = new JList();
        classify.setBounds(150, 150, 80, 100);
        classify.setSelectionMode(0);
        contentPane.add(classify);

        jScrollPaneList = new JScrollPane(classify);
        jScrollPaneList.setBounds(150, 150, 80, 100);
        contentPane.add(jScrollPaneList);

        popupMenu = new JPopupMenu();
        addPopup(classify, popupMenu);

        JMenuItem updateClasify = new JMenuItem("修改");
        popupMenu.add(updateClasify);

        JMenuItem delClassify = new JMenuItem("删除");
        popupMenu.add(delClassify);

        into = new JButton("->");
        into.setBounds(250, 160, 60, 20);
        contentPane.add(into);

        out = new JButton("<-");
        out.setBounds(250, 180, 60, 20);
        contentPane.add(out);

        refre = new JButton("还原");
        refre.setBounds(250, 200, 60, 20);
        contentPane.add(refre);

        addclassify = new JButton("新建");
        addclassify.setBounds(250, 220, 60, 20);
        contentPane.add(addclassify);

        classifyNew = new JList();
        classifyNew.setBounds(330, 150, 80, 100);
        classifyNew.setSelectionMode(0);
        contentPane.add(classifyNew);

        jScrollPaneListNew = new JScrollPane(classifyNew);
        jScrollPaneListNew.setBounds(330, 150, 80, 100);
        contentPane.add(jScrollPaneListNew);

        JLabel label_more = new JLabel("详细信息");
        label_more.setBounds(50, 270, 60, 20);
        contentPane.add(label_more);

        more = new JTextArea();
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

        JLabel label_time = new JLabel("课时");
        label_time.setBounds(300, 440, 60, 20);
        contentPane.add(label_time);

        ctime = new JTextField();
        ctime.setBounds(340, 440, 60, 20);
        contentPane.add(ctime);

        addCourse = new JButton("添加课程");
        addCourse.setBounds(190, 500, 100, 30);
        contentPane.add(addCourse);

        refresh();

        //添加监听事件
        path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.showDialog(new JLabel(), "选择课程路径");
                File file = jfc.getSelectedFile();
                if (file != null && file.isDirectory()) {
                    cpath.setText(file.getAbsolutePath());
                }
            }
        });
        updateClasify.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String str = classificationModel.getElementAt(classifySelected);
                String inputValue = JOptionPane.showInputDialog("请输入新的课程分类名字");
                if (inputValue != null) {
                    if (rootController.updclassification(str, inputValue)) {
                        JOptionPane.showMessageDialog(null, "更新成功", "", JOptionPane.INFORMATION_MESSAGE);
                        refresh();
                    } else {
                        System.out.println("更新失败");
                    }
                }
            }
        });
        delClassify.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String str = classificationModel.getElementAt(classifySelected);
                System.out.println(str);
                if (rootController.delClassification(str)) {
                    JOptionPane.showMessageDialog(null, "删除成功", "", JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        into.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (classifySelected != -1) {
                    String s = classificationModel.getElementAt(classifySelected);
                    classificationModel.remove(classifySelected);
                    classify.repaint();
                    classificationModelNow.addElement(s);
                    classifyNew.repaint();
                    classifySelected = -1;
                }
            }
        });
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (classifyNewSelected != -1) {
                    String s = classificationModelNow.getElementAt(classifyNewSelected);
                    classificationModelNow.remove(classifyNewSelected);
                    classifyNew.repaint();
                    classificationModel.addElement(s);
                    classify.repaint();
                    classifyNewSelected = -1;
                }
            }
        });
        classifyNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                classifyNewSelected = classifyNew.getSelectedIndex();
            }
        });
        addCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = true;
                if (limit.getSelectedIndex() == 1) {
                    b = false;
                }
                switch (rootController.addCourse(cname.getText(), cteacher.getText(), cpath.getText(), classificationModelNow, more.getText(), b, ctime.getText())) {
                    case -1:
                        JOptionPane.showMessageDialog(null, "请完善信息", "", JOptionPane.ERROR_MESSAGE);
                        break;
                    case -3:
                        JOptionPane.showMessageDialog(null, "教师姓名不正确", "", JOptionPane.ERROR_MESSAGE);
                        break;
                    case -4:
                        JOptionPane.showMessageDialog(null, "课时信息不正确", "", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "增加成功", "", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "课程信息已存在", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        refre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classificationModelNow = new DefaultListModel<>();
                refresh();
            }
        });
        addclassify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputValue = JOptionPane.showInputDialog("请输入课程分类名字");
                if (inputValue != null) {
                    if (rootController.addClassify(inputValue)) {
                        JOptionPane.showMessageDialog(null, "添加成功", "", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //设置窗体可见
        setVisible(true);

    }

    /**
     * 页面刷新操作
     */
    public void refresh() {
        classificationModel = objToListModel(rootController.showClassify());
        classify.setModel(classificationModel);
        classifyNew.setModel(classificationModelNow);
    }

    /**
     * 数组对象转换为Model对象
     *
     * @param classificationObj
     * @return
     */
    public DefaultListModel<String> objToListModel(Object[][] classificationObj) {
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < classificationObj.length; i++) {
            model.addElement((String) classificationObj[i][1]);
        }
        return model;
    }

    /**
     * 添加右键菜单方法,此方法多次被调用,可以提取
     * 由于不知道该提取到什么位置最合适,所以此处不进行提取操作
     * @param component
     * @param popup
     */
    private void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                int i = e.getButton();
                if (i == MouseEvent.BUTTON1) {
                    classifySelected = classify.getSelectedIndex();
                }
                if (i == MouseEvent.BUTTON3) {
                    classifySelected = classify.getSelectedIndex();
                    if (classifySelected != -1) {
                        showMenu(e);
                    }
                }
            }
        });
    }

}
