package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Jelena
 */

public class Food {

    private int x;
    private int y;

    private int w = 20;
    private int h = 20;

    private Image image;
    private int width;
    private int height;

    Food(int x, int y) {

        this.x = (int) (Math.random() * 20) * 20;
        this.y = (int) (Math.random() * 20) * 20;
        image = new ImageIcon(getClass().getResource("jabuka copy.png")).getImage();

        this.x = (int) (Math.random() * 20) * 20;
        this.y = (int) (Math.random() * 20) * 20;
        width = 20;
        height = 20;

        image = new ImageIcon(getClass().getResource("jabuka copy.png")).getImage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, width, height, null);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, w, h);
    }

    void newFood() {
        x = (int) (Math.random() * 20) * 20;
        y = (int) (Math.random() * 20) * 20;
    }
}
