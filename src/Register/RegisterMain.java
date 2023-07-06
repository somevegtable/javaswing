package Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RegisterMain  {
    public static void createShow(String text) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("用户注册");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //创建一个用户名的text
        JTextField t1 = new JTextField(10);

        JLabel label = new JLabel("欢迎您注册使用本系统");
        label.setBounds(140, 0, 300, 30);
        label.setFont(new Font("微软雅黑", Font.BOLD, 25));
        label.setForeground(Color.red);
        panel.add(label);
        //这里创建三个列表用于后期接收传来的内容，在注册功能中比对密码的正确性
        List username = new ArrayList();
        List password1 = new ArrayList();
        List password2 = new ArrayList();
        //这里是性别
        Gender genderint = new Gender(null);
        //这里创建JLabel 放置用户名密码
        JLabel l1 = new JLabel("用户名：");
        JLabel l2 = new JLabel("密  码：");
        JLabel l3 = new JLabel("确认密码:");
        JLabel l4 = new JLabel("电  话：");
        l1.setFont(new Font("微软雅黑", 0, 13));
        l1.setBounds(110, 50, 80, 25);
        panel.add(l1);
        l2.setFont(new Font("微软雅黑", 0, 13));
        l2.setBounds(110, 80, 80, 25);
        panel.add(l2);
        l3.setFont(new Font("微软雅黑", 0, 13));
        l3.setBounds(110, 110, 80, 25);
        panel.add(l3);
        l4.setFont(new Font("微软雅黑", 0, 13));
        l4.setBounds(110, 140, 80, 25);
        panel.add(l4);
        //创建一个用户名的text
        JTextField userText = new JTextField(20);
        userText.setBounds(180, 50, 165, 25);
        panel.add(userText);
        //创建密码的jpassword
        JPasswordField pf1 = new JPasswordField(10);
        pf1.setBounds(180, 80, 165, 25);
        panel.add(pf1);
        //放置确认密码
        JPasswordField pf2 = new JPasswordField(10);
        pf2.setBounds(180, 110, 165, 25);
        panel.add(pf2);
        //放置电话
        JTextField dh = new JTextField(10);
        dh.setBounds(180, 140, 165, 25);
        panel.add(dh);

        //这里存放性别
        JLabel labelgenger = new JLabel("性别:");
        ButtonGroup gender = new ButtonGroup();
        JRadioButton man = new JRadioButton("男",true);
        JRadioButton woman = new JRadioButton("女",false);
        labelgenger.setFont(new Font("微软雅黑", 0, 13));
        labelgenger.setBounds(110, 180, 80, 25);
        panel.add(labelgenger);
        //点击男性时候 把性别参数改为1
        //这里是男性按钮
        man.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //外部创建了一个类Gender 其中有一个Object参数表示男女 0代表女 1代表男
                genderint.setGenger(1);
                System.out.println(genderint.getGenger());
            }
        });
        //这里是女性按钮
        //点击时候设置gender 类的 genderint实例变量的gender属性为0
        woman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genderint.setGenger(0);
                System.out.println(genderint.getGenger());
            }
        });
        gender.add(man);gender.add(woman);
        //ButtonGroup放在JPanel中
        JPanel ButtonPanel=new JPanel();
        ButtonPanel.add(man);
        ButtonPanel.add(woman);
        ButtonPanel.setBounds(200, 180, 80, 80);
        panel.add(ButtonPanel);

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
        comboBox.setBounds(190, 280, 100, 25);
        jlb1.setBounds(110, 280, 100, 25);
        panel.add(jlb1);
        panel.add(comboBox);

        //退出
        JButton exit = new JButton("重置");
        exit.setBounds(260, 350, 100, 50);
        panel.add(exit);
        //确认
        JButton confirm = new JButton("确定");
        confirm.setBounds(160, 350, 100, 50);
        panel.add(confirm);
        confirm.addActionListener(new ActionListener(){
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          if (t1.getText().equals("")){
                                              username.add(0,null);
                                          }else {
                                              //如果取值不为空，就让他得到文本框中的内容
                                              username.add(0,t1.getText());
                                          }
                                          //将密码栏中的信息得到，添加给passwrod数组 用于确认密码
                                          password1.add(0,new String(pf1.getPassword()));
                                          password2.add(0,new String(pf2.getPassword()));
                                          if(password1.get(0).equals(password2.get(0))){
                                              System.out.println("注册成功！！！");
                                              new DemoDialog();
                                          }
                                      }
                                  });
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                username.removeAll(username);
                password1.removeAll(password1);
                password2.removeAll(password2);
                genderint.setGenger(null);
                System.out.println("重置");
                userText.setText("");
                pf1.setText("");
                pf2.setText("");
                dh.setText("");
            }
        });
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);
    }
}