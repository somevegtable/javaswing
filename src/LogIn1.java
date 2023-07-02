import javax.swing.*;
import java.awt.*;

public class LogIn1 extends JFrame {

    public LogIn1(){
        JFrame frame = new JFrame("养老院管理系统登录");
        setBounds(600, 300, 600, 600);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(650, 350, 400, 200);

        JLabel label = new JLabel("养老院管理系统");
        label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));

        JButton button = new JButton("登录");

        panel.setLayout(new GridBagLayout());
        panel.add(label);
        panel.add(button);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LogIn1();
    }

}
