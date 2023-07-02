package hugong;

import dao.DAO;
import entity.Bed;
import entity.Old;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BedInfoShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("床位信息");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //查询床位信息
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

        //创建JTable
        JTable table;
        String [] index = {"id","床位码","状态","占用者"};
        Object [][] data = new Object[bedList.size()][index.length];
        //3,向data中添加数据
        for (int i = 0; i < bedList.size(); i++) {
            Bed bed = bedList.get(i);
            data[i][0] = bed.getId();
            data[i][1] = bed.getCode();
            data[i][2] = bed.getStatus();
            data[i][3] = bed.getHolder();
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
