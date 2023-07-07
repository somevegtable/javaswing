package admin.buttonclick;

import admin.BaoanManageShow;
import admin.CostManageShow;
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

public class DeleteCostShow {
    public static void createShow(){
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("删除费用信息");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //创建id标签
        JLabel idLabel = new JLabel("删除费用信息id：");
        idLabel.setFont(new Font("微软雅黑", 0, 13));
        idLabel.setBounds(150, 100, 110, 25);
        panel.add(idLabel);

        //创建id输入文字域
        JTextField idText = new JTextField(20);
        idText.setBounds(220, 100, 165, 25);
        panel.add(idText);


        JButton confirm = new JButton("删除");
        confirm.setBounds(250, 150, 80, 25);
        panel.add(confirm);
        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int check = JOptionPane.showConfirmDialog(null, "确定删除该条信息吗?", "删除",JOptionPane.YES_NO_OPTION);//返回的是按钮的index  i=0或者1
                if (check == 0){
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
                        JOptionPane.showMessageDialog(null, "id不存在！", "失败", 0);
                    }else{
                        dao.update("delete from t_cost where id = "+idText.getText()+"");
                        JOptionPane.showMessageDialog(null, "删除成功", "成功", 1);
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
