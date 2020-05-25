package view;

import controller.RootController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RootUsers extends JPanel {

    /**
     * 管理端第一个面板-用户管理面板
     * 提供对于用户个人信息的操作
     */

    //定义各种组件
    private JTextField textField;
    private JTable table;
    private JButton showAll;
    private JTextField number;
    private JLabel numberSum;
    private JButton up;
    private JButton first;
    private JButton down;
    private JButton last;
    private JButton jump;
    private JButton search;
    private JButton addUser;
    private JComboBox comboBox;
    private JScrollPane scrollPane;
    private JPopupMenu popupMenu;

    //定义需要的全局变量
    private int pageSum;//总页数
    private int pageNow;//当前页数
    private int selection;//现行选中项
    private Object[][] obj;//用于展示数据的数组

    //获取controller对象
    RootController rootController = new RootController();

    /**
     * 用户管理面板 构造函数
     */
    public RootUsers() {

        //对于各种组件的属性设置
        setSize(760, 500);
        setLayout(null);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"姓名", "QQ号", "身份证号"}));
        comboBox.setBounds(40, 30, 80, 20);
        add(comboBox);

        textField = new JTextField();
        textField.setBounds(150, 30, 200, 20);
        add(textField);

        search = new JButton("搜索");
        search.setBounds(370, 30, 80, 20);
        add(search);

        showAll = new JButton("查看全部/刷新");
        showAll.setBounds(490, 30, 120, 20);
        add(showAll);

        addUser = new JButton("添加成员");
        addUser.setBounds(630, 30, 100, 20);
        add(addUser);

        scrollPane = new JScrollPane(null);
        scrollPane.setBounds(30, 90, 710, 330);
        add(scrollPane);

        table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("宋体", Font.PLAIN, 24));
        showTable(new Object[][]{});
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        scrollPane.setViewportView(table);

        popupMenu = new JPopupMenu();
        addPopup(table, popupMenu);

        JMenuItem showUserMore = new JMenuItem("查看详细信息");
        popupMenu.add(showUserMore);

        JMenuItem editUser = new JMenuItem("编辑资料");
        popupMenu.add(editUser);

        JMenuItem delUser = new JMenuItem("删除成员");
        popupMenu.add(delUser);

        first = new JButton("首页");
        first.setEnabled(false);
        first.setBounds(40, 450, 100, 30);
        add(first);

        up = new JButton("上一页");
        up.setEnabled(false);
        up.setBounds(150, 450, 100, 30);
        add(up);

        down = new JButton("下一页");
        down.setEnabled(false);
        down.setBounds(260, 450, 100, 30);
        add(down);

        last = new JButton("尾页");
        last.setEnabled(false);
        last.setBounds(370, 450, 100, 30);
        add(last);

        numberSum = new JLabel("/     页数");
        numberSum.setBounds(550, 450, 70, 30);
        add(numberSum);

        number = new JTextField();
        number.setHorizontalAlignment(SwingConstants.CENTER);
        number.setEnabled(false);
        number.setBounds(500, 450, 40, 30);
        add(number);

        jump = new JButton("跳转");
        jump.setEnabled(false);
        jump.setBounds(630, 450, 100, 30);
        add(jump);

        refresh();

        //事件监听
        number.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                number.setText("");
            }
        });
        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up.setEnabled(false);
                down.setEnabled(true);
                pageNow = 1;
                number.setText(String.valueOf(pageNow));
                obj = rootController.selectPageUser(pageNow);
                showTable(obj);
            }
        });
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageNow--;
                down.setEnabled(true);
                Object[][] objNew;
                if (pageNow == 1) {
                    up.setEnabled(false);
                }
                number.setText(String.valueOf(pageNow));
                obj = rootController.selectPageUser(pageNow);
                showTable(obj);
            }
        });
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageNow++;
                up.setEnabled(true);
                if (pageNow == pageSum) {
                    down.setEnabled(false);
                }
                number.setText(String.valueOf(pageNow));
                obj = rootController.selectPageUser(pageNow);
                showTable(obj);
            }
        });
        last.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                down.setEnabled(false);
                up.setEnabled(true);
                pageNow = pageSum;
                number.setText(String.valueOf(pageNow));
                obj = rootController.selectPageUser(pageNow);
                showTable(obj);
            }
        });
        jump.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rootController.isTruePage(number.getText(), pageSum)) {
                    pageNow = Integer.valueOf(number.getText());
                    up.setEnabled(true);
                    down.setEnabled(true);
                    if (pageNow == 1) {
                        up.setEnabled(false);
                    }
                    if (pageNow == pageSum) {
                        down.setEnabled(false);
                    }
                    obj = rootController.selectPageUser(pageNow);
                    showTable(obj);
                } else {
                    JOptionPane.showMessageDialog(null, "您输入的数据不合法", "", JOptionPane.ERROR_MESSAGE);
                }
                number.setText(String.valueOf(pageNow));
            }
        });
        showAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            new AddUser();
                        } catch (Exception e) {
                        }
                    }
                });
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("")) {
                    first.setEnabled(false);
                    up.setEnabled(false);
                    down.setEnabled(false);
                    last.setEnabled(false);
                    number.setEnabled(false);
                    jump.setEnabled(false);
                    pageNow = 1;
                    pageSum = 1;
                    numberSum.setText("/  " + pageSum + " 页数");
                    number.setText(String.valueOf(pageNow));
                    if ("姓名".equals(comboBox.getSelectedItem())) {
                        obj = rootController.searchUsers(0, textField.getText());
                    } else if ("QQ号".equals(comboBox.getSelectedItem())) {
                        obj = rootController.searchUsers(1, textField.getText());
                    } else if ("身份证号".equals(comboBox.getSelectedItem())) {
                        obj = rootController.searchUsers(2, textField.getText());
                    }
                    if (obj.length == 0) {
                        JOptionPane.showMessageDialog(null, "无记录", "", JOptionPane.ERROR_MESSAGE);
                    }
                    showTable(obj);

                } else {
                    JOptionPane.showMessageDialog(null, "您输入的数据不合法", "", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        delUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int cid = (int) table.getValueAt(selection, 0);
                if (rootController.delUesrFalse(cid)) {
                    JOptionPane.showMessageDialog(null, "删除成功", "", JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        showUserMore.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            new ShowUser(rootController.searchUsersByUid((int) table.getValueAt(selection, 0)));
                        } catch (Exception e) {
                        }
                    }
                });
            }
        });
        editUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            new EditUser(rootController.searchUsersByUid((int) table.getValueAt(selection, 0)));
                        } catch (Exception e) {
                        }
                    }
                });

            }
        });
    }

    /**
     * 刷新方法 构造方法中调用一次,对数据操作后需要调用
     */
    public void refresh() {
        pageSum = rootController.showLiveUserPage();
        numberSum.setText("/  " + pageSum + " 页数");
        pageNow = 1;
        up.setEnabled(false);
        if (pageSum > pageNow) {
            first.setEnabled(true);
            down.setEnabled(true);
            last.setEnabled(true);
            number.setEnabled(true);
            jump.setEnabled(true);
        }
        number.setText(String.valueOf(pageNow));
        obj = rootController.selectPageUser(pageNow);
        showTable(obj);
    }

    /**
     * 展示列表方法 数据刷新后 刷新列表
     *
     * @param obj 需要刷新的数据
     */
    private void showTable(Object[][] obj) {
        table.setModel(new DefaultTableModel(obj, new String[]{"序号", "姓名", "QQ", "身份证号码", "权限"}));
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(250);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    /**
     * 右键小菜单的绑定 与功能设置
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

}
