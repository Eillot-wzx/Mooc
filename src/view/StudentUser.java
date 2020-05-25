package view;

import controller.RootController;
import controller.StudentController;
import domain.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class StudentUser extends JPanel {

    /**
     * 个人信息面板
     */
    //定义面板中的组件
    private JScrollPane scrollPane;
    private JTable table;
    private int selection;//现行选中项

    //定义数据容器
    private Object[][] obj;
    private Object[][] classificationObj;

    //获得Controller对象
    StudentController studentController = new StudentController();
    RootController rootController = new RootController();

    User user = null;

    public StudentUser(User usernew) {
        user = usernew;
        //对于各种组件的属性设置
        setSize(760, 500);
        setLayout(null);

        JLabel label_name = new JLabel("姓名:" + user.getUname());
        label_name.setBounds(30, 25, 100, 15);
        add(label_name);

        String idCard = user.getUidCard();

        int sex = Integer.valueOf(idCard.substring(16, 17));
        String sexs;
        if (sex % 2 == 0) {
            sexs = "女";
        } else {
            sexs = "男";
        }

        JLabel label_sex = new JLabel("性别:" + sexs);
        label_sex.setBounds(190, 25, 100, 15);
        add(label_sex);

        Calendar a = Calendar.getInstance();
        int years = (a.get(Calendar.YEAR)) - (Integer.valueOf(idCard.substring(6, 10)));
        JLabel label_year = new JLabel("年龄:" + years);
        label_year.setBounds(350, 25, 100, 15);
        add(label_year);

        JLabel lblQq = new JLabel("QQ号:" + user.getuQQ());
        lblQq.setBounds(30, 60, 120, 15);
        add(lblQq);

        JLabel label_idCard = new JLabel("身份证号:" + user.getUidCard().substring(0,user.getUidCard().length()-8)+"********");
        label_idCard.setBounds(190, 60, 180, 15);
        add(label_idCard);

        JButton retrievePassword = new JButton("修改密码");
        retrievePassword.setBounds(600, 20, 100, 25);
        add(retrievePassword);

        JLabel label_message = new JLabel("已选修的课程:");
        label_message.setBounds(30, 95, 130, 15);
        add(label_message);

        JScrollPane scrollPane = new JScrollPane((Component) null);
        scrollPane.setBounds(25, 130, 735, 350);
        add(scrollPane);

        table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setFont(new Font("宋体", Font.PLAIN, 24));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        showTable(new Object[][]{});
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        scrollPane.setViewportView(table);

        JPopupMenu popupMenu = new JPopupMenu();
        addPopup(table, popupMenu);

        JMenuItem showMoreCouse = new JMenuItem("查看详细信息");
        popupMenu.add(showMoreCouse);

        JMenuItem study = new JMenuItem("学习");
        popupMenu.add(study);

        JMenuItem delStudy = new JMenuItem("退选");
        popupMenu.add(delStudy);

        refresh();
        //添加监听事件
        retrievePassword.addActionListener(new ActionListener() {
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
        delStudy.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (studentController.delStudy((int) table.getValueAt(selection, 0))) {
                    JOptionPane.showMessageDialog(null, "退选成功", "", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "退选失败", "", JOptionPane.ERROR_MESSAGE);
                }
                refresh();
            }
        });
        study.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            PlayerMain.Start(rootController.searchCourseByCid((int) table.getValueAt(selection, 0)));
                        } catch (Exception e) {
                        }
                    }
                });
            }
        });
        showMoreCouse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            new ShowCourse(rootController.searchCourseByCid((int) table.getValueAt(selection, 0)));
                        } catch (Exception e) {
                        }
                    }
                });
            }
        });
    }

    /**
     * 右键菜单
     *
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
                    selection = table.getSelectedRow();
                }
                if (i == MouseEvent.BUTTON3) {
                    selection = table.getSelectedRow();
                    if (selection != -1) {
                        showMenu(e);
                    }
                }
            }
        });
    }

    /**
     * 将数组中的数据展示到Jlist中
     *
     * @param obj
     */
    private void showTable(Object[][] obj) {
        table.setModel(new DefaultTableModel(obj, new String[]{"序号", "课程名称", "教师姓名", "分类", "课时", "开放情况"}));
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(180);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(267);
        table.getColumnModel().getColumn(4).setPreferredWidth(65);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
    }

    /**
     * 页面刷新
     */
    public void refresh() {
        classificationObj = rootController.showClassify();
        obj = studentController.selectStudyCourse(user.getUid(), classificationObj);
        showTable(obj);
    }
}
