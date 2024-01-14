import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
//!THE obstacles need a class bc there will be a lot of them
//pausing?
//sounds
//the moving thingy the player moves
//after everytings done we can try make the player something more interesting than a cube
public class Main extends JPanel implements MouseInputListener, KeyListener {

    //NOW player

    public static class Player {///??? can i move it out of main
        static Color color = Color.orange;
        static float maxHP = 5;
        static float currentHP = maxHP;
        static int height = 60;
        static int width = 30;
        static int x = (frame.getWidth()/2);
        static int y = (frame.getHeight()/2);
        static float speedY;
        static float speedX;

        static int directionX;
        static int directionY;

        static boolean w = false;
        static boolean a = false;
        static boolean s = false;
        static boolean d = false;

    }

    boolean gameStarted = false;
    int pointCounter = 0;
    private Color backgroundColor;
    static JFrame frame;
    int[] btnPosition = {280,120};//x, y//should make its own obj and add the method for deceting collision
    int[] btnSize = {90,90};//width, height

    void moveBtn(){
        Random rnd = new Random();
        btnPosition[0] = rnd.nextInt(frame.getWidth()+(btnSize[0]/2))+(0-(btnSize[0]/2));
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
        this.addMouseListener(this);
        this.addKeyListener(this);

        setFocusable(true);
        requestFocus();
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

            //drawing the player
 int speed = 13;
            if (Player.w){
                Player.speedY = speed;
                Player.directionY = -1;
            }
            if (Player.a){
                Player.speedX = speed;
                Player.directionX = -1;

            }
            if (Player.s){
                Player.speedY = speed;
                Player.directionY = 1;

            }
            if (Player.d){
                Player.speedX = speed;
                Player.directionX = 1;

            }

            if ((Player.w || Player.s) && (Player.a || Player.d)) {
                Player.speedX /= Math.sqrt(2);
                Player.speedY /= Math.sqrt(2);
            }
//we can add so it "slides  " to crate a more fluid movement

            Player.x += (Player.speedX)*Player.directionX;
            Player.y += (Player.speedY)*Player.directionY;

            g.setColor(Player.color);
            g.fillRect(Player.x, Player.y, Player.width, Player.height);

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
//        Obstacle sus = new Obstacle();

        JFrame frame = new JFrame(title);
        frame.setSize(w,h);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//maximazie the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color backgroundColor = Color.darkGray;//this is also set within the main class, and so we stop passing the value to methods
        Main main = new Main(backgroundColor, frame);
        frame.add(main);
        frame.setVisible(true);
        frame.setSize(frame.getWidth(), frame.getHeight());
        Timer timer = new Timer();
        int delay = 0;
        int period = 1000/60; //
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                frame.repaint();
            }
        }, delay, period);
        //we can create a function that  runs when a variavle is set to true by the click detecrot and then it sets it to false by the end of itself
    }
@Override
public void keyTyped(KeyEvent e) {
    //for arrow keys
    //up38
    //left/37
    //right39
    //down40

}

    @Override
    public void keyPressed(KeyEvent e) {
        int speed = 13;
        //save which ones are still pressed down and make them matter
        switch(e.getKeyChar()){
            case 'w':
//                Player.speed =
                Player.speedY = speed;
                Player.directionY = -1;
                Player.w = true;
                 break;
            case 'a':
                Player.speedX = speed;
                Player.directionX = -1;
                Player.a = true;

                 break;
            case 's':
                Player.speedY = speed;
                Player.directionY = 1;
                Player.s = true;

                break;
            case 'd':
                Player.speedX = speed;
                Player.directionX = 1;
                Player.d = true;


                 break;
        }

        if ((e.getKeyChar() == 'w' || e.getKeyChar() == 's') && (e.getKeyChar() == 'a' || e.getKeyChar() == 'd')) {
            Player.speedX /= Math.sqrt(2);
            Player.speedY /= Math.sqrt(2);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'w':
                Player.speedY = 0;
                Player.w = false;

                break;
            case 'a':
                Player.speedX = 0;
                Player.a = false;

                break;
            case 's':
                Player.speedY = 0;
                Player.s = false;

                break;
            case 'd':
                Player.speedX = 0;
                Player.d = false;

                break;

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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

    }
    @Override
    public void mouseReleased(MouseEvent e) {

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
