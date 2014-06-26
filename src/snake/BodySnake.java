package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class BodySnake implements GameObject {

    private Board board;

    int xHead;
    int yHead;
    int x;
    int y;
    int s = 20;
    int body;

    LinkedList<Rectangle> snake = new LinkedList();

    MovingState realMovingState;
    MovingState usersLastMovingState;

    /**
     * Metoda koja povećava telo zmije za jedan pravougaonik.
     */
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

    void grow() {
        body++;
    }

    /**
     * Metoda koja postavlja usersLastMovingState = MovingState.MOVING_RIGHT gde
     * je usersLastMovingState što korisnik želi, tj. u kom smeru želi da se
     * zmija kreće, dok je realMovingState stvarno kretanje zmije.
     */
    public void moveRight() {
        usersLastMovingState = MovingState.MOVING_RIGHT;
    }

    /**
     * Metoda koja postavlja usersLastMovingState = MovingState.MOVING_LEFT;
     */
    public void moveLeft() {
        usersLastMovingState = MovingState.MOVING_LEFT;
    }

    /**
     * Metoda koja postavlja usersLastMovingState = MovingState.MOVING_UP;
     */
    public void moveUp() {
        usersLastMovingState = MovingState.MOVING_UP;
    }

    /**
     * Metoda koja postavlja usersLastMovingState = MovingState.MOVING_DOWN;
     */
    public void moveDown() {
        usersLastMovingState = MovingState.MOVING_DOWN;
    }

    /**
     * Metoda koja boji našu zmiju, pravougaonik po pravougaonik, u CYAN (svetlo
     * plavu) boju.
     */
    @Override
    public void draw(Graphics2D g2) {
        for (int i = 0; i < snake.size(); i++) {
            g2.setColor(Color.CYAN);
            Rectangle r = snake.get(i);
            g2.fillRect(r.x, r.y, r.width, r.height);
        }
    }

    /**
     * Metoda koja ispituje kada realMovingState postaje usersLastMovingState.
     * Zatim ispituje se kakav je status realMovingState i u zavisnosti od tog
     * stanja implementira se kretanje zmije.
     */
    @Override
    public void move() {

        if (realMovingState == MovingState.MOVING_UP && usersLastMovingState != MovingState.MOVING_DOWN
                || realMovingState == MovingState.MOVING_DOWN && usersLastMovingState != MovingState.MOVING_UP
                || realMovingState == MovingState.MOVING_LEFT && usersLastMovingState != MovingState.MOVING_RIGHT
                || realMovingState == MovingState.MOVING_RIGHT && usersLastMovingState != MovingState.MOVING_LEFT) {
            realMovingState = usersLastMovingState;
        }

        Rectangle head = new Rectangle(snake.getFirst());

        if (realMovingState == MovingState.MOVING_RIGHT) {
            head.x = head.x + s;
            xHead = head.x;
            yHead = head.y;
            snake.add(0, head);
            if (this.snake.size() > body) {
                snake.removeLast();
            }

            if (head.x >= 600) {
                head.x = 0;
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
                head.x = 600 - s;
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
                head.y = 600 - s;
            }

        } else if (realMovingState == MovingState.MOVING_DOWN) {
            head.y = head.y + s;
            xHead = head.x;
            yHead = head.y;
            snake.add(0, head);
            if (this.snake.size() >= body) {
                snake.removeLast();
            }

            if (head.y >= 600) {
                head.y = 0;
            }

        }

    }

    /**
     * Metoda koja vraća pravugaonik koji predstavlja glavu zmije.
     */
    public Rectangle2D getBoundsHead() {
        return new Rectangle2D.Double(xHead, yHead, s, s);
    }

    /**
     * Metoda koja ispituje da li je zmija udarila u svoj rep.
     */
    void hitItself() {
        for (int i = 1; i < snake.size(); i++) {
            if (snake.getFirst().intersects(snake.get(i))) {
                board.stopGame();
            }
        }
    }
}
