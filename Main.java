import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    private Color backgroundColor;

    public Main(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        setOpaque(true); // Make the JPanel opaque
        setPreferredSize(new Dimension(1000, 600)); // Set a preferred size
    }

    public void draw(Graphics g){
        g.setColor(this.backgroundColor);
        g.fillRect(0,0,1000,600);
        g.setColor(Color.black);
        g.drawRect(500,300,90,90);
    }

    @Override
    public void paintComponent(Graphics g) {//removed static from here
        super.paintComponent(g);
        draw(g);
    }

    public static void main(String[] args){
        String title = "Soup";
        int w = 1000;
        int h = 600;
        JFrame frame = new JFrame(title);
        frame.setSize(w,h);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color backgroundColor = Color.darkGray;//this is also set within the main class, and so we stop passing the value to methods
        frame.add(new Main(backgroundColor));
        frame.setVisible(true);
    }
}
