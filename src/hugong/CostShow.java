package hugong;

import com.mysql.cj.util.StringUtils;
import dao.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CostShow {
    public static void createShow() throws SQLException {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("费用支出登记");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //创建老人place标签
        JLabel placeLabel = new JLabel("申请老人床位号：");
        placeLabel.setFont(new Font("微软雅黑", 0, 13));
        placeLabel.setBounds(180, 100, 110, 25);
        panel.add(placeLabel);

        //创建老人place输入文字域
        JTextField placeText = new JTextField(20);
        placeText.setBounds(280, 100, 165, 25);
        panel.add(placeText);

        //创建申请费用金额标签
        JLabel amountLabel = new JLabel("申请费用金额：");
        amountLabel.setFont(new Font("微软雅黑", 0, 13));
        amountLabel.setBounds(180, 150, 100, 25);
        panel.add(amountLabel);

        //创建申请费用输入文字域
        JTextField amountText = new JTextField(20);
        amountText.setBounds(280, 150, 165, 25);
        panel.add(amountText);

        //创建申请原因标签
        JLabel reasonLabel = new JLabel("申请费用原因：");
        reasonLabel.setFont(new Font("微软雅黑", 0, 13));
        reasonLabel.setBounds(180, 200, 100, 25);
        panel.add(reasonLabel);

        //创建申请原因文字域
        JTextField reasonText = new JTextField(20);
        reasonText.setBounds(280, 200, 165, 25);
        panel.add(reasonText);

        JButton confirm = new JButton("登记");
        confirm.setBounds(250, 250, 80, 25);
        panel.add(confirm);
        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DAO dao = new DAO();
                ResultSet resultSet = dao.query("select code from t_bed");
                Set<String> set = new HashSet<>();
                try{
                    while(resultSet.next()){
                        set.add(resultSet.getString(1));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                if (StringUtils.isNullOrEmpty(placeText.getText())){
                    JOptionPane.showMessageDialog(null, "床位号不能为空！", "失败", 0);
                }else if(!set.contains(placeText.getText())){
                    JOptionPane.showMessageDialog(null, "床位号不存在！", "失败", 0);
                }else if (StringUtils.isNullOrEmpty(amountText.getText())){
                    JOptionPane.showMessageDialog(null, "申请金额不能为空！", "失败", 0);
                }else if (StringUtils.isNullOrEmpty(reasonText.getText())){
                    JOptionPane.showMessageDialog(null, "申请原因不能为空！", "失败", 0);
                }else{
                    Integer row = dao.update("insert into t_cost (amount, place, description)" +
                            "values("+amountText.getText()+", '"+placeText.getText()+"', '"+reasonText.getText()+"')");

                    if (row > 0){
                        JOptionPane.showMessageDialog(null, "登记成功", "成功", 1);
                    }else{
                        JOptionPane.showMessageDialog(null, "登记失败！", "失败", 0);
                    }
                }


            }
        });

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
