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
    int body = 2;
    int STEP = 3;
    private int MIN_DX = 20;
    private int MIN_DY = 20;
    private final int MAX_DX = 40;
    private final int MAX_DY = 40;

    private int dx;
    private int dy;

    MovingState state;
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

    enum MovingState {

        MOVING_LEFT, MOVING_RIGHT, MOVING_UP, MOVING_DOWN
    }

    public BodySnake(Board board) {
        this.board = board;
        x = 160;
        y = 160;
        xHead = x;
        yHead = y;
        for (int i = 0; i < body; i++) {
            Rectangle r = new Rectangle(x, y, s, s);
            snake.add(r);
        }
        this.state = MovingState.MOVING_RIGHT;
    }

    public void moveRight() {
        if (state != MovingState.MOVING_LEFT) {
            state = MovingState.MOVING_RIGHT;
        }
    }

    public void moveLeft() {
        if (state != MovingState.MOVING_RIGHT) {
            state = MovingState.MOVING_LEFT;
        }
    }

    public void moveUp() {
        if (state != MovingState.MOVING_DOWN) {
            state = MovingState.MOVING_UP;
        }
    }

    public void moveDown() {
        if (state != MovingState.MOVING_UP) {
            state = MovingState.MOVING_DOWN;
        }
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

        if (state == MovingState.MOVING_RIGHT) {
            Rectangle r = new Rectangle(snake.getFirst());
            r.x = r.x + s;
            xHead = r.x;
            yHead = r.y;
            snake.add(0, r);
            if (this.snake.size() > body) {
                snake.removeLast();
            }

            if (r.x >= board.PANEL_WIDTH) {
                board.stopGame();
            }
        }

        if (state == MovingState.MOVING_LEFT) {
            Rectangle r = new Rectangle(snake.getFirst()); //Kreiramo novi pravougaonik
            r.x = r.x - s;
            xHead = r.x;
            yHead = r.y;
            snake.add(0, r);

            if (this.snake.size() > body) {
                snake.removeLast();
            }

            if (r.x < 0) {
                board.stopGame();
            }
        }
        if (state == MovingState.MOVING_UP) {
            Rectangle r = new Rectangle(snake.getFirst()); //Kreiramo novi pravougaonik
            r.y = r.y - s;
            xHead = r.x;
            yHead = r.y;
            snake.add(0, r);

            if (this.snake.size() > body) {
                snake.removeLast();
            }

            if (r.y < 0) {
                board.stopGame();
            }
        } else if (state == MovingState.MOVING_DOWN) {
            Rectangle r = new Rectangle(snake.getFirst()); //Kreiramo novi pravougaonik
            r.y = r.y + s;
            xHead = r.x;
            yHead = r.y;
            snake.add(0, r);
            if (this.snake.size() >= body) {
                snake.removeLast();
            }

            if (r.y >= board.PANEL_HEIGHT) {
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
