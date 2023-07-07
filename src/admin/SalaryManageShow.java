package admin;

import admin.buttonclick.*;
import dao.DAO;
import entity.Baoan;
import entity.Salary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryManageShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("员工薪资管理");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //查询old信息
        DAO dao = new DAO();
        ResultSet resultSet = dao.query("select * from t_salary");

        //ResultSet转换为List
        List<Salary> salaryList = new ArrayList<>();
        while(resultSet.next()){
            Salary salary = new Salary();
            salary.setId(resultSet.getInt(1));
            salary.setName(resultSet.getString(2));
            salary.setBasis(resultSet.getInt(3));
            salary.setBonus(resultSet.getInt(4));
            salary.setSalary(resultSet.getInt(5));
            salary.setDeduct(resultSet.getInt(6));
            salary.setRealSalary(resultSet.getInt(7));
            salary.setAccount(resultSet.getString(8));
            salaryList.add(salary);
        }

        JButton addUser = new JButton("新增薪资信息");
        addUser.setBounds(100, 50, 120, 25);
        addUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSalaryShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton updateUser = new JButton("修改薪资信息");
        updateUser.setBounds(250, 50, 120, 25);
        updateUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateSalaryShow.createShow();
                frame.setVisible(false);
            }
        });

        JButton deleteUser = new JButton("删除薪资信息");
        deleteUser.setBounds(400, 50, 120, 25);
        deleteUser.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteSalaryShow.createShow();
                frame.setVisible(false);
            }
        });

        panel.add(addUser);
        panel.add(updateUser);
        panel.add(deleteUser);
        frame.add(panel);

        //创建JTable
        JTable table;
        String [] index = {"id","姓名","基础薪资","奖金", "应发薪资", "扣除", "实发薪资", "账号"};
        Object [][] data = new Object[salaryList.size()+1][index.length];
        //3,向data中添加数据
        data[0]=index;
        for (int i = 0; i < salaryList.size(); i++) {
            Salary user = salaryList.get(i);
            data[i+1][0] = user.getId();
            data[i+1][1] = user.getName();
            data[i+1][2] = user.getBasis();
            data[i+1][3] = user.getBonus();
            data[i+1][4] = user.getSalary();
            data[i+1][5] = user.getDeduct();
            data[i+1][6] = user.getRealSalary();
            data[i+1][7] = user.getAccount();
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
