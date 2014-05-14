package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import static snake.BodySnake.MovingState.MOVING_RIGHT;



public class BodySnake extends Rectangle.Double implements GameObject {

    private final  Board board;
    
    private final Color fillColor = Color.GREEN;
   
    public static  int s = 20;
    ArrayList <Rectangle> snake= new ArrayList();

    private int dx = 20;
    private int dy = 20;
    
    private MovingState state;

    
    enum MovingState { MOVING_LEFT, MOVING_RIGHT, MOVING_UP, MOVING_DOWN }
   
    
    public BodySnake(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.width = s;
        this.height = s;
        this.state = MovingState.MOVING_RIGHT;
    }
 
    public void moveRight() {
        state = MovingState.MOVING_RIGHT;
    }
    
    public void moveLeft() {
        state = MovingState.MOVING_LEFT;
    }
    
    public void moveUp() {
        state = MovingState.MOVING_UP;
    }
 
    public void moveDown() {
        state = MovingState.MOVING_DOWN;
    }
    
    @Override
    public void draw(Graphics2D g2) {
        g2.setPaint(fillColor);
        g2.fill(this);
       
    }
    
    @Override
    public void move() {
        
        if (state == MovingState.MOVING_RIGHT)
            x += dx;
        if (state == MovingState.MOVING_LEFT)
            x -= dx;
        if (state == MovingState.MOVING_UP)
            y -= dy;
        else if (state == MovingState.MOVING_DOWN)
            y += dy;
        
        }
    
       
}

