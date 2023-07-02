package hugong;

import dao.DAO;
import entity.Bed;
import entity.Salary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryShow {
    public static void createShow(String userName) throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("薪资查看");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //查询薪资信息
        DAO dao = new DAO();
        ResultSet resultSet = dao.query("select * from t_salary where account = '"+userName+"'");

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



        //创建JTable
        JTable table;
        String [] index = {"id","姓名","基础薪资","奖金", "应发薪资", "扣除", "实发薪资"};
        Object [][] data = new Object[salaryList.size()][index.length];
        //3,向data中添加数据
        for (int i = 0; i < salaryList.size(); i++) {
            Salary salary = salaryList.get(i);
            data[i][0] = salary.getId();
            data[i][1] = salary.getName();
            data[i][2] = salary.getBasis();
            data[i][3] = salary.getBonus();
            data[i][4] = salary.getSalary();
            data[i][5] = salary.getDeduct();
            data[i][6] = salary.getRealSalary();
        }

        //4,创建一个默认的表格模型
        DefaultTableModel defaultModel = new DefaultTableModel(data, index);
        table=new JTable(defaultModel);
        table.setBackground(Color.WHITE);
        table.setPreferredScrollableViewportSize(new Dimension(200, 100));//JTable的高度和宽度按照设定
        table.setFillsViewportHeight(true);

        //5，给表格设置滚动条
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(table);

        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.add(jScrollPane,BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
