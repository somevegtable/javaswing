package admin.buttonclick;

import admin.OldInfoManageShow;
import admin.PlaceManageShow;
import com.mysql.cj.util.StringUtils;
import dao.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class AddBedShow {
    public static void createShow(){
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("添加床位信息");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //创建床位号标签
        JLabel codeLabel = new JLabel("床位号：");
        codeLabel.setFont(new Font("微软雅黑", 0, 13));
        codeLabel.setBounds(180, 100, 110, 25);
        panel.add(codeLabel);

        //创建床位号输入文字域
        JTextField codeText = new JTextField(20);
        codeText.setBounds(250, 100, 165, 25);
        panel.add(codeText);

        //创建状态标签
        JLabel statusLabel = new JLabel("状态：");
        statusLabel.setFont(new Font("微软雅黑", 0, 13));
        statusLabel.setBounds(180, 150, 110, 25);
        panel.add(statusLabel);

        //创建性别下拉框
        JComboBox<String> statusText = new JComboBox<>();//创建一个下拉列表
        statusText.addItem("占用");
        statusText.addItem("空闲");
        statusText.setFont(new Font("微软雅黑", 0, 13));
        statusText.setBounds(250, 150, 100, 25);
        panel.add(statusText);

        //创建占用者标签
        JLabel holderLabel = new JLabel("占用者：");
        holderLabel.setFont(new Font("微软雅黑", 0, 13));
        holderLabel.setBounds(180, 200, 110, 25);
        panel.add(holderLabel);

        //创建占用者输入文字域
        JTextField holderText = new JTextField(20);
        holderText.setBounds(250, 200, 165, 25);
        panel.add(holderText);


        JButton confirm = new JButton("添加");
        confirm.setBounds(250, 400, 80, 25);
        panel.add(confirm);
        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(StringUtils.isNullOrEmpty(codeText.getText()) ||
                   StringUtils.isNullOrEmpty(statusText.getSelectedItem().toString())){
                    JOptionPane.showMessageDialog(null, "参数不能为空！", "失败", 0);
                }else{
                    int check = JOptionPane.showConfirmDialog(null, "确定添加该用户吗?", "添加",JOptionPane.YES_NO_OPTION);//返回的是按钮的index  i=0或者1
                    if (check == 0) {
                        DAO dao = new DAO();
                        Integer row = dao.update("insert into t_bed(code, status, holder)" +
                                "values('"+codeText.getText()+"', '"+statusText.getSelectedItem().toString()+"', '"+holderText.getText()+"')");
                        JOptionPane.showMessageDialog(null, "添加成功", "成功", 1);
                    }
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    PlaceManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
