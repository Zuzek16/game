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

    int pointCounter = 0;
    private Color backgroundColor;
    JFrame frame;
    int[] Btnposition = {280,120};//x, y//should make its own obj and add the method for deceting collision
    int[] Btnsize = {90,90};//width, height

    void moveBtn(){
        Random rnd = new Random();

        Btnposition[0] = rnd.nextInt(frame.getWidth()+(Btnsize[0]/2))+(0-(Btnsize[0]/2));
//        System.out.println(Btnposition[0]);
        Btnposition[1] =  rnd.nextInt(frame.getHeight()+(Btnsize[1]/2))+(0-(Btnsize[1]));

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
        setPreferredSize(new Dimension(1000, 600)); // Set a preferred size
        addMouseListener(this);
    }

    public void draw(Graphics g){
        g.setColor(this.backgroundColor);
        g.fillRect(0,0,1000,600);
        g.setColor(Color.black);
        g.drawRect(Btnposition[0],Btnposition[1],Btnsize[0],Btnsize[1]);
        g.setColor(new Color(0x311B31));
        g.fillRect(Btnposition[0],Btnposition[1],Btnsize[0],Btnsize[1]);
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
        System.out.println(frame.getWidth());////THIS NOW
        System.out.println(frame.getHeight());
        //frame.setSize(dfsffsdfsdsdfsdf)
        Timer timer = new Timer();
        int delay = 0;
        int period = 1000/60; //
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Your function here
                System.out.println("x: "+main.Btnposition[0]);
                System.out.println("y: "+main.Btnposition[1]);
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

        if (e.getX() >= Btnposition[0] &&
                e.getX() <= (Btnposition[0]+Btnsize[0]) &&
                e.getY() >= Btnposition[1] &&
                e.getY() <= (Btnposition[1]+Btnsize[1])
        ){
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
