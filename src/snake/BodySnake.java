package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class BodySnake implements GameObject {

    private final Board board;

    private final Color fillColor = Color.GREEN;

    public static int s = 20;

    LinkedList<Rectangle> snake = new LinkedList();
    int body;
    int STEP = 3;
    private int MIN_DX = 20;
    private int MIN_DY = 20;
    private final int MAX_DX = 40;
    private final int MAX_DY = 40;

    private int dx;
    private int dy;

    MovingState realMovingState;
    MovingState usersLastMovingState;
    
    int xHead;
    int yHead;
    int x;
    int y;

    void speedUp() {
        dx += STEP;
        dy += STEP;

        if (dx > MAX_DX) {
            dx = MAX_DX;
        }
        if (dy > MAX_DY) {
            dy = MAX_DY;
        }

    }

    void grow() {
        body++;
    }

    enum MovingState {

        MOVING_LEFT, MOVING_RIGHT, MOVING_UP, MOVING_DOWN
    }

    public BodySnake(Board board) {
        this.body = 2;
        this.board = board;
        x = 160;
        y = 160;
        xHead = x;
        yHead = y;
        for (int i = 0; i < body; i++) {
            Rectangle r = new Rectangle(x, y, s, s);
            snake.add(r);
        }
        this.realMovingState = MovingState.MOVING_RIGHT;
        this.usersLastMovingState = MovingState.MOVING_RIGHT;
    }

    public void moveRight() {
        usersLastMovingState = MovingState.MOVING_RIGHT;
    }

    public void moveLeft() {
        usersLastMovingState = MovingState.MOVING_LEFT;
    }

    public void moveUp() {
        usersLastMovingState = MovingState.MOVING_UP;
    }

    public void moveDown() {
        usersLastMovingState = MovingState.MOVING_DOWN;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (int i = 0; i < snake.size(); i++) {
            g2.setColor(Color.GREEN);
            Rectangle r = snake.get(i);
            g2.fillRect(r.x, r.y, r.width, r.height);
        }
    }

    @Override
    public void move() {

        if(realMovingState == MovingState.MOVING_UP && usersLastMovingState != MovingState.MOVING_DOWN ||
                realMovingState == MovingState.MOVING_DOWN && usersLastMovingState != MovingState.MOVING_UP ||
                realMovingState == MovingState.MOVING_LEFT && usersLastMovingState != MovingState.MOVING_RIGHT ||
                realMovingState == MovingState.MOVING_RIGHT && usersLastMovingState != MovingState.MOVING_LEFT)
            realMovingState = usersLastMovingState;
        
        System.out.println(realMovingState.toString() + " " + usersLastMovingState.toString());
        
        Rectangle head = new Rectangle(snake.getFirst());
        
        if (realMovingState == MovingState.MOVING_RIGHT) {
            head.x = head.x + s;
            xHead = head.x;
            yHead = head.y;
            snake.add(0, head);
            if (this.snake.size() > body) {
                snake.removeLast();
            }

            if (head.x >= board.PANEL_WIDTH) {
                board.stopGame();
            }
        } else if (realMovingState == MovingState.MOVING_LEFT) {
            head.x = head.x - s;
            xHead = head.x;
            yHead = head.y;
            snake.add(0, head);

            if (this.snake.size() > body) {
                snake.removeLast();
            }

            if (head.x < 0) {
                board.stopGame();
            }
        } else if (realMovingState == MovingState.MOVING_UP) {
            head.y = head.y - s;
            xHead = head.x;
            yHead = head.y;
            snake.add(0, head);

            if (this.snake.size() > body) {
                snake.removeLast();
            }

            if (head.y < 0) {
                board.stopGame();
            }
        } else if (realMovingState == MovingState.MOVING_DOWN) {
            head.y = head.y + s;
            xHead = head.x;
            yHead = head.y;
            snake.add(0, head);
            if (this.snake.size() >= body) {
                snake.removeLast();
            }

            if (head.y >= board.PANEL_HEIGHT) {
                board.stopGame();
            }
        }

    }

    public Rectangle2D getBoundsHead() {
        return new Rectangle2D.Double(xHead, yHead, s, s);
    }

    void hitItself() {
        for (int i = 1; i < snake.size(); i++) {
            if (snake.getFirst().intersects(snake.get(i))) {
                board.stopGame();
            }
        }
    }
}
