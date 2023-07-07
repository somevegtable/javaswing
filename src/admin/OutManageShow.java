package admin;

import admin.buttonclick.*;
import dao.DAO;
import entity.Cost;
import entity.In;
import entity.Out;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OutManageShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("外出人员管理");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        DAO dao = new DAO();
        ResultSet resultSet = dao.query("select * from t_out");

        //ResultSet转换为List
        List<Out> outList = new ArrayList<>();
        while(resultSet.next()){
            Out out = new Out();
            out.setId(resultSet.getInt(1));
            out.setName(resultSet.getString(2));
            out.setGender(resultSet.getString(3));
            out.setPhone(resultSet.getString(4));
            out.setToplace(resultSet.getString(5));
            outList.add(out);
        }

        JButton addUser = new JButton("新增外出人员信息");
        addUser.setBounds(100, 50, 120, 25);
        addUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddOutShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton updateUser = new JButton("修改外出人员信息");
        updateUser.setBounds(250, 50, 120, 25);
        updateUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateOutShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton deleteUser = new JButton("删除外出人员信息");
        deleteUser.setBounds(400, 50, 120, 25);
        deleteUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteOutShow.createShow();
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
        Object [][] data = new Object[outList.size()][index.length];
        //3,向data中添加数据
        data[0]=index;
        for (int i = 0; i < outList.size(); i++) {
            Out user = outList.get(i);
            data[i+1][0] = user.getId();
            data[i+1][1] = user.getName();
            data[i+1][2] = user.getGender();
            data[i+1][3] = user.getPhone();
            data[i+1][4] = user.getToplace();
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
