import entity.Old;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OldInfoShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("老人信息");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //查询老人信息
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
            System.out.println(old);
            oldList.add(old);
        }
        System.out.println(oldList);

        //创建JTable
        JTable table;
        String [] index = {"id","姓名","性别","床位", "健康状态", "电话"};
        Object [][] data = new Object[oldList.size()][index.length];
        //3,向data中添加数据
        for (int i = 0; i < oldList.size(); i++) {
            Old old = oldList.get(i);
            data[i][0] = old.getId();
            data[i][1] = old.getName();
            data[i][2] = old.getGender();
            data[i][3] = old.getPlace();
            data[i][4] = old.getStatus();
            data[i][5] = old.getPhone();
        }

        //4,创建一个默认的表格模型
        DefaultTableModel defaultModel = new DefaultTableModel(data, index);
        table=new JTable(defaultModel);
        table.setBackground(Color.WHITE);
        table.setPreferredScrollableViewportSize(new Dimension(100, 80));//JTable的高度和宽度按照设定
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
