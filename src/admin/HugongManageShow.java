package admin;

import admin.buttonclick.*;
import dao.DAO;
import entity.Hugong;
import entity.Old;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HugongManageShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("护工信息管理");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //查询old信息
        DAO dao = new DAO();
        ResultSet resultSet = dao.query("select * from t_worker");

        //ResultSet转换为List
        List<Hugong> hugonglist = new ArrayList<>();
        while(resultSet.next()){
            Hugong hugong = new Hugong();
            hugong.setId(resultSet.getInt(1));
            hugong.setName(resultSet.getString(2));
            hugong.setGender(resultSet.getString(3));
            hugong.setPhone(resultSet.getString(4));
            hugong.setSalary(resultSet.getString(5));
            hugong.setAccount(resultSet.getString(6));
            hugong.setPassword(resultSet.getString(6));
            hugonglist.add(hugong);
        }

        JButton addUser = new JButton("新增护工信息");
        addUser.setBounds(100, 50, 120, 25);
        addUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddHugongShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton updateUser = new JButton("修改护工信息");
        updateUser.setBounds(250, 50, 120, 25);
        updateUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateHugongShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton deleteUser = new JButton("删除护工信息");
        deleteUser.setBounds(400, 50, 120, 25);
        deleteUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteHugongShow.createShow();
                frame.setVisible(false);
            }
        });

        panel.add(addUser);
        panel.add(updateUser);
        panel.add(deleteUser);
        frame.add(panel);

        //创建JTable
        JTable table;
        String [] index = {"id","姓名","性别","电话", "薪资", "账号", "密码"};
        Object [][] data = new Object[hugonglist.size()][index.length];
        //3,向data中添加数据
        for (int i = 0; i < hugonglist.size(); i++) {
            Hugong user = hugonglist.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getName();
            data[i][2] = user.getGender();
            data[i][3] = user.getPhone();
            data[i][4] = user.getSalary();
            data[i][5] = user.getAccount();
            data[i][6] = user.getPassword();
        }

        //4,创建一个默认的表格模型
        DefaultTableModel defaultModel = new DefaultTableModel(data, index);
        table=new JTable(defaultModel);
        table.setBackground(Color.white);
        table.setBounds(0, 150, 600, 400);

        //5，给表格设置滚动条
        panel.add(table);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
