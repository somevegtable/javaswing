package admin;

import admin.buttonclick.AddBaoanShow;
import admin.buttonclick.DeleteBaoanShow;
import admin.buttonclick.UpdateBaoanShow;
import dao.DAO;
import entity.Baoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OutAndInManageShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("进出人员管理");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        DAO dao = new DAO();
        ResultSet resultSet = dao.query("select * from t_baoan");


        JButton out = new JButton("外出人员信息管理");
        out.setBounds(200, 100, 200, 100);
        out.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OutManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                frame.setVisible(false);
            }
        });

        JButton in = new JButton("来访人员信息管理");
        in.setBounds(200, 200, 200, 100);
        in.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                frame.setVisible(false);
            }
        });


        panel.add(out);
        panel.add(in);
        frame.add(panel);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
