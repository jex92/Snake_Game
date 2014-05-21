package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Board extends JPanel implements Runnable {

    /**
     * Širina table
     */
    public final int PANEL_WIDTH = 600;
    /**
     * Visina table
     */
    public final int PANEL_HEIGHT = 600;

    final Color BACKGROUND_COLOR = Color.BLACK;

    private Image image;
    final Thread runner;

    Boolean inGame;

    BodySnake bodysnake;
    Food food;

    String message;

    public Board() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
        setFont(getFont().deriveFont(Font.BOLD, 18f));
        setDoubleBuffered(true);

        inGame = false;

        bodysnake = new BodySnake(this);

        food = new Food();

        addKeyListener(new GameKeyAdapter());

        runner = new Thread(this);
        runner.start();
    }

    public void startGame() {
        bodysnake = new BodySnake(this);
        inGame = true;
    }

    public void stopGame(String message) {
        inGame = false;
        this.message = message;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        if (inGame) {
            // Savjeti pri iscrtavanju

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // Iscrtaj teren
            g2.drawRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
            g2.setColor(Color.BLACK);

            image = new ImageIcon(getClass().getResource("grass.jpg")).getImage();
            g2.drawImage(image, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
            // Iscrtaj sve objekte
            bodysnake.draw(g2);
            food.draw(g2);

            Toolkit.getDefaultToolkit().sync();

            // Optimizacija upotrebe RAM-a, 
            g.dispose();
        } else {
            image = new ImageIcon(getClass().getResource("background.png")).getImage();
            g2.drawImage(image, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);

        }
    }

    public void update() {
        bodysnake.move();
    }

    private void detectCollision() {

        if (bodysnake.getBoundsHead().intersects(food.getBounds())) {
            bodysnake.body++;
            food.newFood();

        }

    }

    @Override
    public void run() {
        while (true) {
            update();
            detectCollision();
            repaint();

            try {
                Thread.sleep(120);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    class GameKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_LEFT) {
                bodysnake.moveLeft();
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                bodysnake.moveRight();
            } else if (keyCode == KeyEvent.VK_UP) {
                bodysnake.moveUp();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                bodysnake.moveDown();
            }
        }

    }

}
