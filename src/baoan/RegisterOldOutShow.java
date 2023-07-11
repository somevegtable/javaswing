package baoan;

import com.mysql.cj.util.StringUtils;
import dao.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterOldOutShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("外出人员登记");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        DAO dao=new DAO();
        //创建外出人员place标签
        JLabel placeLabel = new JLabel("外出人员床位号：");
        placeLabel.setFont(new Font("微软雅黑", 0, 13));
        placeLabel.setBounds(180, 100, 80, 25);
        panel.add(placeLabel);

        //创建外出人员place输入文字域
        JTextField placeText = new JTextField(20);
        placeText.setBounds(250, 100, 165, 25);
        panel.add(placeText);

        //创建外出人员name状态标签
        JLabel nameLabel = new JLabel("姓名：");
        nameLabel.setFont(new Font("微软雅黑", 0, 13));
        nameLabel.setBounds(180, 150, 80, 25);
        panel.add(nameLabel);

        //创建外出人员name输入文字域
        JTextField nameText = new JTextField(20);
        nameText.setBounds(250, 150, 165, 25);
        panel.add(nameText);

        //创建来访人员toplace状态标签
        JLabel toPlaceLabel = new JLabel("目的地：");
        toPlaceLabel.setFont(new Font("微软雅黑", 0, 13));
        toPlaceLabel.setBounds(180, 200, 80, 25);
        panel.add(toPlaceLabel);

        //创建来访人员toplace文字域
        JTextField toPlaceText = new JTextField(20);
        toPlaceText.setBounds(250, 200, 165, 25);
        panel.add(toPlaceText);

        JButton confirm = new JButton("登记");
        confirm.setBounds(250, 300, 80, 25);
        panel.add(confirm);
        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNullOrEmpty(placeText.getText()) ||
                    StringUtils.isNullOrEmpty(nameText.getText()) ||
                    StringUtils.isNullOrEmpty(toPlaceText.getText())){
                    JOptionPane.showMessageDialog(null, "参数不能为空！", "失败", 0);
                }
                else {
                    ResultSet rs=dao.query("select * from t_old where place="+placeText.getText()+" and name= '"+nameText.getText()+"'");
                    try {
                            if(rs.next()){
                                dao.update("insert into t_out(name, gender, phone, toplace) values('"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(6)+"','"+toPlaceText.getText()+"')");
                                JOptionPane.showMessageDialog(null, "登记成功", "成功", 0);
                                frame.setVisible(false);
                            }

                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                }
            }
        });

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
