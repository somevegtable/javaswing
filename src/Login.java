import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class Login {
    public static void createShow(){
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("欢迎你");
        label.setBounds(250, 0, 100, 25);
        label.setFont(new Font("微软雅黑", Font.BOLD, 25));
        label.setForeground(Color.red);
        panel.add(label);

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.add(panel);
        frame.setVisible(true);
    }
}
