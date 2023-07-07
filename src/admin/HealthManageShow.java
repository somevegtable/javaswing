package admin;

import admin.buttonclick.*;
import dao.DAO;
import entity.Baoan;
import entity.Old;
import hugong.UpdateHealthShow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HealthManageShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("健康管理");

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

        JButton updateUser = new JButton("修改健康信息");
        updateUser.setBounds(250, 50, 120, 25);
        updateUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminUpdateHealthShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                frame.setVisible(false);
            }
        });

        panel.add(updateUser);
        frame.add(panel);

        //创建JTable
        JTable table;
        String [] index = {"id","姓名","性别","床位", "电话", "健康状态"};
        Object [][] data = new Object[oldList.size()][index.length];
        //3,向data中添加数据
        for (int i = 0; i < oldList.size(); i++) {
            Old user = oldList.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getName();
            data[i][2] = user.getGender();
            data[i][3] = user.getPlace();
            data[i][4] = user.getPhone();
            data[i][5] = user.getStatus();
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
