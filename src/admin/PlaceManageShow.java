package admin;

import admin.buttonclick.*;
import dao.DAO;
import entity.Bed;
import entity.Old;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceManageShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("床位管理");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //查询bed信息
        DAO dao = new DAO();
        ResultSet resultSet = dao.query("select * from t_bed");

        //ResultSet转换为List
        List<Bed> bedList = new ArrayList<>();
        while(resultSet.next()){
            Bed bed = new Bed();
            bed.setId(resultSet.getInt(1));
            bed.setCode(resultSet.getString(2));
            bed.setStatus(resultSet.getString(3));
            bed.setHolder(resultSet.getString(4));
            bedList.add(bed);
        }

        JButton addUser = new JButton("新增床位信息");
        addUser.setBounds(100, 50, 120, 25);
        addUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBedShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton updateUser = new JButton("修改床位信息");
        updateUser.setBounds(250, 50, 120, 25);
        updateUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateBedShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton deleteUser = new JButton("删除床位信息");
        deleteUser.setBounds(400, 50, 120, 25);
        deleteUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteBedShow.createShow();
                frame.setVisible(false);
            }
        });

        panel.add(addUser);
        panel.add(updateUser);
        panel.add(deleteUser);
        frame.add(panel);

        //创建JTable
        JTable table;
        String [] index = {"id","床位号","状态","占用者"};
        Object [][] data = new Object[bedList.size()][index.length];
        //3,向data中添加数据
        data[0]=index;
        for (int i = 0; i < bedList.size(); i++) {
            Bed bed = bedList.get(i);
            data[i+1][0] = bed.getId();
            data[i+1][1] = bed.getCode();
            data[i+1][2] = bed.getStatus();
            data[i+1][3] = bed.getHolder();
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
