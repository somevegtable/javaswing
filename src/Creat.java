import dao.DAO;
import hugong.HugongMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Creat {
    public void creat(){
        JFrame frame = new JFrame("登录");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);  //窗口居中显示
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);          //禁止调节窗口大小

        //创建面板
        JPanel panel = new JPanel();
        panel.setLayout(null);              //布局为空,手动设置组件的坐标位置

        //创建用户名标签
        JLabel userName = new JLabel("用户名：");
        userName.setFont(new Font("微软雅黑", 0, 13));
        userName.setBounds(180, 100, 80, 25);
        panel.add(userName);

        //创建用户名输入文字域
        JTextField userText = new JTextField(20);
        userText.setBounds(230, 100, 165, 25);
        panel.add(userText);

        //创建密码标签
        JLabel password = new JLabel("密码：");
        password.setFont(new Font("微软雅黑", 0, 13));
        password.setBounds(180, 150, 80, 25);
        panel.add(password);

        //创建密码输入文字域
        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(230, 150, 165, 25);
        panel.add(passwordText);

        //单选框
        JRadioButton radioButton1 = new JRadioButton("护工", true);
        JRadioButton radioButton2 = new JRadioButton("保安");
        JRadioButton radioButton3 = new JRadioButton("管理员");
        radioButton1.setBounds(180, 200, 100, 30);
        radioButton2.setBounds(280, 200, 100, 30);
        radioButton3.setBounds(380, 200, 100, 30);
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);
        panel.add(radioButton1);
        panel.add(radioButton2);
        panel.add(radioButton3);

        //登录按钮
        JButton login = new JButton("登录");
        login.setBounds(250, 250, 80, 25);
        panel.add(login);

        //添加面板
        frame.add(panel);
        frame.setVisible(true);
        LoginListener loginListener = new LoginListener(frame, passwordText, userText, panel);
        login.addActionListener(loginListener);
    }

    public class LoginListener implements ActionListener{
        private JTextField userName;
        private JPasswordField password;
        private JFrame frame;
        private JPanel panel;

        public LoginListener(JFrame frame, JPasswordField password, JTextField userName, JPanel panel){
            this.frame = frame;
            this.password = password;
            this.userName = userName;
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("userName:" + userName.getText()+"  "+"password:" + password.getText());
            DAO dao = new DAO();

            String radioButtonText = null;
            for(Component component : panel.getComponents()){
                if (component instanceof JRadioButton){
                    if (((JRadioButton) component).isSelected()){
                        radioButtonText = ((JRadioButton) component).getText();
                    }
                }
            }
            ResultSet adminResultSet = dao.query("select * from t_admin where account = '"+userName.getText()+"' and password = '"+password.getText()+"'");
            ResultSet hugongResultSet = dao.query("select * from t_worker where account = '"+userName.getText()+"' and password = '"+password.getText()+"'");
            ResultSet baoanResultSet = dao.query("select * from t_baoan where account = '"+userName.getText()+"' and password = '"+password.getText()+"'");


            //用户密码判断
            try {
                if(radioButtonText.equals("管理员") && adminResultSet.next()){
                    //弹框
                    JOptionPane.showMessageDialog(null, "管理员登录成功", "成功", 1);
                    Login.createShow();
                    frame.setVisible(false);
                }else if(radioButtonText.equals("护工") && hugongResultSet.next()){
                    JOptionPane.showMessageDialog(null, "护工登录成功", "成功", 1);
                    HugongMain.createShow(userName.getText());
                    frame.setVisible(false);
                }else if(radioButtonText.equals("保安") && baoanResultSet.next()){
                    JOptionPane.showMessageDialog(null, "保安登录成功", "成功", 1);
                    Login.createShow();
                    frame.setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null, "登录失败", "失败", 0);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
    }
}


