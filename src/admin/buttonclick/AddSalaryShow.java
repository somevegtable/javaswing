package admin.buttonclick;

import admin.BaoanManageShow;
import admin.SalaryManageShow;
import com.mysql.cj.util.StringUtils;
import dao.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class AddSalaryShow {
    public static void createShow(){
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("添加薪资信息");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //创建角色姓名标签
        JLabel nameLabel = new JLabel("姓名：");
        nameLabel.setFont(new Font("微软雅黑", 0, 13));
        nameLabel.setBounds(180, 100, 110, 25);
        panel.add(nameLabel);

        //创建角色姓名输入文字域
        JTextField nameText = new JTextField(20);
        nameText.setBounds(250, 100, 165, 25);
        panel.add(nameText);

        //创建基础薪资标签
        JLabel basisLabel = new JLabel("基础薪资：");
        basisLabel.setFont(new Font("微软雅黑", 0, 13));
        basisLabel.setBounds(180, 150, 110, 25);
        panel.add(basisLabel);

        //创建基础薪资输入文字域
        JTextField basisText = new JTextField(20);
        basisText.setBounds(250, 150, 110, 25);
        panel.add(basisText);

        //创建奖金标签
        JLabel bonusLabel = new JLabel("奖金：");
        bonusLabel.setFont(new Font("微软雅黑", 0, 13));
        bonusLabel.setBounds(180, 200, 110, 25);
        panel.add(bonusLabel);

        //创建奖金输入文字域
        JTextField bonusText = new JTextField(20);
        bonusText.setBounds(250, 200, 165, 25);
        panel.add(bonusText);

        //创建薪资标签
        JLabel salaryLabel = new JLabel("应发薪资：");
        salaryLabel.setFont(new Font("微软雅黑", 0, 13));
        salaryLabel.setBounds(180, 250, 100, 25);
        panel.add(salaryLabel);

        //创建薪资文字域
        JTextField salaryText = new JTextField(20);
        salaryText.setBounds(250, 250, 165, 25);
        panel.add(salaryText);

        //创建扣除标签
        JLabel deductLabel = new JLabel("扣除：");
        deductLabel.setFont(new Font("微软雅黑", 0, 13));
        deductLabel.setBounds(180, 300, 100, 25);
        panel.add(deductLabel);

        //创建扣除文字域
        JTextField deductText = new JTextField(20);
        deductText.setBounds(250, 300, 165, 25);
        panel.add(deductText);

        //创建实发薪资标签
        JLabel realLabel = new JLabel("实发薪资：");
        realLabel.setFont(new Font("微软雅黑", 0, 13));
        realLabel.setBounds(180, 350, 100, 25);
        panel.add(realLabel);

        //创建实发薪资文字域
        JTextField realText = new JTextField(20);
        realText.setBounds(250, 350, 165, 25);
        panel.add(realText);

        //创建账号标签
        JLabel accountLabel = new JLabel("账号：");
        accountLabel.setFont(new Font("微软雅黑", 0, 13));
        accountLabel.setBounds(180, 400, 100, 25);
        panel.add(accountLabel);

        //创建账号文字域
        JTextField accountText = new JTextField(20);
        accountText.setBounds(250, 400, 165, 25);
        panel.add(accountText);

        JButton confirm = new JButton("添加");
        confirm.setBounds(250, 450, 80, 25);
        panel.add(confirm);
        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(StringUtils.isNullOrEmpty(nameText.getText()) ||
                   StringUtils.isNullOrEmpty(basisText.getText()) ||
                   StringUtils.isNullOrEmpty(bonusText.getText()) ||
                   StringUtils.isNullOrEmpty(salaryText.getText()) ||
                   StringUtils.isNullOrEmpty(deductText.getText()) ||
                   StringUtils.isNullOrEmpty(accountText.getText()) ||
                   StringUtils.isNullOrEmpty(realText.getText())){
                    JOptionPane.showMessageDialog(null, "参数不能为空！", "失败", 0);
                }else{
                    int check = JOptionPane.showConfirmDialog(null, "确定添加该薪资信息吗?", "添加",JOptionPane.YES_NO_OPTION);//返回的是按钮的index  i=0或者1
                    if (check == 0) {
                        DAO dao = new DAO();
                        Integer row = dao.update("insert into t_salary(name, basis, bonus, salary, deduct, realsalary, account)" +
                                "values('"+nameText.getText()+"', '"+basisText.getText()+"', '"+bonusText.getText()+"', '"+salaryText.getText()+"', '"+deductText.getText()+"', '"+realText.getText()+"', '"+accountText.getText()+"')");
                        JOptionPane.showMessageDialog(null, "添加成功", "成功", 1);
                    }
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    SalaryManageShow.createShow();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
