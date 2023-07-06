package admin.buttonclick;

import admin.InManageShow;
import admin.OutManageShow;
import com.mysql.cj.util.StringUtils;
import dao.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class AddOutShow {
    public static void createShow(){
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("添加外出人员信息");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //创建角色姓名标签
        JLabel nameLabel = new JLabel("姓名：");
        nameLabel.setFont(new Font("微软雅黑", 0, 13));
        nameLabel.setBounds(180, 100, 110, 25);
        panel.add(nameLabel);

        //创建角色姓名输入文字域
        JTextField nameText = new JTextField(20);
        nameText.setBounds(250, 100, 165, 25);
        panel.add(nameText);

        //创建性别标签
        JLabel genderLabel = new JLabel("性别：");
        genderLabel.setFont(new Font("微软雅黑", 0, 13));
        genderLabel.setBounds(180, 150, 110, 25);
        panel.add(genderLabel);

        //创建性别下拉框
        JComboBox<String> genderText = new JComboBox<>();//创建一个下拉列表
        genderText.addItem("男");
        genderText.addItem("女");
        genderText.setFont(new Font("微软雅黑", 0, 13));
        genderText.setBounds(250, 150, 100, 25);
        panel.add(genderText);

        //创建电话标签
        JLabel phoneLabel = new JLabel("电话：");
        phoneLabel.setFont(new Font("微软雅黑", 0, 13));
        phoneLabel.setBounds(180, 200, 110, 25);
        panel.add(phoneLabel);

        //创建电话输入文字域
        JTextField phoneText = new JTextField(20);
        phoneText.setBounds(250, 200, 165, 25);
        panel.add(phoneText);

        //创建起始地标签
        JLabel placeLabel = new JLabel("目的地：");
        phoneLabel.setFont(new Font("微软雅黑", 0, 13));
        phoneLabel.setBounds(180, 250, 110, 25);
        panel.add(phoneLabel);

        //创建电话输入文字域
        JTextField placeText = new JTextField(20);
        phoneText.setBounds(250, 250, 165, 25);
        panel.add(phoneText);


        JButton confirm = new JButton("添加");
        confirm.setBounds(250, 300, 80, 25);
        panel.add(confirm);
        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(StringUtils.isNullOrEmpty(nameText.getText()) ||
                   StringUtils.isNullOrEmpty(genderText.getSelectedItem().toString()) ||
                   StringUtils.isNullOrEmpty(placeText.getText())){
                    JOptionPane.showMessageDialog(null, "参数不能为空！", "失败", 0);
                }else{
                    int check = JOptionPane.showConfirmDialog(null, "确定添加该记录吗?", "添加",JOptionPane.YES_NO_OPTION);//返回的是按钮的index  i=0或者1
                    if (check == 0) {
                        DAO dao = new DAO();
                        Integer row = dao.update("insert into t_out(name, gender, phone, toplace)" +
                                "values('"+nameText.getText()+"', '"+genderText.getSelectedItem().toString()+"', '"+phoneText.getText()+"', '"+placeText.getText()+"')");
                        JOptionPane.showMessageDialog(null, "添加成功", "成功", 1);
                    }
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    OutManageShow.createShow();
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
