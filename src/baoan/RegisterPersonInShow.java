package baoan;

import com.mysql.cj.util.StringUtils;
import dao.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterPersonInShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("来访人员登记");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        DAO dao=new DAO();
        //创建来访人员name标签
        JLabel nameLabel = new JLabel("姓名：");
        nameLabel.setFont(new Font("微软雅黑", 0, 13));
        nameLabel.setBounds(180, 100, 80, 25);
        panel.add(nameLabel);

        //创建来访人员name输入文字域
        JTextField nameText = new JTextField(20);
        nameText.setBounds(250, 100, 165, 25);
        panel.add(nameText);

        //创建来访人员gengder状态标签
        JLabel genderLabel = new JLabel("性别：");
        genderLabel.setFont(new Font("微软雅黑", 0, 13));
        genderLabel.setBounds(180, 150, 80, 25);
        panel.add(genderLabel);

        //创建来访人员gender输入文字域
        JTextField genderText = new JTextField(20);
        genderText.setBounds(250, 150, 165, 25);
        panel.add(genderText);

        //创建来访人员phone状态标签
        JLabel phoneLabel = new JLabel("性别：");
        phoneLabel.setFont(new Font("微软雅黑", 0, 13));
        phoneLabel.setBounds(180, 200, 80, 25);
        panel.add(phoneLabel);

        //创建来访人员phone输入文字域
        JTextField phoneText = new JTextField(20);
        phoneText.setBounds(250, 200, 165, 25);
        panel.add(phoneText);

        //创建来访人员fromplace状态标签
        JLabel fromPlaceLabel = new JLabel("起始地：");
        fromPlaceLabel.setFont(new Font("微软雅黑", 0, 13));
        fromPlaceLabel.setBounds(180, 250, 80, 25);
        panel.add(fromPlaceLabel);

        //创建来访人员fromplace文字域
        JTextField fromPlaceText = new JTextField(20);
        fromPlaceText.setBounds(250, 250, 165, 25);
        panel.add(fromPlaceText);

        JButton confirm = new JButton("登记");
        confirm.setBounds(250, 300, 80, 25);
        panel.add(confirm);
        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNullOrEmpty(nameText.getText()) ||
                    StringUtils.isNullOrEmpty(genderText.getText()) ||
                    StringUtils.isNullOrEmpty(phoneText.getText()) ||
                    StringUtils.isNullOrEmpty(fromPlaceText.getText())){
                    JOptionPane.showMessageDialog(null, "参数不能为空！", "失败", 0);
                }
                else {
                    dao.update("insert into t_in(name,gender,phone,fromplace) values('"+nameText.getText()+"','"+genderText.getText()+"','"+phoneText.getText()+"','"+fromPlaceText.getText()+"')");
                    JOptionPane.showMessageDialog(null, "登记成功", "成功", 0);
                    frame.setVisible(false);
                }
            }
        });

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
