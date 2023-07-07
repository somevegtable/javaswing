package admin;

import admin.buttonclick.*;
import dao.DAO;
import entity.Old;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OldInfoManageShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("老人信息管理");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //查询old信息
        DAO dao = new DAO();
        ResultSet resultSet = dao.query("select * from t_old");

        //ResultSet转换为List
        List<Old> oldList = new ArrayList<>();
        while(resultSet.next()){
            Old old = new Old();
            old.setId(resultSet.getInt(1));
            old.setName(resultSet.getString(2));
            old.setGender(resultSet.getString(3));
            old.setPlace(resultSet.getString(4));
            old.setStatus(resultSet.getString(5));
            old.setPhone(resultSet.getString(6));
            oldList.add(old);
        }

        JButton addUser = new JButton("新增老人信息");
        addUser.setBounds(100, 50, 120, 25);
        addUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddOldShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton updateUser = new JButton("修改老人信息");
        updateUser.setBounds(250, 50, 120, 25);
        updateUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateOldShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton deleteUser = new JButton("删除老人信息");
        deleteUser.setBounds(400, 50, 120, 25);
        deleteUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteOldShow.createShow();
                frame.setVisible(false);
            }
        });

        panel.add(addUser);
        panel.add(updateUser);
        panel.add(deleteUser);
        frame.add(panel);

        //创建JTable
        JTable table;
        String [] index = {"id","姓名","性别","床位", "健康状态", "电话"};
        Object [][] data = new Object[oldList.size()][index.length];
        //3,向data中添加数据
        data[0]=index;
        for (int i = 0; i < oldList.size(); i++) {
            Old user = oldList.get(i);
            data[i+1][0] = user.getId();
            data[i+1][1] = user.getName();
            data[i+1][2] = user.getGender();
            data[i+1][3] = user.getPlace();
            data[i+1][4] = user.getStatus();
            data[i+1][5] = user.getPhone();
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
