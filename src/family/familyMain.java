package family;

import hugong.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class familyMain {
    public static void createShow(String userName){
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("老人亲属");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("欢迎您");
        label.setBounds(250, 0, 100, 25);
        label.setFont(new Font("微软雅黑", Font.BOLD, 25));
        label.setForeground(Color.red);
        panel.add(label);

        //老人信息查看
        JButton queryOld = new JButton("查看老人信息");
        queryOld.setBounds(100, 100, 150, 50);
        panel.add(queryOld);
        queryOld.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OldInfoShow1.createShow(userName);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //床位查看
        JButton queryBed = new JButton("查看健康信息");
        queryBed.setBounds(300, 100, 150, 50);
        panel.add(queryBed);
        queryBed.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OldInfoShow2.createShow(userName);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //健康信息登记
        JButton selectCost = new JButton("费用查询");
        selectCost.setBounds(100, 300, 150, 50);
        panel.add(selectCost);
        selectCost.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CostManageShow1.createShow(userName);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //退出
        JButton exit = new JButton("退出");
        exit.setBounds(300, 300, 150, 50);
        panel.add(exit);
        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        frame.add(panel);
        frame.setVisible(true);
    }
}
