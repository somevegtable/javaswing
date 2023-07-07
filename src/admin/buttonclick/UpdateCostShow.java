package admin.buttonclick;

import admin.BaoanManageShow;
import admin.CostManageShow;
import com.mysql.cj.util.StringUtils;
import dao.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UpdateCostShow {
    public static void createShow(){
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("修改费用信息");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //创建费用金额标签
        JLabel amountLabel = new JLabel("金额：");
        amountLabel.setFont(new Font("微软雅黑", 0, 13));
        amountLabel.setBounds(180, 100, 110, 25);
        panel.add(amountLabel);

        //创建金额输入文字域
        JTextField amountText = new JTextField(20);
        amountText.setBounds(250, 100, 165, 25);
        panel.add(amountText);

        //创建床位号标签
        JLabel placeLabel = new JLabel("床位号：");
        placeLabel.setFont(new Font("微软雅黑", 0, 13));
        placeLabel.setBounds(180, 150, 110, 25);
        panel.add(placeLabel);

        //创建床位号输入文字域
        JTextField placeText = new JTextField(20);
        placeText.setBounds(250, 150, 100, 25);
        panel.add(placeText);


        //创建描述标签
        JLabel miaosLabel = new JLabel("描述：");
        miaosLabel.setFont(new Font("微软雅黑", 0, 13));
        miaosLabel.setBounds(180, 200, 110, 25);
        panel.add(miaosLabel);

        //创建描述输入文字域
        JTextField miaosText = new JTextField(20);
        miaosText.setBounds(250, 200, 165, 25);
        panel.add(miaosText);

        //创建修改人id标签
        JLabel idLabel = new JLabel("修改信息id：");
        idLabel.setFont(new Font("微软雅黑", 0, 13));
        idLabel.setBounds(180, 250, 100, 25);
        panel.add(idLabel);

        //创建修改人id文字域
        JTextField idText = new JTextField(20);
        idText.setBounds(250, 250, 165, 25);
        panel.add(idText);

        JButton confirm = new JButton("修改");
        confirm.setBounds(250, 300, 80, 25);
        panel.add(confirm);
        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(StringUtils.isNullOrEmpty(amountText.getText()) ||
                        StringUtils.isNullOrEmpty(placeText.getText()) ||
                        StringUtils.isNullOrEmpty(miaosText.getText()) ||
                        StringUtils.isNullOrEmpty(idText.getText())){
                    JOptionPane.showMessageDialog(null, "参数不能为空！", "失败", 0);
                }else{
                    int check = JOptionPane.showConfirmDialog(null, "确定修改该条信息吗?", "添加",JOptionPane.YES_NO_OPTION);//返回的是按钮的index  i=0或者1
                    if (check == 0) {
                        DAO dao = new DAO();
                        ResultSet resultSet = dao.query("select id from t_cost");
                        Set<String> set = new HashSet<>();
                        try{
                            while(resultSet.next()){
                                set.add(resultSet.getString(1));
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        if (!set.contains(idText.getText())){
                            JOptionPane.showMessageDialog(null, "id不存在", "失败", 0);
                        }else{
                            Integer row = dao.update("update t_cost set amount = '"+amountText.getText()+"', place = '"+placeText.getText()+"', description = '"+miaosText.getText()+"' where id = "+idText.getText()+"");
                            JOptionPane.showMessageDialog(null, "修改成功", "成功", 1);
                        }
                    }
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    CostManageShow.createShow();
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
