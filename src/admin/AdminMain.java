package admin;

import hugong.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AdminMain {
    public static void createShow(String userName){
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("院长");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("欢迎你");
        label.setBounds(250, 0, 100, 25);
        label.setFont(new Font("微软雅黑", Font.BOLD, 25));
        label.setForeground(Color.red);
        panel.add(label);

        //用户管理
        JButton userManage = new JButton("用户管理");
        userManage.setBounds(100, 50, 150, 50);
        panel.add(userManage);
        userManage.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UserManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //老人信息管理
        JButton oldInfoManage = new JButton("老人信息管理");
        oldInfoManage.setBounds(300, 50, 150, 50);
        panel.add(oldInfoManage);
        oldInfoManage.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OldInfoManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //床位管理
        JButton placeManage = new JButton("床位管理");
        placeManage.setBounds(100, 150, 150, 50);
        panel.add(placeManage);
        placeManage.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PlaceManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //护工信息管理
        JButton hugongManage = new JButton("护工信息管理");
        hugongManage.setBounds(300, 150, 150, 50);
        panel.add(hugongManage);
        hugongManage.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HugongManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //保安信息管理
        JButton baoanManage = new JButton("保安信息管理");
        baoanManage.setBounds(100, 250, 150, 50);
        panel.add(baoanManage);
        baoanManage.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BaoanManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //费用管理
        JButton costManage = new JButton("费用管理");
        costManage.setBounds(300, 250, 150, 50);
        panel.add(costManage);
        costManage.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CostManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //员工薪资管理
        JButton salaryManage = new JButton("员工薪资管理");
        salaryManage.setBounds(100, 350, 150, 50);
        panel.add(salaryManage);
        salaryManage.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SalaryManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //健康管理
        JButton healthManage = new JButton("健康管理");
        healthManage.setBounds(300, 350, 150, 50);
        panel.add(healthManage);
        healthManage.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HealthManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //进出人员管理
        JButton outAndInManage = new JButton("进出人员管理");
        outAndInManage.setBounds(100, 450, 150, 50);
        panel.add(outAndInManage);
        outAndInManage.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OutAndInManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //退出
        JButton exit = new JButton("退出");
        exit.setBounds(300, 450, 150, 50);
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
