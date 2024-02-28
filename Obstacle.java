import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Obstacle {
    //they will spawn off-screen and go in a straight line to some other end  disappear and reappear in other location and loop this;
    //as the game goes on we add new ones
    //make them curve in the direction of the player
    Obstacle (){
        colors.add(new Color(139, 0, 0));
        colors.add(new Color(255, 0, 0));
        colors.add(new Color(205, 92, 92));
        colors.add(new Color(178, 34, 34));
        colors.add(new Color(220, 20, 60));
        colors.add(new Color(145, 19, 49));
        colors.add(new Color(255, 99, 71));
        colors.add(new Color(250, 128, 114));
        colors.add(new Color(240, 128, 128));
        colors.add(new Color(139, 23, 19));

        //choose color
        color = colors.get(new Random().nextInt(colors.size()));
    }
    int x;
    int y;
    ArrayList<Color> colors = new ArrayList<>();//cloor will be chosen at random from li
    Color color = null;
    static int height = 20;
    static int width = 20;
    static float speedY;
    static float speedX;
    static int directionX;
    static int directionY;
}
