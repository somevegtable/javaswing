package Register;
import javax.swing.*;
import java.awt.*;

public class DemoDialog extends JDialog {
    //construct method 构造方法初始化弹窗样式
     public void DemoDialog(int id){
        this.setTitle("注册提示");
        this.setVisible(true);
        this.setLocation(200,200);
        this.setSize(400,200);
        this.setBackground(Color.LIGHT_GRAY);
        //add one label
        Container contentPane = this.getContentPane();
        JLabel jLabel = new JLabel("注册成功，欢迎使用！！！！！！");
        jLabel.setFont(new Font("微软雅黑", 0, 20));

        contentPane.add(jLabel);
        //center 居中
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

    }
}


