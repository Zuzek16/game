import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
//!THE obstacles need a class bc there will be a lot of them
// main menu
//pausing?
//sounds
//the moving thingy the player moves
public class Main extends JPanel implements MouseInputListener {
    boolean gameStarted = false;
    int pointCounter = 0;
    private Color backgroundColor;
    JFrame frame;
    int[] btnPosition = {280,120};//x, y//should make its own obj and add the method for deceting collision
    int[] btnSize = {90,90};//width, height

    void moveBtn(){
        Random rnd = new Random();
        btnPosition[0] = rnd.nextInt(frame.getWidth()+(btnSize[0]/2))+(0-(btnSize[0]/2));
//        System.out.println(btnPosition[0]);
        btnPosition[1] =  rnd.nextInt(frame.getHeight()+(btnSize[1]/2))+(0-(btnSize[1]));

    }

    void givePoints(){
        pointCounter ++;
    }
    void givePoints(int amount){
        pointCounter += amount;
    }



    public Main(Color backgroundColor, JFrame frame) {
        this.frame = frame;
        this.backgroundColor = backgroundColor;
        boolean test = true;

        setOpaque(true); // Make the JPanel opaque
        setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight())); // Set a preferred size
        addMouseListener(this);
    }

    public void draw(Graphics g){
        Font defFont = g.getFont();
        FontMetrics defFontMetrics = g.getFontMetrics();
        g.setColor(this.backgroundColor);
        g.fillRect(0,0,frame.getWidth()+100,frame.getHeight()+100);
        g.setColor(Color.black);
        g.drawRect(btnPosition[0],btnPosition[1],btnSize[0],btnSize[1]);
        g.setColor(new Color(0x311B31));
        g.fillRect(btnPosition[0],btnPosition[1],btnSize[0],btnSize[1]);

        g.setColor(Color.white);
        if (!gameStarted) {
        g.drawString("Start game", (btnPosition[0]+(btnSize[0])/5),(btnPosition[1]+(btnSize[1])/2));
        g.setFont(new Font("Monospaced", Font.BOLD, 90));
        g.drawString("Soup", btnPosition[0]*2, btnPosition[1]+(60));//title
            g.setFont(new Font("Monospaced", Font.PLAIN, 20));
            int lineHeight = 25; // Adjust the value based on your font size and spacing
            int yPosition = btnPosition[1] + (2 * btnSize[1]); // Starting y-position
            g.drawString("Use WASD/arrow keys to move your character", btnPosition[0], yPosition);
            yPosition += lineHeight;
            g.drawString("and avoid moving obstacles,", btnPosition[0], yPosition);
            yPosition += lineHeight;
            g.drawString("while using the mouse to score points by clicking on buttons", btnPosition[0], yPosition);
            yPosition += lineHeight;
            g.drawString("appearing around the screen.", btnPosition[0], yPosition);
            yPosition += lineHeight;
            g.drawString("The buttons change position each time you successfully click them.", btnPosition[0], yPosition);
            yPosition += lineHeight;
            g.drawString("Every few gained points, the game will speed up.", btnPosition[0], yPosition);
        } else {
            g.setFont(defFont);
            g.setFont(new Font("default", Font.PLAIN, 20));
            g.drawString("Score: ", (getWidth() - g.getFontMetrics(g.getFont()).stringWidth("Score: 000000")) / 2, g.getFontMetrics().getHeight());

            int scoreTextX = (getWidth() - g.getFontMetrics(g.getFont()).stringWidth("Score: ")) / 2;
            int scoreCounterX = scoreTextX + g.getFontMetrics(g.getFont()).stringWidth("Score: ");
            g.drawString(String.valueOf(pointCounter), scoreCounterX,g.getFontMetrics().getHeight());
        }
        g.setFont(defFont);
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
//        frame.setSize(w,h);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color backgroundColor = Color.darkGray;//this is also set within the main class, and so we stop passing the value to methods
        Main main = new Main(backgroundColor, frame);
        frame.add(main);
        frame.setVisible(true);
//        System.out.println(frame.getWidth());////THIS NOW
//        System.out.println(frame.getHeight());
        frame.setSize(frame.getWidth(), frame.getHeight());
        Timer timer = new Timer();
        int delay = 0;
        int period = 1000/60; //
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Your function here
                System.out.println("x: "+main.btnPosition[0]);
                System.out.println("y: "+main.btnPosition[1]);
                frame.repaint();
//                System.out.println("Your points: "+main.pointCounter);
//                System.out.println("Function executed");//debug
            }
        }, delay, period);
        //////try to make it move smoothy
        ////fill the rect
        ////dected a click in an area
        /////make it intractable
        //when you click the button it moves
        //
        //we can create a function that  runs when a variavle is set to true by the click detecrot and then it sets it to false by the end of itself
        //move its things to a separate class classBtn
        //make sure it still works
    }
//    private static void myTask() {
//        System.out.println("Running");
//    }
    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println("You clicked: ");

        if (e.getX() >= btnPosition[0] &&
                e.getX() <= (btnPosition[0]+btnSize[0]) &&
                e.getY() >= btnPosition[1] &&
                e.getY() <= (btnPosition[1]+btnSize[1])
        ){
            if (!gameStarted){gameStarted=true;}
            System.out.println("Clicked the button - you got a point ");//still works - even on the moving cube
            givePoints();
            moveBtn();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("You pressed");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println("You released");

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
