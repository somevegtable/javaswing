package admin;

import admin.buttonclick.*;
import dao.DAO;
import entity.Baoan;
import entity.Hugong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaoanManageShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("保安信息管理");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //查询old信息
        DAO dao = new DAO();
        ResultSet resultSet = dao.query("select * from t_baoan");

        //ResultSet转换为List
        List<Baoan> baoanList = new ArrayList<>();
        while(resultSet.next()){
            Baoan baoan = new Baoan();
            baoan.setId(resultSet.getInt(1));
            baoan.setName(resultSet.getString(2));
            baoan.setAccount(resultSet.getString(3));
            baoan.setPassword(resultSet.getString(4));
            baoan.setPhone(resultSet.getString(5));
            baoan.setGender(resultSet.getString(6));
            baoan.setSalary(resultSet.getString(6));
            baoanList.add(baoan);
        }

        JButton addUser = new JButton("新增保安信息");
        addUser.setBounds(100, 50, 120, 25);
        addUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBaoanShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton updateUser = new JButton("修改保安信息");
        updateUser.setBounds(250, 50, 120, 25);
        updateUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateBaoanShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton deleteUser = new JButton("删除保安信息");
        deleteUser.setBounds(400, 50, 120, 25);
        deleteUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteBaoanShow.createShow();
                frame.setVisible(false);
            }
        });

        panel.add(addUser);
        panel.add(updateUser);
        panel.add(deleteUser);
        frame.add(panel);

        //创建JTable
        JTable table;
        String [] index = {"id","姓名","账号","密码", "电话", "性别", "薪资"};
        Object [][] data = new Object[baoanList.size()][index.length];
        //3,向data中添加数据
        data[0]=index;
        for (int i = 0; i < baoanList.size(); i++) {
            Baoan user = baoanList.get(i);
            data[i+1][0] = user.getId();
            data[i+1][1] = user.getName();
            data[i+1][2] = user.getAccount();
            data[i+1][3] = user.getPassword();
            data[i+1][4] = user.getPhone();
            data[i+1][5] = user.getGender();
            data[i+1][6] = user.getSalary();
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
