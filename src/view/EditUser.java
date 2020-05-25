package view;

import controller.RootController;
import domain.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUser extends JFrame {

    /**
     * 编辑用户窗口
     * 可对选定的user对象进行编辑
     */

    //定义各种组件
    private JPanel contentPane;
    private JTextField username;
    private JTextField QQ;
    private JTextField idCard;
    private JComboBox comboBox;

    //获取controller对象
    private RootController rootController = new RootController();

    /**
     * 构造函数 对user对象进行编辑
     *
     * @param user
     */
    public EditUser(User user) {

        //各种组件的属性设置
        setTitle("编辑用户");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 320);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label_name = new JLabel("姓名:");
        label_name.setBounds(50, 60, 40, 30);
        contentPane.add(label_name);

        username = new JTextField(user.getUname());
        username.setBounds(120, 60, 130, 30);
        contentPane.add(username);

        JLabel label_QQ = new JLabel("QQ:");
        label_QQ.setBounds(50, 90, 60, 30);
        contentPane.add(label_QQ);

        QQ = new JTextField(String.valueOf(user.getuQQ()));
        QQ.setColumns(10);
        QQ.setBounds(120, 90, 130, 30);
        contentPane.add(QQ);

        JLabel label_idCard = new JLabel("身份证号:");
        label_idCard.setBounds(50, 120, 60, 30);
        contentPane.add(label_idCard);

        idCard = new JTextField(user.getUidCard());
        idCard.setBounds(120, 120, 130, 30);
        contentPane.add(idCard);

        JLabel label_limit = new JLabel("权限:");
        label_limit.setBounds(50, 160, 60, 30);
        contentPane.add(label_limit);

        comboBox = new JComboBox();
        comboBox.setBounds(120, 160, 80, 25);

        comboBox.setModel(new DefaultComboBoxModel(new String[]{"学生", "管理员"}));
        if (user.isUlimit()) {
            comboBox.setSelectedIndex(1);
        }
        contentPane.add(comboBox);

        JButton editUser = new JButton("确认修改");
        editUser.setBounds(100, 220, 100, 25);
        contentPane.add(editUser);

        setVisible(true);

        //添加监听事件
        editUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean limit;
                if (comboBox.getSelectedIndex() == 0) {
                    limit = false;
                } else {
                    limit = true;
                }
                switch (rootController.editUser(user.getUid(), username.getText(), QQ.getText(), idCard.getText(), limit)) {
                    case -1:
                        JOptionPane.showMessageDialog(null, "请完善注册信息", "", JOptionPane.ERROR_MESSAGE);
                        break;
                    case -2:
                        JOptionPane.showMessageDialog(null, "QQ不合法", "", JOptionPane.ERROR_MESSAGE);
                        QQ.setText("");
                        QQ.grabFocus();
                        break;
                    case -3:
                        JOptionPane.showMessageDialog(null, "身份证不合法", "", JOptionPane.ERROR_MESSAGE);
                        idCard.setText("");
                        idCard.grabFocus();
                        break;
                    case -4:
                        JOptionPane.showMessageDialog(null, "用户名不合法", "", JOptionPane.ERROR_MESSAGE);
                        username.setText("");
                        username.grabFocus();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "修改成功", "", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "QQ号或身份证冲突", "", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }
}
