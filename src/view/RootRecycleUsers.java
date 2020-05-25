package view;

import controller.RootController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RootRecycleUsers extends JPanel {

    /**
     * 用户回收站-用户管理面板
     * 可对软删除的用户进行管理 包括删除与恢复操作
     */

    private JScrollPane scrollPane;
    private JTable table;

    private int selection;//现行选中项
    private Object[][] obj;//用于展示数据的数组

   RootController rootController = new RootController();

    public RootRecycleUsers() {
        setSize(750, 400);
        setLayout(null);
        scrollPane = new JScrollPane((Component) null);
        scrollPane.setBounds(10, 10, 723, 400);
        add(scrollPane);

        table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("宋体", Font.PLAIN, 24));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        scrollPane.setViewportView(table);

        JPopupMenu popupMenu = new JPopupMenu();
        addPopup(table, popupMenu);

        JMenuItem trueAll = new JMenuItem("彻底删除");
        popupMenu.add(trueAll);

        JMenuItem recover = new JMenuItem("恢复");
        popupMenu.add(recover);

        //监听事件
        recover.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (rootController.recoverUser((int) table.getValueAt(selection, 0))) {
                    JOptionPane.showMessageDialog(null, "恢复成功", "", JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, "恢复失败", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        trueAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (rootController.delUser((int)table.getValueAt(selection, 0))) {
                    JOptionPane.showMessageDialog(null, "删除成功", "", JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                }else {
                    JOptionPane.showMessageDialog(null, "删除失败", "【出错啦】", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void showTable(Object[][] obj) {
        table.setModel(new DefaultTableModel(obj, new String[]{"序号", "姓名", "QQ", "身份证号码", "权限"}));
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(220);
        table.getColumnModel().getColumn(4).setPreferredWidth(140);
    }

    public void refresh() {
        obj = rootController.selectDelUser();
        showTable(obj);
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
}