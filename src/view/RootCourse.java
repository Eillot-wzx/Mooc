package view;

import controller.RootController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RootCourse extends JPanel {
    /**
     * 用户课程信息管理面板
     */

    //定义面板上的各种组件
    private JComboBox comboBox;
    private JTextField textField;
    private JButton search;
    private JButton showAll;
    private JButton classifySearch;
    private JScrollPane scrollPane;
    private JTable table;
    private JTextField number;
    private JLabel numberSum;
    private JButton up;
    private JButton first;
    private JButton down;
    private JButton last;
    private JButton jump;
    private JButton addCourse;
    private JComboBox comboBox_classify;

    //定义面板中所需要的数据
    private int pageSum;
    private int pageNow;
    private int selection;//现行选中项

    //定义数据存储容器
    private Object[][] obj;
    private Object[][] classificationObj;

    RootController rootController = new RootController();

    public RootCourse() {
        //对于各种组件的属性设置
        setSize(760, 500);
        setLayout(null);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"智能搜索", "课程名", "教师"}));
        comboBox.setBounds(45, 20, 80, 20);
        add(comboBox);

        textField = new JTextField();
        textField.setBounds(135, 20, 230, 20);
        add(textField);

        search = new JButton("搜索");
        search.setBounds(380, 20, 80, 20);
        add(search);

        showAll = new JButton("搜索全部/刷新");
        showAll.setBounds(475, 20, 130, 20);
        add(showAll);

        addCourse = new JButton("增加课程");
        addCourse.setBounds(620, 20, 100, 20);
        add(addCourse);

        comboBox_classify = new JComboBox();
        comboBox_classify.setBounds(380, 55, 80, 20);
        add(comboBox_classify);

        classifySearch = new JButton("分类查找");
        classifySearch.setBounds(475, 55, 100, 20);
        add(classifySearch);

        scrollPane = new JScrollPane(null);
        scrollPane.setBounds(30, 90, 710, 330);
        add(scrollPane);

        table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setFont(new Font("宋体", Font.PLAIN, 24));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        showTable(new Object[][]{});
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        scrollPane.setViewportView(table);

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

        JPopupMenu popupMenu = new JPopupMenu();
        addPopup(table, popupMenu);

        JMenuItem showMoreCouse = new JMenuItem("查看详细信息");
        popupMenu.add(showMoreCouse);

        JMenuItem editCourse = new JMenuItem("编辑课程");
        popupMenu.add(editCourse);

        JMenuItem delCourseFalse = new JMenuItem("删除课程");
        popupMenu.add(delCourseFalse);

        //添加监听事件
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
                obj = rootController.selectPageCourse(pageNow, classificationObj);
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
                obj = rootController.selectPageCourse(pageNow, classificationObj);
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
                obj = rootController.selectPageCourse(pageNow, classificationObj);
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
                obj = rootController.selectPageCourse(pageNow, classificationObj);
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
                    obj = rootController.selectPageCourse(pageNow, classificationObj);
                    showTable(obj);
                } else {
                    JOptionPane.showMessageDialog(null, "您输入的数据不合法", "", JOptionPane.ERROR_MESSAGE);
                }
                number.setText(String.valueOf(pageNow));
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
                    if ("智能搜索".equals(comboBox.getSelectedItem())) {
                        obj = rootController.serchCourses(3, textField.getText(), classificationObj);
                    } else if ("课程名".equals(comboBox.getSelectedItem())) {
                        obj = rootController.serchCourses(0, textField.getText(), classificationObj);
                    } else if ("教师".equals(comboBox.getSelectedItem())) {
                        obj = rootController.serchCourses(1, textField.getText(), classificationObj);
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
        showAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        classifySearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                obj = rootController.serchCourses(2, "%" + String.valueOf(comboBox_classify.getSelectedIndex() + 1) + "%", classificationObj);
                showTable(obj);
            }
        });
        addCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            new AddCourse();
                        } catch (Exception e) {
                            e.printStackTrace();
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
        delCourseFalse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int cid = (int) table.getValueAt(selection, 0);
                if (rootController.delCourseFalse(cid)) {
                    JOptionPane.showMessageDialog(null, "删除成功", "", JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        editCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new EditCourse(rootController.searchCourseByCid((int) table.getValueAt(selection, 0)));
            }
        });
    }

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

    public void refresh() {
        pageSum = rootController.showLiveCoursePage();
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
        classificationObj = rootController.showClassify();
        showclassify(classificationObj);
        obj = rootController.selectPageCourse(pageNow, classificationObj);
        showTable(obj);
    }

    private void showTable(Object[][] obj) {
        table.setModel(new DefaultTableModel(obj, new String[]{"序号", "课程名称", "教师姓名", "分类", "课时", "开放情况"}));
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(180);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(267);
        table.getColumnModel().getColumn(4).setPreferredWidth(40);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
    }

    private void showclassify(Object[][] classificationObj) {
        String[] str = new String[classificationObj.length];
        for (int i = 0; i < classificationObj.length; i++) {
            str[i] = (String) classificationObj[i][1];
        }
        comboBox_classify.setModel(new DefaultComboBoxModel(str));
    }
}

