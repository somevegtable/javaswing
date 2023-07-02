package baoan;

import entity.Salary;
import hugong.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class BaoanMain {
    public static void createShow(String userName){
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("保安");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("欢迎你");
        label.setBounds(250, 0, 100, 25);
        label.setFont(new Font("微软雅黑", Font.BOLD, 25));
        label.setForeground(Color.red);
        panel.add(label);

        //老人信息查看
        JButton oldOut = new JButton("老人外出登记");
        oldOut.setBounds(100, 100, 150, 50);
        panel.add(oldOut);
        oldOut.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RegisterOldOutShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //床位查看
        JButton personIn = new JButton("来访人员登记");
        personIn.setBounds(300, 100, 150, 50);
        panel.add(personIn);
        personIn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RegisterPersonInShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //健康信息登记
        JButton updateHealth = new JButton("薪资查看");
        updateHealth.setBounds(100, 200, 150, 50);
        panel.add(updateHealth);
        updateHealth.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SalaryShow.createShow(userName);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //退出
        JButton exit = new JButton("退出");
        exit.setBounds(300, 200, 150, 50);
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
