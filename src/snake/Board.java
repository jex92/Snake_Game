package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
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

    private ImageIcon grass;
    private ImageIcon background;
  
    
    final Thread runner;
    
    int foodEaten;
    
    Random random;
    
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
        
        grass = new ImageIcon(getClass().getResource("grass.jpg"));
        background = new ImageIcon(getClass().getResource("background.png"));
        
        
        random = new Random();
        food = new Food(random20(), random20());

        addKeyListener(new GameKeyAdapter());
    
        runner = new Thread(this);
        runner.start();
    }

    public void startGame() {
        foodEaten = 0;
        bodysnake = new BodySnake(this);
        inGame = true;
    }

    public void stopGame() {
        inGame = false;
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

            g2.drawImage(grass.getImage(), 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
            // Iscrtaj sve objekte
            bodysnake.draw(g2);
            food.draw(g2);
            
            Font f = new Font("Helvetica", Font.PLAIN, 30);
            g2.setFont(f);
            g2.setColor(Color.WHITE);
            g2.drawString("Score: " + (foodEaten * 10), 10, 30);
            
            Toolkit.getDefaultToolkit().sync();

            // Optimizacija upotrebe RAM-a, 
            g.dispose();
        } else {
            g2.drawImage(background.getImage(), 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
        }
    }
    
    private int random20() {
        return random.nextInt(20);
    }

    public void update() {
        bodysnake.move();
        
    }

    private void detectCollision() {
        if (bodysnake.getBoundsHead().intersects(food.getBounds())) {
            bodysnake.grow();
            
            while(foodIntersectsSnake()) {
                food = new Food(random20(), random20());
            }
            
            foodEaten++;
        }
        bodysnake.hitItself();
//        if (foodEaten % 5 == 0) {
//         
    }

    @Override
    public void run() {
        
        while (true) {
            
            if (inGame) {
                update();
                detectCollision();
                repaint();
            }
            int score = 100;
            int speed = 150;
            for (int i = 1; i < foodEaten; i++) {
                if ((foodEaten * 10) >= score) {
                    speed = speed - 10;
                } if (speed <= 0) {
                    speed = (speed + 10);
                }

                score += 30;
            }
           

            try {
                
                Thread.sleep(speed);
                 

            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }

        }
        
    }

    private boolean foodIntersectsSnake() {
        for (int i = 0; i < bodysnake.snake.size(); i++) {
            if (bodysnake.snake.get(i).intersects(food.getBounds())) {
                return true;
            }
        }
        return false;
    }

    class GameKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
          
            if (keyCode == KeyEvent.VK_LEFT ) {
                bodysnake.moveLeft();
            } else if (keyCode == KeyEvent.VK_RIGHT ) {
                bodysnake.moveRight();
            } else if (keyCode == KeyEvent.VK_UP ) {
                bodysnake.moveUp();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                bodysnake.moveDown();
            } 
        }
        
   }
}

