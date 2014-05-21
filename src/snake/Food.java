package snake;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class Food {

    private int x;
    private int y;

    private int w = 20;
    private int h = 20;

    private Image image;

    Food() {

        x = (int) (Math.random() * 20) * 20;
        y = (int) (Math.random() * 20) * 20;
        image = new ImageIcon(getClass().getResource("apple.png")).getImage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, w, h, null);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, w, h);
    }

    void newFood() {
        x = (int) (Math.random() * 20) * 20;
        y = (int) (Math.random() * 20) * 20;
    }
}
