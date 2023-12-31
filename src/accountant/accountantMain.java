package accountant;

import hugong.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class accountantMain {
    public static void createShow(String userName){
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("会计");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("欢迎你");
        label.setBounds(250, 0, 100, 25);
        label.setFont(new Font("微软雅黑", Font.BOLD, 25));
        label.setForeground(Color.red);
        panel.add(label);
        //薪资查看
        JButton querySalary = new JButton("薪资查看");
        querySalary.setBounds(210, 80, 150, 50);
        panel.add(querySalary);
        querySalary.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AcSalaryShow.createShow(userName);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //费用支出登记
        JButton cost = new JButton("费用管理");
        cost.setBounds(210, 160, 150, 50);
        panel.add(cost);
        cost.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AsCostManange.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //退出
        JButton exit = new JButton("退出");
        exit.setBounds(210, 240, 150, 50);
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
