package admin.buttonclick;

import admin.CostManageShow;
import admin.InManageShow;
import com.mysql.cj.util.StringUtils;
import dao.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UpdateInShow {
    public static void createShow(){
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("修改来访人员信息");

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
        JLabel placeLabel = new JLabel("起始地：");
        placeLabel.setFont(new Font("微软雅黑", 0, 13));
        placeLabel.setBounds(180, 250, 100, 25);
        panel.add(placeLabel);

        //创建起始地文字域
        JTextField placeText = new JTextField(20);
        placeText.setBounds(250, 250, 165, 25);
        panel.add(placeText);

        //创建修改人id标签
        JLabel idLabel = new JLabel("修改信息id：");
        idLabel.setFont(new Font("微软雅黑", 0, 13));
        idLabel.setBounds(180, 300, 100, 25);
        panel.add(idLabel);

        //创建修改人id文字域
        JTextField idText = new JTextField(20);
        idText.setBounds(250, 300, 165, 25);
        panel.add(idText);

        JButton confirm = new JButton("修改");
        confirm.setBounds(250, 350, 80, 25);
        panel.add(confirm);
        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(StringUtils.isNullOrEmpty(nameText.getText()) ||
                        StringUtils.isNullOrEmpty(placeText.getText()) ||
                        StringUtils.isNullOrEmpty(phoneText.getText()) ||
                        StringUtils.isNullOrEmpty(genderText.getSelectedItem().toString()) ||
                        StringUtils.isNullOrEmpty(idText.getText())){
                    JOptionPane.showMessageDialog(null, "参数不能为空！", "失败", 0);
                }else{
                    int check = JOptionPane.showConfirmDialog(null, "确定修改该条信息吗?", "添加",JOptionPane.YES_NO_OPTION);//返回的是按钮的index  i=0或者1
                    if (check == 0) {
                        DAO dao = new DAO();
                        ResultSet resultSet = dao.query("select id from t_in");
                        Set<String> set = new HashSet<>();
                        try{
                            while(resultSet.next()){
                                set.add(resultSet.getString(1));
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        if (!set.contains(idText.getText())){
                            JOptionPane.showMessageDialog(null, "id不存在", "失败", 0);
                        }else{
                            Integer row = dao.update("update t_in set name = '"+nameText.getText()+"', gender = '"+genderText.getSelectedItem().toString()+"', phone = '"+phoneText.getText()+"', fromplace = '"+placeText.getText()+"' where id = "+idText.getText()+"");
                            JOptionPane.showMessageDialog(null, "修改成功", "成功", 1);
                        }
                    }
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    InManageShow.createShow();
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
