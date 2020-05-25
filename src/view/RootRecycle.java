package view;

import controller.RootController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RootRecycle extends JPanel {

    private JTable table;
    private JButton recoverAll;
    private JButton delAll;
    private JTabbedPane tabbedPane;
    private RootRecycleUsers rootRecycleUsers;
    private RootRecycleCourse rootRecycleCourse;

    private RootController rootController = new RootController();

    public RootRecycle() {
        setSize(760, 500);
        setLayout(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 20, 750, 450);
        add(tabbedPane);

        rootRecycleUsers = new RootRecycleUsers();
        rootRecycleCourse = new RootRecycleCourse();

        tabbedPane.add("用户回收站", rootRecycleUsers);
        tabbedPane.add("课程回收站", rootRecycleCourse);

        recoverAll = new JButton("全部恢复");
        recoverAll.setBounds(510, 480, 100, 20);

        add(recoverAll);

        delAll = new JButton("全部删除");
        delAll.setBounds(630, 480, 100, 20);

        add(delAll);

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                refresh();
            }
        });
        delAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabbedPane.getSelectedIndex() == 0) {
                    if (rootController.delAllUsers()) {
                        JOptionPane.showMessageDialog(null, "删除成功", "", JOptionPane.INFORMATION_MESSAGE);
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败", "", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (tabbedPane.getSelectedIndex() == 1) {
                    if (rootController.delAllCourse()) {
                        JOptionPane.showMessageDialog(null, "删除成功", "", JOptionPane.INFORMATION_MESSAGE);
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        recoverAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabbedPane.getSelectedIndex() == 0) {
                    if (rootController.recoverAllUser()) {
                        JOptionPane.showMessageDialog(null, "恢复成功", "", JOptionPane.INFORMATION_MESSAGE);
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "恢复失败", "【出错啦】", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (tabbedPane.getSelectedIndex() == 1) {
                    if (rootController.recoverAllCourse()) {
                        JOptionPane.showMessageDialog(null, "恢复成功", "", JOptionPane.INFORMATION_MESSAGE);
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "恢复失败", "【出错啦】", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

    }

    public void refresh() {
        delAll.setEnabled(false);
        recoverAll.setEnabled(false);
        if (tabbedPane.getSelectedIndex() == 0) {
            rootRecycleUsers.refresh();
            if (rootController.isDelUsers()) {
                recoverAll.setEnabled(true);
                delAll.setEnabled(true);
            }
        } else if (tabbedPane.getSelectedIndex() == 1) {
            rootRecycleCourse.refresh();
            if (rootController.isDelCourse()) {
                recoverAll.setEnabled(true);
                delAll.setEnabled(true);
            }
        }
    }
}
