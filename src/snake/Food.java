package snake;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class Food {

    private int x;
    private int y;

    private final int s = 20;

    private Image image;
    
    Food(int x, int y) {
        this.x = x * s;
        this.y = y * s;
        image = new ImageIcon(getClass().getResource("apple.png")).getImage();
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, s, s, null);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, s, s);
    }
}
