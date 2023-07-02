package hugong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class HugongMain {
    public static void createShow(String userName){
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("护工");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("欢迎你");
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
                    OldInfoShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //床位查看
        JButton queryBed = new JButton("查看床位信息");
        queryBed.setBounds(300, 100, 150, 50);
        panel.add(queryBed);
        queryBed.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BedInfoShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //健康信息登记
        JButton updateHealth = new JButton("健康信息登记");
        updateHealth.setBounds(100, 200, 150, 50);
        panel.add(updateHealth);
        updateHealth.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UpdateHealthShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //薪资查看
        JButton querySalary = new JButton("薪资查看");
        querySalary.setBounds(300, 200, 150, 50);
        panel.add(querySalary);
        querySalary.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SalaryShow.createShow(userName);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //费用支出登记
        JButton cost = new JButton("费用支出登记");
        cost.setBounds(100, 300, 150, 50);
        panel.add(cost);
        cost.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CostShow.createShow();
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
