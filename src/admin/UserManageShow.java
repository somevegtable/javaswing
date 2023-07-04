package admin;

import admin.buttonclick.AddUserShow;
import admin.buttonclick.DeleteUserShow;
import admin.buttonclick.UpdateUserShow;
import dao.DAO;
import entity.Bed;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManageShow {

    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("床位信息");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //查询user信息
        DAO dao = new DAO();
        ResultSet resultSet = dao.query("select * from t_user");

        //ResultSet转换为List
        List<User> userList = new ArrayList<>();
        while(resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setGender(resultSet.getString(3));
            user.setPhone(resultSet.getString(4));
            user.setAccount(resultSet.getString(5));
            user.setPassword(resultSet.getString(6));
            user.setRole(resultSet.getString(7));
            userList.add(user);
        }

        JButton addUser = new JButton("新增用户");
        addUser.setBounds(100, 50, 100, 25);
        addUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserShow.createShow();
            }
        });

        JButton updateUser = new JButton("修改用户");
        updateUser.setBounds(250, 50, 100, 25);
        updateUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateUserShow.createShow();
            }
        });

        JButton deleteUser = new JButton("删除用户");
        deleteUser.setBounds(400, 50, 100, 25);
        deleteUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteUserShow.createShow();
            }
        });

        panel.add(addUser);
        panel.add(updateUser);
        panel.add(deleteUser);
        frame.add(panel);

        //创建JTable
        JTable table;
        String [] index = {"id","姓名","性别","电话", "账号", "密码", "角色"};
        Object [][] data = new Object[userList.size()][index.length];
        //3,向data中添加数据
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getName();
            data[i][2] = user.getGender();
            data[i][3] = user.getPhone();
            data[i][4] = user.getAccount();
            data[i][5] = user.getPassword();
            data[i][6] = user.getRole();
        }

        //4,创建一个默认的表格模型
        DefaultTableModel defaultModel = new DefaultTableModel(data, index);
        table=new JTable(defaultModel);
        table.setBackground(Color.white);
        table.setBounds(0, 100, 600, 400);

        //5，给表格设置滚动条
        panel.add(table);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
