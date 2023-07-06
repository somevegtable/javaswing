import admin.AdminMain;
import baoan.BaoanMain;
import dao.DAO;
import family.familyMain;
import accountant.accountantMain;
import hugong.HugongMain;
import Register.RegisterMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    int index;
    String sc="护工";
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

        //下拉列表框
        JLabel jlb1;	//定义标签
        jlb1=new JLabel("请选择身份：");
        JComboBox<String> comboBox = new JComboBox<>(); //定义下拉框
        comboBox.addItem("护工");    //添加选项
        comboBox.addItem("保安");    //添加选项
        comboBox.addItem("管理员");  //添加选项
        comboBox.addItem("亲属");   //添加选项
        comboBox.addItem("财务人员");  //添加选项
        comboBox.setFont(new Font("微软雅黑", 0, 13));
        jlb1.setFont(new Font("微软雅黑", 0, 13));
        comboBox.setBounds(260, 200, 100, 25);
        jlb1.setBounds(180, 200, 100, 25);
        panel.add(jlb1);
        panel.add(comboBox);
        //下拉框监听事件,获取索引值
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBox.getSelectedIndex();
               if(selectedIndex >= 0){
                   sc= (String) comboBox.getSelectedItem();
                   System.out.println("当前选中的值: " + sc);
                   index=selectedIndex;
                }
            }
        });


        //登录按钮
        JButton login = new JButton("登录");
        login.setBounds(200, 250, 80, 25);
        panel.add(login);
        JButton resigter = new JButton("注册");
        resigter.setBounds(290, 250, 80, 25);
        panel.add(resigter);

        //添加面板
        frame.add(panel);
        frame.setVisible(true);
        LoginListener loginListener = new LoginListener(frame, passwordText, userText, panel);
        login.addActionListener(loginListener);
        resigter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    RegisterMain.createShow(userName.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("点击注册");
            }
        } );
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
            ResultSet userResultSet = dao.query("select * from t_user where account = '"+userName.getText()+"' and password = '"+password.getText()+"'");

            //用户密码判断

            try {
                    //方法二
                if(index==0 && userResultSet.next()&& sc.equals(userResultSet.getString(7))){
                    //弹框
                    JOptionPane.showMessageDialog(null, "护工登录成功", "成功", 1);
                    HugongMain.createShow(userName.getText());
                    frame.setVisible(false);
                }else if(index==1&& userResultSet.next()&&sc.equals(userResultSet.getString(7))){
                    JOptionPane.showMessageDialog(null, "保安登录成功", "成功", 1);
                    BaoanMain.createShow(userName.getText());
                    frame.setVisible(false);
                }else if(index==2 && userResultSet.next()&&sc.equals(userResultSet.getString(7))){
                    JOptionPane.showMessageDialog(null, "管理员登录成功", "成功", 1);
                    AdminMain.createShow(userName.getText());
                    frame.setVisible(false);
                }else if(index==3 && userResultSet.next()&&sc.equals(userResultSet.getString(7))){
                    JOptionPane.showMessageDialog(null, "亲属登录成功", "成功", 1);
                    familyMain.createShow(userName.getText());
                    frame.setVisible(false);
                }else if(index==4 && userResultSet.next()&&sc.equals(userResultSet.getString(7))){
                    JOptionPane.showMessageDialog(null, "财务人员登录成功", "成功", 1);
                    accountantMain.createShow(userName.getText());
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


