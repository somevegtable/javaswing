package family;

import admin.buttonclick.AddCostShow;
import admin.buttonclick.DeleteCostShow;
import admin.buttonclick.UpdateCostShow;
import dao.DAO;
import entity.Cost;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CostManageShow1 {
    public static void createShow(String str) throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("费用支出查询");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //查询old信息
        DAO dao = new DAO();
        ResultSet resultSet=dao.query("select * from t_user where account = '"+str+"'");

        resultSet.next();
        String phone=resultSet.getString(4);
        resultSet = dao.query("select * from t_old where phone = '"+phone+"'");
        resultSet.next();
        if(resultSet!=null){
            String id=resultSet.getString(1);
            resultSet = dao.query("select * from t_cost where id = '"+id+"'");
        }

        //ResultSet转换为List
        List<Cost> costList = new ArrayList<>();
        while(resultSet.next()){
            Cost cost = new Cost();
            cost.setId(resultSet.getInt(1));
            cost.setAmount(resultSet.getInt(2));
            cost.setPlace(resultSet.getString(3));
            cost.setDescription(resultSet.getString(4));
            costList.add(cost);
        }


        frame.add(panel);

        //创建JTable
        JTable table;
        String [] index = {"id","金额","床位号","描述"};
        Object [][] data = new Object[costList.size()+1][index.length];

        //3,向data中添加数据
        data[0][0] = "id";
        data[0][1] = "金额";
        data[0][2] ="床位号";
        data[0][3] = "描述";
        for (int i = 0; i < costList.size(); i++) {
            Cost user = costList.get(i);
            data[i+1][0] = user.getId();
            data[i+1][1] = user.getAmount();
            data[i+1][2] = user.getPlace();
            data[i+1][3] = user.getDescription();
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
