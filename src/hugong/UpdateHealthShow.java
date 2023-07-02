package hugong;

import com.mysql.cj.util.StringUtils;
import dao.DAO;
import entity.Bed;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateHealthShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("健康信息登记");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //创建老人id标签
        JLabel idLabel = new JLabel("老人编号：");
        idLabel.setFont(new Font("微软雅黑", 0, 13));
        idLabel.setBounds(180, 100, 80, 25);
        panel.add(idLabel);

        //创建老人id输入文字域
        JTextField idText = new JTextField(20);
        idText.setBounds(250, 100, 165, 25);
        panel.add(idText);

        //创建老人健康状态标签
        JLabel statusLabel = new JLabel("健康状态：");
        statusLabel.setFont(new Font("微软雅黑", 0, 13));
        statusLabel.setBounds(180, 150, 80, 25);
        panel.add(statusLabel);

        //创建老人健康状态输入文字域
        JTextField statusText = new JTextField(20);
        statusText.setBounds(250, 150, 165, 25);
        panel.add(statusText);

        JButton confirm = new JButton("登记");
        confirm.setBounds(250, 250, 80, 25);
        panel.add(confirm);
        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNullOrEmpty(idText.getText())){
                    JOptionPane.showMessageDialog(null, "id不能为空！", "失败", 0);
                }
                String id = idText.getText();

                DAO dao = new DAO();
                ResultSet resultSet = dao.query("select * from t_old where id = "+id+"");
                try {
                    if (resultSet.next() && !StringUtils.isNullOrEmpty(statusText.getText())){
                        Integer rows = dao.update("update t_old set status = '" + statusText.getText() + "' where id = " + id + "");
                        System.out.println("影响的行数：" + rows);
                        if (rows > 0){
                            JOptionPane.showMessageDialog(null, "登记成功！", "成功", 1);
                            frame.setVisible(false);
                        }else{
                            JOptionPane.showMessageDialog(null, "登记失败！", "失败", 0);
                            frame.setVisible(false);
                        }
                    }else if (StringUtils.isNullOrEmpty(idText.getText()) || StringUtils.isNullOrEmpty(statusText.getText())){
                        JOptionPane.showMessageDialog(null, "参数不能为空！", "失败", 0);
                        frame.setVisible(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "参数信息不合法！", "失败", 0);
                    }
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
