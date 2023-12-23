import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args){
        String title = "Soup";
        int w = 1000;
        int h = 600;
        JFrame frame = new JFrame(title);
        frame.setSize(w,h);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.darkGray);

        JButton startBtn = new JButton();


        startBtn.setBounds(100,100,100,100);
//        startBtn.setSize(100,100);
        startBtn.setBackground(new Color(0x2B438E));
        startBtn.setForeground(new Color(0xF3F3F3));
        startBtn.setText("Begin");

        frame.add(startBtn);
        frame.setLayout(null);
        startBtn.setVisible(true);
    }
}
