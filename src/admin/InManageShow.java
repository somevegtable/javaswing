package admin;

import admin.buttonclick.*;
import dao.DAO;
import entity.Cost;
import entity.In;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InManageShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("来访人员管理");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        DAO dao = new DAO();
        ResultSet resultSet = dao.query("select * from t_in");

        //ResultSet转换为List
        List<In> inList = new ArrayList<>();
        while(resultSet.next()){
            In in = new In();
            in.setId(resultSet.getInt(1));
            in.setName(resultSet.getString(2));
            in.setGender(resultSet.getString(3));
            in.setPhone(resultSet.getString(4));
            in.setFromplace(resultSet.getString(5));
            inList.add(in);
        }

        JButton addUser = new JButton("新增来访人员信息");
        addUser.setBounds(100, 50, 120, 25);
        addUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddInShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton updateUser = new JButton("修改来访人员信息");
        updateUser.setBounds(250, 50, 120, 25);
        updateUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateInShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton deleteUser = new JButton("删除来访人员信息");
        deleteUser.setBounds(400, 50, 120, 25);
        deleteUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteInShow.createShow();
                frame.setVisible(false);
            }
        });

        panel.add(addUser);
        panel.add(updateUser);
        panel.add(deleteUser);
        frame.add(panel);

        //创建JTable
        JTable table;
        String [] index = {"id","姓名","性别","电话", "起始地"};
        Object [][] data = new Object[inList.size()+1][index.length];
        //3,向data中添加数据
        data[0]=index;
        for (int i = 0; i < inList.size(); i++) {
            In user = inList.get(i);
            data[i+1][0] = user.getId();
            data[i+1][1] = user.getName();
            data[i+1][2] = user.getGender();
            data[i+1][3] = user.getPhone();
            data[i+1][4] = user.getFromplace();
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
